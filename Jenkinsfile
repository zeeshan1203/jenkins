pipeline {
    agent any

    environment {
        URL = "google.com"
    }

    stages {
        stage('One') {
            steps {
                echo 'Hello World'
                echo URL
            }
        }

        stage('Two') {
            steps {
                echo 'Hello World'
            }
        }
    }
}
