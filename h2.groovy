@GrabConfig(systemClassLoader = true)
@Grab('com.h2database:h2:1.4.186')
import groovy.sql.Sql

def sql = Sql.newInstance("jdbc:h2:./hello", "sa", "sa", "org.h2.Driver")
// DDL

sql.execute("CREATE TABLE IF NOT EXISTS DUMMY (id bigint auto_increment, value text)")

//DML
sql.execute("INSERT INTO DUMMY(value) VALUES(:value)", [value: 'hello'])
println sql.rows("select * from DUMMY")
