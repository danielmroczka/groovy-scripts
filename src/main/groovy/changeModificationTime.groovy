/**
 * Updates last modification time video files if matches name pattern yyyyMMdd_HHmmss to time from filename
 *
 * Rename video file if doesn't match name pattern to name parsed from last modification time
 */

def path = '.'

if (args.size() > 0) {
    path = args[0]
}

def all = 0
def changed = 0

new File(path).eachFileRecurse { file ->
    if (file.name.toLowerCase().endsWith('.mp4') || file.name.toLowerCase().endsWith('.avi')) {

        def matcher = file.name =~ /\d{8}_\d{6}\..+/
        def modification = file.lastModified()

        if (matcher.matches()) {
            def fromFileName = new Date().parse("yyyyMMdd_HHmmss", file.name.substring(0, file.name.indexOf("."))).toTimestamp()

            if (Math.abs(modification - fromFileName.time) > 60000) {
                changed++
                file.setLastModified(fromFileName.time)
                println "${changed}. Changing modification time ${file.name} from ${modification} to ${fromFileName}"
            }

        } else {
            changed++
            def now = new Date(file.lastModified())
            def ext = file.name.substring(file.name.indexOf("."))
            println "${changed}. Renaming file ${file.name} to ${now.format("yyyyMMdd_HHmmss") + ext}"
            file.renameTo(file.parent + "\\" + now.format("yyyyMMdd_HHmmss") + ext)
        }

        all++
    }
}

println "Processed ${all} files, changed ${changed} file(s)"
