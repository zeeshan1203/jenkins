pipeline {
    agent any

    environment {
        SAMPLE_URL = "google.com"
        SSH = credentials("CENTOS-SSH-CRED")
    }

    options {
        disableConcurrentBuilds()
    }

    tools {
        maven 'MAVEN'
    }

    parameters {
        string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
        text(name: 'BIOGRAPHY', defaultValue: '', description: 'Enter some information about the person')
        booleanParam(name: 'TOGGLE', defaultValue: true, description: 'Toggle this value')
        choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')
        password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
    }

    stages {
        stage('One') {
            steps {
                echo 'Hello World'
                echo SAMPLE_URL
                sh "echo ${SSH} | base64"
                echo PERSON
            }
        }

        stage('Two') {

            steps {
                echo 'Hello World'
            }
        }

        stage('Maven') {
            when {
                beforeInput true
                branch 'production'
            }
            input {
                message "Should we continue?"
                ok "Yes, we should."
                submitter "alice,bob"
                parameters {
                    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
                }
            }
            steps {
                sh "mvn --version"
            }
        }

    }
}
