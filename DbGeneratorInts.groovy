@Grab('mysql:mysql-connector-java:5.1.34')
@GrabConfig(systemClassLoader = true)
//@Grab(group='com.h2database', module='h2', version='1.4.185')
import groovy.sql.Sql

def sql = Sql.newInstance('jdbc:mysql://localhost:3306/foo', 'root', 'root', 'com.mysql.jdbc.Driver')

println 'Started script'

1000000.times {
    sql.withBatch(1000000, 'insert into test2 (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)') { ps ->
        int i = it + 1
        ps.addBatch(i, i / 4, i / 16, i / 64, i / 64, i / 256, i / 1000, i / 4000, i / 16000, i / 64000)
        if (it % 10000 == 0) {
            println 'inserted ' + it + ' record'
        }
    }
}

println 'Insert completed'