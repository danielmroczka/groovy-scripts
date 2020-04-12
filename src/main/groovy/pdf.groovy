/**
 * Prints pdf info
 */
@GrabConfig(systemClassLoader = true)
@Grab(group = 'org.apache.pdfbox', module = 'pdfbox', version = '2.0.19')

org.apache.pdfbox.pdmodel.PDDocument doc = org.apache.pdfbox.pdmodel.PDDocument.load(new File(args[0]))
org.apache.pdfbox.pdmodel.PDDocumentCatalog catalog = doc.getDocumentCatalog()
org.apache.pdfbox.pdmodel.common.PDMetadata metadata = catalog.getMetadata()

org.apache.pdfbox.pdmodel.PDDocumentInformation info = doc.getDocumentInformation()

def report = "Page Count=${doc.getNumberOfPages()}\n"
        << "Title=${info.getTitle()}\n"
        << "Author=${info.getAuthor()}\n"
        << "Subject=${info.getSubject()}\n"
        << "Keywords=${info.getKeywords()}\n"
        << "Creator=${info.getCreator()}\n"
        << "Producer=${info.getProducer()}\n"
        << "Creation Date=${info.getCreationDate()}\n"
        << "Modification Date=${info.getModificationDate()}\n"
        << "Trapped=${info.getTrapped()}\n"

println report
println metadata.properties
