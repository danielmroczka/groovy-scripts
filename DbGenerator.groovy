@Grab('mysql:mysql-connector-java:5.1.34') @GrabConfig(systemClassLoader = true)

import groovy.sql.Sql

def rand = new Random()
def sql = Sql.newInstance('jdbc:mysql://192.168.1.110:3306/foo', 'root', '', 'com.mysql.jdbc.Driver')
def firstnames = ['James', 'John', 'Robert', 'Michael', 'William', 'David', 'Richard', 'Joseph', 'Charles', 'Thomas', 'Christopher', 'Daniel', 'Matthew', 'Donald', 'Anthony', 'Paul', 'Mark', 'George', 'Steven', 'Kenneth',
        'Mary', 'Patricia', 'Jennifer', 'Elizabeth', 'Linda', 'Barbara', 'Susan', 'Margaret', 'Jessica', 'Dorothy', 'Sarah', 'Karen', 'Nancy', 'Betty', 'Lisa', 'Sandra', 'Helen', 'Ashley', 'Donna', 'Kimberly']
def lastnames = ['Smith', 'Johnson', 'Williams', 'Brown', 'Jones', 'Miller', 'Davis', 'Garcia', 'Rodriguez', 'Wilson', 'Moore', 'Lewis', 'Harris']
def cities = ['Berlin', 'London', 'Dublin', 'Warsaw', 'Amsterdam', 'Vienna']

//sql.execute 'delete from person'
println 'Deleted all items from Person'

30.times {
    sql.withBatch(1000, 'insert into person (firstname, lastname, phone, city, status) values (?, ?, ?, ?, ?)') { ps ->
        1000.times {
            def firstname = firstnames[rand.nextInt(firstnames.size)]
            def lastname = lastnames[rand.nextInt(lastnames.size)]
            def city = cities[rand.nextInt(cities.size)]
            def phone = rand.nextInt(8999999) + 1000000;
            def status = rand.nextInt(100) < 99 ? 'A' : null
            ps.addBatch(firstname, lastname, phone, city, status)
        }

    }

    println 'inserted 1000 items...'
}

println 'Insert completed'

