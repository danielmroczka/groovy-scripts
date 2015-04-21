import groovy.time.TimeCategory

import static java.util.Calendar.*

def now = new GregorianCalendar()
def from = new GregorianCalendar(2014, JUNE, 29)

use(TimeCategory) {
def duration = now - from
    print "Days: ${duration}, Weeks: ${duration/7}, etc."

}
