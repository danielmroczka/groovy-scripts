@Grab(group = 'org.apache.commons', module = 'commons-email', version = '1.3.2')
import org.apache.commons.mail.HtmlEmail
@Grab(group = 'org.apache.commons', module = 'commons-email', version = '1.3.2')
import org.apache.commons.mail.HtmlEmail
@Grab(group = 'org.apache.commons', module = 'commons-email', version = '1.3.2')
import org.apache.commons.mail.HtmlEmail

def email = new HtmlEmail();
email.setHostName("smtp.gmail.com");
email.setSmtpPort(465);
email.setSSLOnConnect(true);

def console = System.console()
def smtpUser = console.readLine 'Please enter SMTP user: '
def password = console.readPassword('Please enter password for: ' + smtpUser + ': ').toString()
def to = console.readLine 'Recipient to (email address): '
def toName = System.console().readLine 'Recipient to (full name): '
email.setAuthentication(smtpUser, password);
email.addTo(to, toName);

email.setFrom(smtpUser);
email.setSubject("Test");

email.setHtmlMsg("<html></html>");
println "Sending email to: " + email.getToAddresses() + " ..."
String id = email.send();
println "Email sent. ID=" + id
