def call() {
    pipeline {

        agent any

        triggers {
            pollSCM('H/2 * * * *')
        }

        stages {

            stage('Compile Code') {
                steps {
                    script {
                        sh "mvn compile"
                    }
                }
            }

            stage('Build Package') {
                steps {
                    script {
                        sh "mvn package"
                    }
                }
            }

        } // stages
    } // pipeline
}
