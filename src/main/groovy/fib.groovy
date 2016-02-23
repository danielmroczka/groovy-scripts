#!/usr/bin/groovy

long fib(long i) {
   i <= 2 ? 1 : fib(i - 2) + fib(i - 1)
}

if (args.length < 1) {
    println "Usage: groovy fib <number>"
} else {
    time = System.currentTimeMillis()
    println fib(Long.parseLong(this.args[0]))
    println "Calculated in " + (System.currentTimeMillis() - time) + " ms"
}
