pipeline {
    agent any
    
    environment {
        // Use system Java and Maven
        JAVA_HOME = "${env.JAVA_HOME}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from GitHub...'
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building the project...'
                script {
                    if (isUnix()) {
                        sh '/usr/local/bin/mvn clean compile'
                    } else {
                        bat 'mvn clean compile'
                    }
                }
            }
        }
        
        stage('Run Tests') {
            steps {
                echo 'Running Selenium tests...'
                script {
                    if (isUnix()) {
                        sh '/usr/local/bin/mvn test -Dheadless=true'
                    } else {
                        bat 'mvn test -Dheadless=true'
                    }
                }
            }
        }
    }
    
    post {
        always {
            echo 'Cleaning up...'
            // Archive test results
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
            
            // Archive screenshots if they exist
            archiveArtifacts artifacts: 'screenshots/**/*.png', allowEmptyArchive: true
            
            // Clean workspace
            cleanWs()
        }
        
        success {
            echo 'Tests passed successfully!'
        }
        
        failure {
            echo 'Tests failed!'
            // Send notifications here if needed
        }
    }
}
