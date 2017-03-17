/**
 * Reads file URL from file and downloads resources
 * 
 * @param args
 */
static void main(String[] args) {
    if (args.length == 0) {
        pritnln("Usage: provide filename!")
        return
    }

    def file = new File(args[0])

    file.withReader { reader ->
        while (line = reader.readLine()) {
            content = line.toURL().getBytes()
            def outputFilename = line.substring(line.lastIndexOf("/") + 1)
            println("Saving: " + outputFilename + "...")
            new File(outputFilename).setBytes(content)
        }
    }
}