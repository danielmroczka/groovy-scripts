def benchmark = { closure ->
    time = System.nanoTime()
    closure.call()
    time = System.nanoTime() - time
    println time.div(10e6) + " [ms]"
}

def duration = benchmark {
    (0..1000).inject(0) { sum, item ->
        sum + item
    }
}