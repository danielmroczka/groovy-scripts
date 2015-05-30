import groovy.json.JsonSlurper
import groovy.xml.MarkupBuilder

public static void main(String[] args) {
    if (args.length < 1) {
        println 'No args!'
    }
    for (def file : args) {
        println "Converting file: " + file
        def reader = new FileReader(file)
        def ui = new JsonSlurper().parse(reader)
        def writer = new StringWriter()
        def xml = new MarkupBuilder(writer)
        xml.items {
            ui.items.each { widget ->
                item(type: widget.type,
                        height: widget.height,
                        width: widget.width) {
                    axes {
                        widget.axes.each { widgetAxis ->
                            axis(type: widgetAxis.type,
                                    name: widgetAxis.title)
                        }
                    }
                }
            }
        }
        File result = new File('output.xml')
        result.text = writer.toString();

        println writer.toString()
    }
}

