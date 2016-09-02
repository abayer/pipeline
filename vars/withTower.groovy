import org.apache.commons.lang.RandomStringUtils

def call (host, credentials, Closure body){
    echo "I'm in a call"
    dir ("tmp/${RandomStringUtils.random(9, true, true)}") {
        echo "I'm in a dir"
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "$credentials",
                          usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                              echo "I'm in creds"
            writeFile file: './.tower_cli.cfg',
                    text: "host: $host\nusername: $env.USERNAME \npassword: $env.PASSWORD"
            body()
        }
        deleteDir()
    }
}
