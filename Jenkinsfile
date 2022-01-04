pipeline {
    agent any

    environment {
        SAMPLE_URL = "google.com"
        SSH = credentials("CENTOS-SSH-CRED")
    }

    stages {
        stage('One') {
            steps {
                echo 'Hello World'
                echo SAMPLE_URL
                sh "echo ${SSH} | base64"
            }
        }

        stage('Two') {
            steps {
                echo 'Hello World'
            }
        }
    }
}