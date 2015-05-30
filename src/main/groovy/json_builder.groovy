import groovy.json.JsonBuilder

def builder = new JsonBuilder()
builder.customer {
    name 'John'
    lastName 'Appleseed'
    address {
        streetName 'Gordon street'
        city 'Philadelphia'
        houseNumber 20
    }
}
println builder.toPrettyString()