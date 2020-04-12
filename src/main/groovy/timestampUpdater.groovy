// Changes timestamp modification of file depends on filename pattern:
// yyyyMMdd_HHmmss.jpg -> setLastModified(yyyyMMdd_HHmmss)
// Script useful if for some reason last modification has been changed and it is important to sort files by modification time
import java.time.LocalDateTime

import static groovy.io.FileType.FILES
def dir = new File(".")
def files = []
dir.traverse(type: FILES, maxDepth: 0) { files.add(it) }

println files.size()

for (String fileName:files) {
    def file = new File(fileName)
    println (new Date(file.lastModified()))
    fileName = fileName.substring(2, fileName.length()-4)

    try {
        Date date = Date.parse('yyyyMMdd_HHmmss', fileName)
        file.setLastModified(date.time)
    }
    catch (e) {
            
    }
}