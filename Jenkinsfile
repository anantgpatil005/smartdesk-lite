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
            steps {
                bat 'docker --version'
                bat 'docker ps'
            }
        }

        stage('Push Docker Images') {
    steps {
        withCredentials([
            usernamePassword(
                credentialsId: 'dockerhub-credentials',
                usernameVariable: 'DOCKER_USERNAME',
                passwordVariable: 'DOCKER_PASSWORD'
            )
        ]) {

            bat '''
                powershell -NoProfile -Command "$env:DOCKER_PASSWORD | docker login --username $env:DOCKER_USERNAME --password-stdin"
            '''

            bat 'docker tag smartdesk-ticket-service:latest %DOCKER_USERNAME%/smartdesk-ticket-service:latest'
            bat 'docker tag smartdesk-notification-service:latest %DOCKER_USERNAME%/smartdesk-notification-service:latest'
            bat 'docker tag smartdesk-frontend:latest %DOCKER_USERNAME%/smartdesk-frontend:latest'

            bat 'docker push %DOCKER_USERNAME%/smartdesk-ticket-service:latest'
            bat 'docker push %DOCKER_USERNAME%/smartdesk-notification-service:latest'
            bat 'docker push %DOCKER_USERNAME%/smartdesk-frontend:latest'
        }
    }
}
    }
}