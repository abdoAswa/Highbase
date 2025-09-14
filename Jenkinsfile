pipeline {
  agent {
    docker {
      image 'maven:3.9.6-eclipse-temurin-17'
      args '-v /dev/shm:/dev/shm' // يحسن أداء المتصفح جوه docker
    }
  }

  environment {
    MAVEN_OPTS = "-Dmaven.test.failure.ignore=true"
  }

  stages {
    stage('Build') {
      steps {
        sh 'mvn clean compile -DskipTests'
      }
    }

    stage('Run Selenium Tests') {
      steps {
        // تشغيل Selenium مع Chrome headless
        sh '''
          echo "Starting Selenium Tests in Headless Chrome..."
          mvn test -Dselenium.browser=chrome -Dheadless=true
        '''
      }
    }

    stage('Archive Test Reports') {
      steps {
        junit '**/target/surefire-reports/*.xml'
      }
    }
  }

  post {
    always {
      echo 'Cleaning up workspace...'
      cleanWs()
    }
    success {
      echo '✅ All tests passed successfully!'
    }
    failure {
      echo '❌ Some tests failed. Check the reports.'
    }
  }
}
