/**
 * Build trkseg based on GPS location and timestamp from EXIF stored in JPG files in current folder
 */
@Grapes(
    @Grab(group='com.drewnoakes', module='metadata-extractor', version='2.17.0')
)

import com.drew.imaging.jpeg.*;
import com.drew.imaging.*;
import com.drew.metadata.*;
import com.drew.metadata.exif.*;
import groovy.io.FileType
import groovy.xml.*

import java.time.Instant
import java.time.ZoneId

def tree = new Node(null, "trkseg")

new File(".").eachFileRecurse(FileType.FILES) { file ->
    if (file.name.toLowerCase().endsWith(".jpg")) {
        process(tree, file)
    }
}

def process(Node tree, File file) {
    def metadata = ImageMetadataReader.readMetadata(file);
    def gpsDirectory = metadata.getDirectoriesOfType(GpsDirectory.class);
    def node = new Node(tree, "trkpt", [lat: gpsDirectory.geoLocation.latitude[0], lon: gpsDirectory.geoLocation.longitude[0]])

    def exifData = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

    if (exifData != null) {
        def time = exifData.getDateOriginal(TimeZone.getDefault()).toInstant()
        new Node(node, "time", time)
    }
}

def file = new File("output.gpx")
file.write(XmlUtil.serialize(tree))
