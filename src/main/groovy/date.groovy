import groovy.time.TimeCategory

import static java.util.Calendar.JUNE

def now = new GregorianCalendar()
def from = new GregorianCalendar(2014, JUNE, 29, 11, 30)

use(TimeCategory) {
    def duration = now - from
    println "Days: ${duration}"
    println "Weeks: ${(int) duration / 7} and ${duration - 7 * ((int) duration / 7)} days"
    println "Hours: ${duration * 24} etc."

}
