// Get duration between two locations

def slurper = new groovy.json.JsonSlurper()
def parsed = slurper.parseText('https://maps.googleapis.com/maps/api/directions/json?origin=Piekary+Liszki&destination=Krakow+Aleja+3+Maja&sensor=false'.toURL().text)

println parsed.routes.legs.duration