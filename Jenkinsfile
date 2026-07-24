pipeline {
    agent any

    tools {
    maven 'maven 3.9.10'
    jdk 'JAVA JDK 17'
}

    stages {
        stage('clean') {
            steps {
                echo 'Start Clean'
                bat 'mvn clean'
            }
        }

        stage('test') {
            steps {
                echo 'Start Test'
                bat 'mvn test'
            }
        }

        stage('build') {
            steps {
                echo 'Start Build'
                bat 'mvn install -DskipTests'
            }
        }

        stage('scan') {
            steps {
                echo 'Start scan'

                withSonarQubeEnv('SonarQube') {
                    bat 'mvn sonar:sonar'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully'
        }

        failure {
            echo 'Pipeline failed. Check the failed stage logs.'
        }
    }
}