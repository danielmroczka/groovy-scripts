@Grab(group = 'org.apache.commons', module = 'commons-email', version = '1.3.2')
import org.apache.commons.mail.HtmlEmail

/*
    Collects IP Address and sends it by the email

    settings.cfg file format:
    user=
    password=
    to=

    Basic configuration on Linux:

    add the groovy path_to_groovy_script path_to_settings into /etc/rc.local file
*/

import org.apache.commons.mail.HtmlEmail

def file

if (args.length == 1) {
    file = args[0]
    println "Using cfg file: " + file + "..."
} else {
    println "Usage: IpNotifier.groovy settings.cfg"
    return
}

Properties props = new Properties()
File propertiesFile = new File(file)
propertiesFile.withInputStream {
    props.load(it)
}



InetAddress thisIp = InetAddress.getLocalHost();
thisIpAddress = thisIp.getHostAddress().toString();

def ips = ""
NetworkInterface.getNetworkInterfaces().each { iface ->
    iface.inetAddresses.each { addr ->
        if (addr.hostAddress.contains(".") && addr.hostAddress != "127.0.0.1") {
            ips += addr.hostAddress
            ips += " | "
        }
    }
}

ips = ips.trim()

if (ips.endsWith("|")) {
    ips = ips.substring(0, ips.length() - 1)
}

URL whatismyip = new URL("http://checkip.amazonaws.com");
BufferedReader i = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
String ip = i.readLine();

def ctx = sprintf('Raspberry Pi has been rebooted. Current time: %s<br/>Network IP Address: %s<br/>External IP Address: %s', [new Date(), ips, ip])
def email = new HtmlEmail()

email.setHostName("smtp.gmail.com")
email.setSmtpPort(465)
email.setSSLOnConnect(true)
email.setAuthentication(props.user, props.password)
email.addTo(props.to, props.toName)
email.setFrom(props.user)
email.setSubject("Raspberry Pi restarted. Available on " + ips)
email.setHtmlMsg("<html>" + ctx + "</html>")

println "Sending email to: " + email.getToAddresses() + " ..."
String id = email.send();
println "Email sent. ID=" + id