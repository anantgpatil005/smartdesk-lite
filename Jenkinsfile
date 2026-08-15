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
				withCredentials([
            usernamePassword(
                credentialsId: 'dockerhub-credentials',
                usernameVariable: 'DOCKER_USERNAME',
                passwordVariable: 'DOCKER_PASSWORD'
            )
        ]) {
             bat '''
            echo ==========================
            echo WINDOWS USER
            echo ==========================
            whoami

            echo.
            echo ==========================
            echo DOCKER EXECUTABLE
            echo ==========================
            where docker
            docker --version

            echo.
            echo ==========================
            echo DOCKER CONTEXT
            echo ==========================
            docker context show

            echo.
            echo ==========================
            echo DOCKER CONFIG
            echo ==========================
            echo DOCKER_CONFIG=[%DOCKER_CONFIG%]

            echo.
            echo ==========================
            echo DOCKER INFO
            echo ==========================
            docker info
        '''
        }
            }
        }

      
    }
}