pipeline {
    agent any

    environment {
        ACR_NAME = 'taskmanageracr'
        ACR_LOGIN_SERVER = 'taskmanageracr.azurecr.io'
        IMAGE_FRONTEND = 'taskmanager-frontend'
        IMAGE_BACKEND  = 'taskmanager-backend'
    }

    stages {

        stage('Clone Repository') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/psrividhya01/Task-Manager.git'
            }
        }

        stage('Build Backend Image') {
            steps {
                sh '''
                    docker build -t $ACR_LOGIN_SERVER/$IMAGE_BACKEND:latest ./Backend/TaskManager
                '''
            }
        }

        stage('Build Frontend Image') {
            steps {
                sh '''
                    docker build -t $ACR_LOGIN_SERVER/$IMAGE_FRONTEND:latest ./Frontend
                '''
            }
        }

        stage('Login to Azure ACR') {
            steps {
                sh '''
                    az acr login --name $ACR_NAME
                '''
            }
        }

        stage('Push Images to ACR') {
            steps {
                sh '''
                    docker push $ACR_LOGIN_SERVER/$IMAGE_BACKEND:latest
                    docker push $ACR_LOGIN_SERVER/$IMAGE_FRONTEND:latest
                '''
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                sh '''
                    docker-compose down
                    docker-compose pull
                    docker-compose up -d
                '''
            }
        }
    }

    post {
        success {
            echo '✅ Deployment Successful!'
        }
        failure {
            echo '❌ Deployment Failed! Check logs.'
        }
    }
}