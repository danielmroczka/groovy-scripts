/**
 * Creates orderd list with integers seperated by comma. Useful in select * from table in (1,2,...,n)
 */

def start = 1
def count = 10000
def prefix = ''
def suffix = ''

print prefix

for (i in start..start + count - 1) {
    print i + ","
}

print start + count + suffix
