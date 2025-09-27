pipeline {
    agent any

    environment {
        AWS_REGION = 'us-east-1'  // Change this to your AWS region
        LAMBDA_FUNCTION_NAME = 'bmi-calculator'
        JAVA_HOME = tool 'JDK11'  // Make sure you have JDK 11 configured in Jenkins
        AWS_CREDENTIALS = 'aws-credentials'  // Configure these credentials in Jenkins
    }

    tools {
        maven 'Maven3'  // Make sure you have Maven configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Code Quality') {
            steps {
                sh 'mvn verify sonar:sonar'
            }
        }

        stage('Deploy to AWS Lambda') {
            environment {
                AWS_ACCESS_KEY_ID = credentials("${AWS_CREDENTIALS}_ACCESS_KEY_ID")
                AWS_SECRET_ACCESS_KEY = credentials("${AWS_CREDENTIALS}_SECRET_ACCESS_KEY")
            }
            steps {
                script {
                    // Update Lambda function code
                    sh """
                        aws lambda update-function-code \
                            --region ${AWS_REGION} \
                            --function-name ${LAMBDA_FUNCTION_NAME} \
                            --zip-file fileb://target/bmi-calculator-lambda-1.0-SNAPSHOT.jar
                    """
                }
            }
        }
    }

    post {
        always {
            // Clean workspace
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed! Check the logs for details.'
        }
    }
}