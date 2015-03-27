@Grab(group='org.apache.commons', module='commons-email', version='1.3.2')

/*
    Collects IP Address and sends it by the email
    
    settings.cfg file format:
    user=
    password=
    to=
*/
import org.apache.commons.mail.HtmlEmail;
import java.net.*;
import java.io.*;

file = 'settings.cfg'

if (args.length == 1) {
    file = args[0]
}

Properties properties = new Properties()
File propertiesFile = new File(file)
propertiesFile.withInputStream {
    properties.load(it)
}

def email = new HtmlEmail();
email.setHostName("smtp.gmail.com");
email.setSmtpPort(465);
email.setSSLOnConnect(true);

def smtpUser = properties.user
def password = properties.password
def to = properties.to
def toName = properties.to

email.setAuthentication(smtpUser, password);
email.addTo(to, toName);
email.setFrom(smtpUser);
email.setSubject("Test");

InetAddress thisIp = InetAddress.getLocalHost();
thisIpAddress = thisIp.getHostAddress().toString();

def ips = ""
NetworkInterface.getNetworkInterfaces().each { iface ->
    iface.inetAddresses.each { addr -> 
    if(addr.hostAddress.contains(".")) {
        ips += addr.hostAddress
        ips += " | "
        }
    }
}


URL whatismyip = new URL("http://checkip.amazonaws.com");
BufferedReader i = new BufferedReader(new InputStreamReader(whatismyip.openStream()));

String ip = i.readLine();

def ctx = sprintf( 'Network IP Address: %s<br/>External IP Address: %s', [ips, ip])

email.setHtmlMsg("<html>" + ctx + "</html>");

println "Sending email to: " + email.getToAddresses() + " ..."
String id = email.send();
println "Email sent. ID=" + id