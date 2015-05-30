import java.util.jar.JarFile

def folder = "C:\\dev\\as\\apache-tomee-jaxrs-1.7.1\\lib";
def baseDir = new File(folder);
files = baseDir.listFiles();
for (f in files) {
    print "${f.name}, "
    new JarFile(f).manifest.mainAttributes.entrySet().each
            {
                if (it.key =~ /Implementation-Version/) {
                    print it.value
                }

            }
    println ''

}

