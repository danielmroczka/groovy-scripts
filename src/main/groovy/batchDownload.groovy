/**
 * Downloads files using range pattern of url
 */

def range = 'a'..'z'

range.each { param ->

    def url = "http://server/${param}.pdf"
    String filename = java.nio.file.Paths.get(new URI(url).getPath()).getFileName().toString()
    try {
        redirectFollowingDownload(url, filename)
        println url + " -> " + filename
    }
    catch (FileNotFoundException fnfe) {
        new File(filename).delete()
        println "File not found: " + url;
    }
}

def redirectFollowingDownload(String url, String filename) {
    while (url) {
        new URL(url).openConnection().with { conn ->
            conn.instanceFollowRedirects = false
            url = conn.getHeaderField("Location")
            if (!url) {
                new File(filename).withOutputStream { out ->
                    conn.inputStream.with { inp ->
                        out << inp
                        inp.close()
                    }
                }
            }
        }
    }
}
