@Grab(group = 'com.jcraft', module = 'jsch', version = '0.1.52')
@Grab(group = 'org.apache.ant', module = 'ant-jsch', version = '1.9.4')

def ant = new AntBuilder()
ant.scp(file: 'user@host:/tmp/file.txt',
        todir: "/home/localuser/",
        verbose: true,
        keyfile: "/home/localuser/.ssh/id_dsa",
        passphrase: "password")
