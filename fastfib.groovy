class Fibber {
    def old = 1, fib = 1, current = 1

    def next() {
        def newFib = fib + old
        old = fib
        fib = newFib
        current++
    }
}

def fib(n) {
    def fibber = new Fibber();
    while (fibber.current < n) fibber.next()
    return fibber.fib
}

println fib(Long.parseLong(args[0]));

