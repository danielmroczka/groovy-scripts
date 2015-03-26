def theInfoName = "C:/PrgTools/query.txt"

File theInfoFile = new File(theInfoName)
File result = new File(theInfoName + '_out')
result.delete()
def words

def tableFlag
def whereFlag

theInfoFile.eachLine { line ->

    if (line?.trim().size() == 0) {
        return null

    } else {

        words = line.trim().split("\t: ")
        if (words[0].startsWith('Hibernate:')) {
            if (whereFlag) {
                result << '\n'
            }
            whereFlag = false;
            println('\n-------------------------');


        }
        if (whereFlag) {
            if (words[0] != '(' && words[0] != ')') {
                print words[0] + ' '
                result << words[0] + ' '
            }
        }
        if (words[0].startsWith('where')) {
            tableFlag = false;
            whereFlag = true;
        }

        if (tableFlag) {
            println words[0]
            result << words[0] + ' WHERE '
        }

        if (words[0] == 'from') {
            tableFlag = true;
        }

    }
}
 
