def call(String COMPONENT) {
    pipeline {

        agent any

//        triggers {
//            pollSCM('H/2 * * * *')
//        }

        stages {

//      stage('Submit Code to SonarQube') {
//        steps {
//          sh "sonar-scanner -Dsonar.projectKey=${COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://172.31.17.177:9000 -Dsonar.login=5d0bbdf4d611370d0627732bde2157567539a8af"
//        }
//      }
//
//      stage('Quality Gate Status') {
//        steps {
//          sh "sonar-quality-gate.sh admin admin123 172.31.17.177 ${COMPONENT}"
//        }
//      }

            stage('Download Dependencies') {
                steps {
                    script {
                        addShortText background: 'yellow', color: 'black', borderColor: 'yellow', text: "${GIT_BRANCH}"
                    }
                    sh "npm install"
                }
            }

//            stage('Prepare Archive' ) {
//                when { expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true' ]) } }
//                steps {
//                    sh """
//            zip -r ${COMPONENT}-`echo ${GIT_BRANCH}| awk -F / '{print \$NF}'`.zip node_modules server.js
//          """
//                }
//            }
//
//            stage('Upload to Nexus') {
//                when { expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true' ]) } }
//                steps {
//                    sh "curl -f -v -u admin:sami123 --upload-file ${COMPONENT}-`echo ${GIT_BRANCH}| awk -F / '{print \$NF}'`.zip http://172.31.87.229:8081/repository/${COMPONENT}/${COMPONENT}-`echo ${GIT_BRANCH}| awk -F / '{print \$NF}'`.zip"
//                }
//            }

            stage('Trigger Dev Deployment') {
                steps {
                    script {
                        APP_VERSION=sh([returnStdout: true, script: "echo ${GIT_BRANCH}| awk -F / '{print \$NF}'" ])
                        print APP_VERSION
                    }
                    //build job: 'Deployment', parameters: [string(name: 'ENVIRONMENT', value: 'dev'), string(name: 'COMPONENT', value: "${COMPONENT}"), string(name: 'APP_VERSION', value: "")]
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
