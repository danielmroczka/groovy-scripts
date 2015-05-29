/**
 * Created by daniel on 2015-04-28.
 */

def benchmark = { closure ->
    start = System.nanoTime()
    closure.call()
    now = System.nanoTime()
    println now - start + " [ns]"
}

def duration = benchmark {
    (0..100).inject(0) { sum, item ->
        sum + item
    }
}