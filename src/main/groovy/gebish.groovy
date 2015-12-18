@Grapes(
        @Grab(group = 'org.codehaus.geb', module = 'geb-spock', version = '0.7.2')
)
import geb.Browser
@Grapes(
        @Grab(group = 'org.codehaus.geb', module = 'geb-spock', version = '0.7.2')
)
import geb.Browser

Browser.drive {
    go "http://myapp.com/login"

    assert $("h1").text() == "Please Login"

    $("form.login").with {
        username = "admin"
        password = "password"
        login().click()
    }

    assert $("h1").text() == "Admin Section"
}