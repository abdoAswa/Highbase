pipeline {
    agent any
    
    tools {
        // Use the JDK you configured earlier
        jdk 'JDK-17' // Change this to match your JDK name
    }
    
    environment {
        // Set Maven path if not in system PATH
        MAVEN_HOME = tool 'Maven' // Configure Maven in Global Tools first
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
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
                        sh 'mvn clean compile'
                    } else {
                        bat 'mvn clean compile'
                    }
                }
            }
        }
        
        stage('Setup Browser') {
            steps {
                echo 'Setting up browser drivers...'
                script {
                    if (isUnix()) {
                        // Download ChromeDriver for Linux
                        sh '''
                            wget -O chromedriver.zip https://chromedriver.storage.googleapis.com/114.0.5735.90/chromedriver_linux64.zip
                            unzip chromedriver.zip
                            chmod +x chromedriver
                            sudo mv chromedriver /usr/local/bin/
                        '''
                    } else {
                        // For Windows - adjust path as needed
                        bat '''
                            curl -o chromedriver.zip https://chromedriver.storage.googleapis.com/114.0.5735.90/chromedriver_win32.zip
                            powershell Expand-Archive chromedriver.zip -DestinationPath .
                        '''
                    }
                }
            }
        }
        
        stage('Run Tests') {
            steps {
                echo 'Running Selenium tests...'
                script {
                    if (isUnix()) {
                        sh 'mvn test -Dheadless=true'
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
            publishTestResults testResultsPattern: 'target/surefire-reports/*.xml'
            
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
