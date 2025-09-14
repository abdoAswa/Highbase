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
                // Compile the code, skip tests for this stage
                sh 'mvn clean compile -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                // Run only the tests
                sh 'mvn test'
            }
        }

        stage('Archive Reports') {
            steps {
                // Assuming TestNG or Surefire reports in target folder
                junit '**/target/surefire-reports/*.xml'
                // إذا عندك تقارير TestNG غير Surefire
                // ممكن تستخدم steps للتحويل أو نسخ الملفات المناسبة
            }
        }
    }

    post {
        always {
            // نظّف workspace عشان ما يبقاش فيه ملفات مؤقتة
            cleanWs()
        }
        success {
            echo 'Build & Tests passed!'
        }
        failure {
            echo 'Build or Tests failed — شوف الـ logs'
        }
    }
}
