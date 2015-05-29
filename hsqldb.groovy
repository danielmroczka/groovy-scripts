@GrabConfig(systemClassLoader = true)
@Grab('org.hsqldb:hsqldb:2.3.0')
import org.hsqldb.Server
@GrabConfig(systemClassLoader = true)
@Grab('org.hsqldb:hsqldb:2.3.0')
import org.hsqldb.Server

class DBUtil {
    def ddls = [
            '''
CREATE TABLE COOKBOOK(
ID INTEGER PRIMARY KEY,
TITLE VARCHAR(100),
AUTHOR VARCHAR(100),
YEAR INTEGER)
''',
            '''
CREATE TABLE CHAPTER(
ID INTEGER PRIMARY KEY,
BOOK_ID INTEGER,
TITLE VARCHAR(100))
''',
            '''
CREATE TABLE RECIPE(
ID INTEGER PRIMARY KEY,
CHAPTER_ID INTEGER,
TITLE VARCHAR(100),
DESCRIPTION CLOB,
IMAGE BLOB)
''',
            '''
CREATE TABLE INGREDIENT(
ID INTEGER PRIMARY KEY,
RECIPE_ID INTEGER,
NAME VARCHAR(100),
AMOUNT DOUBLE,
UNITS VARCHAR(20))
'''
    ]

    static dbSettings = [
            url   : 'jdbc:hsqldb:hsql://localhost/cookingdb',
            driver: 'org.hsqldb.jdbcDriver',
            user  : 'sa',
            password: ''
    ]

    def startServer() {
        Server server = new Server()
        def logFile = new File('db.log')
        server.setLogWriter(new PrintWriter(logFile))
        server.with {
            setDatabaseName(0, 'cookingdb')
            setDatabasePath(0, 'mem:cookingdb')
            start()
        }
        server
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            println('Usage h2 url sql-script')
            return
        }
        def server = startServer()
        println('hello!')
        server.stop()
    }
}
