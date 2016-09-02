
def call (host, credentials, Closure body){
    System.err.println "I'm in a call"
    dir ("tmp/5") {
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
