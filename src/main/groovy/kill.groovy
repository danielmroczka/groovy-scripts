/**
 Script kills process which listen to port provided as a params
 Usage: groovy kill.groovy 80 8080 8090
 **/

for (String port : args) {
    def p = "netstat -ano".execute() | "find \":${port} \"".execute() | "find \"LISTENING\"".execute()
    p.waitFor()
    def lines = p.text
    println lines
    lines = lines.split("\n")

    def pids = [] as Set

    for (String line : lines) {
        def x = line.trim().split("\\s+")
        if (x.size() > 2 && x[1].endsWith(":" + port)) {
            def pid = x[x.size() - 1].trim()
            if (!pids.contains(pid)) {
                def killproc = "taskkill /F /pid ${pid}".execute()
                killproc.waitFor()
                pids << pid
                println "Killed process: ${pid}"
            }
        }
    }

    println "Killed ${pids.size()} processes"
}