import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

if (!args.length in 2) {
    println 'Usage: xslt <xslt filename> <xml filename> <output filename>'
}

def xslt = new File(args[0]).text.trim()
def input = new File(args[1]).text.trim()
def output = System.out
if (args.length > 2) {
    output = new File(args[2])
    if (!output.exists()) {
        output.createNewFile()
    }
}

def factory = TransformerFactory.newInstance()
def transformer = factory.newTransformer(new StreamSource(new StringReader(xslt)))
transformer.setOutputProperty(OutputKeys.INDENT, "yes")
transformer.transform(new StreamSource(new StringReader(input)), new StreamResult(output))