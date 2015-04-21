@GrabConfig(systemClassLoader = true)
@Grab('com.h2database:h2:1.4.186')
import groovy.sql.Sql

def sql = Sql.newInstance("jdbc:h2:./hello", "sa", "sa", "org.h2.Driver")
// DDL
sql.execute("CREATE TABLE IF NOT EXISTS FOO (id int, value text)")

//DML
sql.execute("INSERT INTO FOO VALUES(:id, :value)", [id: 1, value: 'hello'])
println sql.rows("select * from foo")
