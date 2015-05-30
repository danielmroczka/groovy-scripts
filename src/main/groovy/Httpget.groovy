@Grab(group = 'org.codehaus.groovy.modules.http-builder', module = 'http-builder', version = '0.7')
import groovyx.net.http.HTTPBuilder
@Grab(group = 'org.codehaus.groovy.modules.http-builder', module = 'http-builder', version = '0.7')

import groovyx.net.http.HTTPBuilder

import static groovyx.net.http.ContentType.HTML
import static groovyx.net.http.Method.GET

def http = new HTTPBuilder('https://www.youtube.com/watch?v=RTQ9Ct5C1C8')

http.request(GET, HTML) { req ->
    // uri.path = 'watch?v=RTQ9Ct5C1C8' // overrides any path in the default URL
    headers.'User-Agent' = 'Mozilla/5.0'

    response.success = { resp, reader ->
        assert resp.status == 200
        println "My response handler got response: ${resp.statusLine}"
        println "Response length: ${resp.headers.'Content-Length'}"
        //System.out << reader // print response reader
    }

    // called only for a 404 (not found) status code:
    response.'404' = { resp ->
        println 'Not found'
    }
}

/*for ( i in 0..10 ) { 
    print(".")
    def html = http.get(path: '/watch', query:[v:'RTQ9Ct5C1C8'], contentType: HTML )
    print html
}*/
