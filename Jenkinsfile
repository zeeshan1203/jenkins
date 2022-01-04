pipeline {
    agent any

    environment {
        SAMPLE_URL = "google.com"
    }

    stages {
        stage('One') {
            steps {
                echo 'Hello World'
                echo SAMPLE_URL
            }
        }

        stage('Two') {
            steps {
                echo 'Hello World'
            }
        }
    }
}