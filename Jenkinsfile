pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }
		
		stage('Build Common') {
            steps {
                bat 'cd common && mvn clean install'
            }
        }
		
		stage('Build Ticket Service') {
            steps {
                bat 'cd ticket-service && mvn clean package'
            }
        }
		
		stage('Build Notification Service') {
            steps {
                bat 'cd notification-service && mvn clean package'
            }
        }
		
		stage('Build Frontend') {
            steps {
                bat 'cd frontend && npm install && npm run build'
            }
        }

    }
}