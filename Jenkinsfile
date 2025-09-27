pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK11'
    }

    stages {
        stage('Initialize') {
            steps {
                echo 'Starting the CI/CD pipeline for BMI Calculator Lambda...'
            }
        }
        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            // Archive test results
            junit '**/target/surefire-reports/*.xml'
            
            // Archive the built JAR
            archiveArtifacts artifacts: 'target/bmi-calculator-lambda-*.jar', fingerprint: true
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed! Check the logs for details.'
        }
    }
}
// In Maven, there are several important commands (lifecycle phases) that you can use in your Jenkins pipeline. Here are the key Maven commands:

// mvn clean - Cleans the project by deleting the target directory and build artifacts
// mvn compile - Compiles the source code
// mvn test - Runs unit tests
// mvn package - Packages the compiled code into a distributable format (like JAR)
// mvn install - Installs the package into the local repository
// mvn deploy - Copies the package to the remote repository
// mvn verify - Runs checks to verify the package is valid
// mvn validate - Validates the project is correct and all necessary information is available
// You can also combine these commands:

// mvn clean install - Cleans and then installs the package
// mvn clean package - Cleans and then packages (what your pipeline currently uses)
// mvn clean test - Cleans and runs tests
// Additional useful options:

// -DskipTests - Skips test execution (like in your build stage)
// -B or --batch-mode - Runs Maven in non-interactive mode (good for CI/CD)
// -X or --debug - Produces debug output
// -U - Forces update of dependencies