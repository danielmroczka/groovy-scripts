import groovy.io.FileType

import java.util.jar.JarFile

public static void main(String[] args) {
    if (args.length == 0) {
        list('.')
    } else {
        list(args[0])
    }
}

def list(String root) {
    def list = []

    new File(root).eachFileRecurse(FileType.FILES) { file ->
        if (file.name.endsWith('.jar') || file.name.endsWith('.war')) {
            list << file
        }
    }

    list.each {
        println 'File: \t' + it.path
        println ''
        def attr = new JarFile(it.path).manifest.mainAttributes
        println attr
        println '-----------------------'
    }
}

