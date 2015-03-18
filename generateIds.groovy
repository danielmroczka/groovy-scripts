// Creates orderd list list of int seperated by comma. Useful in select * from table in (1,2,...,n)
def start = 25834436
def count = 10000

for (i in start..start+count-1) print i+","
print start+count
