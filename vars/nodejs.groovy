def call() {
    pipeline {

        agent any

        triggers {
            pollSCM('H/2 * * * *')
        }

        stages {

            stage('Download Dependencies') {
                steps {
                    script {
                        sh "npm install"
                    }
                }
            }

        } // stages
    } // pipeline
}
