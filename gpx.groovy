/**
 * Reads gpx files in provided directory then parses files and uses maps.googleapis.com/maps/api/geocode to find street name and postcode
 */

import groovy.io.FileType
import groovy.json.JsonSlurper

def dir = new File('j:/download/gpx/')

Set zip = []
Set street = []

dir.eachFile(FileType.FILES) { file ->
    if (!file.name.endsWith('.gpx')) {
        return
    }
    println "Processing file: " + file


    def log = new File(dir, file.name.replace('.gpx', '.log'))
    log.text = ''


    def xml = new XmlSlurper().parseText(file.text)



    def lastPos

    xml.trk.trkseg.trkpt.each { trkpt ->


        if (lastPos && dist(trkpt, lastPos) < 50) {
            return
        }

        def res = toAddress(trkpt).results[0].address_components

        def streetname = res.findAll {
            it.types.contains('route')
        }.collect { it.long_name }

        def postalcode = res.findAll { it.types.contains('postal_code') }.collect({ it.long_name })

        log.append(postalcode[0] + ', ' + streetname[0] + '\n')

        zip.add(postalcode[0])
        street.add(streetname[0])

        lastPos = trkpt

        print '.'
    }

}

def toAddress(trkpt) {

    def status = false

    def jsonSlurper = new JsonSlurper()
    def object
    while (!status) {
        String html = ('https://maps.googleapis.com/maps/api/geocode/json?latlng=' + trkpt.@lat + ',' + trkpt.@lon).toURL().text
        object = jsonSlurper.parseText(html)
        status = object.status == 'OK'
        if (!status) {
            sleep(5)
        }
    }

    return object
}

double dist(trkpt1, trkpt2) {
    def long1 = Double.parseDouble(trkpt1.@lon.text())
    def long2 = Double.parseDouble(trkpt2.@lon.text())
    def lat1 = Double.parseDouble(trkpt1.@lat.text())
    def lat2 = Double.parseDouble(trkpt2.@lat.text())

    d2r = Math.PI / 180.0

    Math.with {
        double dlong = (long2 - long1) * d2r;
        double dlat = (lat2 - lat1) * d2r;
        double a = pow(sin(dlat / 2.0), 2) + cos(lat1 * d2r) * cos(lat2 * d2r) * pow(sin(dlong / 2.0), 2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));
        double d = 6367000 * c;
        return d;
    }
}

println ''
println zip
println street
println ''
println 'Done'

