def call(String COMPONENT) {
    pipeline {

        agent any

//        triggers {
//            pollSCM('H/2 * * * *')
//        }

        stages {

//            stage('Submit Code to SonarQube') {
//                steps {
//                    sh "sonar-scanner -Dsonar.projectKey=${COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://172.31.17.177:9000 -Dsonar.login=5d0bbdf4d611370d0627732bde2157567539a8af"
//                }
//            }
//
//            stage('Quality Gate Status') {
//                steps {
//                    sh "sonar-quality-gate.sh admin admin123 172.31.17.177 ${COMPONENT}"
//                }
//            }

            stage('Download Dependencies') {
                steps {
                    script {
                        sh "npm install"
                    }
                }
            }

            stage('Prepare Archive' ) {
                steps {
                    sh """
            ls
            zip -r ${COMPONENT}.zip node_modules server.js 
          """
                }
            }

        } // stages

        post {
            always {
                cleanWs()
            }
        }

    } // pipeline
}
