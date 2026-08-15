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
                dir('common') {
                    bat 'mvn clean install'
                }
            }
        }

        stage('Build Ticket Service') {
            steps {
                dir('ticket-service') {
                    bat 'mvn clean package'
                }
            }
        }

        stage('Docker Build Ticket Service') {
            steps {
                dir('ticket-service') {
                    bat 'docker build -t smartdesk-ticket-service:latest .'
                }
            }
        }

        stage('Build Notification Service') {
            steps {
                dir('notification-service') {
                    bat 'mvn clean package'
                }
            }
        }

        stage('Docker Build Notification Service') {
            steps {
                dir('notification-service') {
                    bat 'docker build -t smartdesk-notification-service:latest .'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    bat 'npm install && npm run build'
                }
            }
        }

        stage('Docker Build Frontend') {
            steps {
                dir('frontend') {
                    bat 'docker build -t smartdesk-frontend:latest .'
                }
            }
        }

        stage('Verify Docker') {
            stage('DEBUG - Jenkins Environment') {
    steps {
        withCredentials([
            usernamePassword(
                credentialsId: 'dockerhub-credentials',
                usernameVariable: 'DOCKER_USERNAME',
                passwordVariable: 'DOCKER_PASSWORD'
            )
        ]) {
            bat '''
                echo ==========================
                echo DOCKER LOGIN
                echo ==========================

                powershell -NoProfile -Command "$env:DOCKER_PASSWORD | docker login --username $env:DOCKER_USERNAME --password-stdin"
            '''
        }
    }
}
        }

      
    }
}