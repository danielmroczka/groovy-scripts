/**
 * Reads file URL from file and downloads resources
 *
 * @param args
 */
static void main(String[] args) {
    if (args.length == 0) {
        println("Usage: provide filename!")
        return
    }

    def file = new File(args[0])
    def force = args.length > 1 && "force".equalsIgnoreCase(args[1])

    file.withReader { reader ->
        while (line = reader.readLine()) {
            //content = line.toURL().getBytes()
            def outputFilename = line.substring(line.lastIndexOf("/") + 1)

            if (!new File(outputFilename).exists() || force) {
                println("Saving: " + outputFilename + "...")
                def out = new File(outputFilename).newOutputStream()
                out << new URL(line).openStream()
                out.close()
            } else {
                println("Skipped: " + outputFilename)
            }
        }
    }
}