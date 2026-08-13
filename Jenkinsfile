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
		
		stage('Docker Build Ticket Service') {
			steps {
				bat 'cd ticket-service && docker build -t smartdesk-ticket-service:latest .'
			}
		}
		
		stage('Build Notification Service') {
            steps {
                bat 'cd notification-service && mvn clean package'
            }
        }
		stage('Docker Build Notification Service') {
			steps {
				bat 'cd notification-service && docker build -t smartdesk-notification-service:latest .'
			}
		}
		
		stage('Build Frontend') {
            steps {
                bat 'cd frontend && npm install && npm run build'
            }
        }
		
		stage('Docker Build Frontend') {
			steps {
				bat 'cd frontend && docker build -t smartdesk-frontend:latest .'
			}
		}
		
		stage('Verify Docker') {
            steps {
                bat 'docker --version'
                bat 'docker ps'
            }
        }

    }
}