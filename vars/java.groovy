def call(String COMPONENT) {
    pipeline {

        agent any

//        triggers {
//            pollSCM('H/2 * * * *')
//        }

        stages {

            stage('Compile Code') {
                steps {
                    script {
                        sh "mvn compile"
                    }
                }
            }

            stage('Submit Code to SonarQube') {
                steps {
                    sh "sonar-scanner -Dsonar.projectKey=${COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://172.31.17.177:9000 -Dsonar.login=5d0bbdf4d611370d0627732bde2157567539a8af"
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
