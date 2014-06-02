// 

long fib(long i) {
   i <= 2 ? 1 : fib(i - 2) + fib(i - 1);
}

if (this.args.length < 1) {
    println "Usage: groovy fib <number>";
} else {
    println fib(Long.parseLong(this.args[0]));
}
