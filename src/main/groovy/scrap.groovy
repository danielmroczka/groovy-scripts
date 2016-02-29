@Grab(group = 'org.codehaus.groovy.modules.http-builder', module = 'http-builder', version = '0.7.1')
import groovyx.net.http.*

def http = new HTTPBuilder('http://www.foxebook.net/page/1')

def html = http.get([:])

/*http.get( path : '/',
          contentType : 'text',
          //query : [q:'Groovy']
           ) { resp, reader ->
 
  println "response status: ${resp.statusLine}"
  println 'Headers: -----------'
  resp.headers.each { h ->
    println " ${h.name} : ${h.value}"
  }
  
  resp.
}*/

html."**".findAll { it.@id.toString() == 'content' && it.name() == 'DIV' }.
        DIV[0].ARTICLE.H3.A.@href.collect { l ->
    retrieveBook(l);
}

def retrieveBook(url) {

    def http = new HTTPBuilder(url)
    def html = http.get([:])
    html."**".findAll { it.@id.toString() == 'download' && it.name() == 'DIV' }.
            DIV[0].TABLE.TBODY.TR.TD[1].A.collect { l ->
        def bookUrl = l.@href
        gotoBook(bookUrl)
    }
}

def gotoBook(url) {
    url.toURL().eachLine {
        println it
    }
    //def page = new XmlParser().parse('http://www.foxebook.net/download.php?url=aHR0cDovL2FkZi5seS8xODUxMDk4L2h0dHA6Ly93d3cuZW1iZWR1cGxvYWQuY29tLz9kPTdDTVdHR0VFS0U=')
    //println page 
//  def http = new HTTPBuilder(url)

    //http.get( path : '/',
    //      contentType : 'text',
    //     ) { resp, reader -> println resp.getContext().getAttribute('meta') }

}

println html