pipeline {
  agent {
    docker { image 'maven:3.9.6-eclipse-temurin-17' }
  }

  stages {
    stage('Checkout') {
      steps {
        git 'https://github.com/abdoAswa/Highbase.git'
      }
    }

    stage('Build') {
      steps {
        sh 'mvn clean compile -DskipTests'
      }
    }

    stage('Run Tests') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Archive Reports') {
      steps {
        // If using Surefire TestNG reports:
        junit '**/target/surefire-reports/*.xml'
      }
    }
  }

  post {
    always {
      cleanWs()
    }
  }
}
