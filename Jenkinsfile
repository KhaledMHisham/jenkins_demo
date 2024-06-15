pipeline {
    agent any

    tools {
        maven 'Maven' // This should match the name of the Maven installation configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the repository
                git branch: 'main', url: 'https://github.com/KhaledMHisham/jenkins_demo.git'
            }
        }

        stage('Build and Test') {
            steps {
                // Build the project and run tests using Maven
                withMaven(maven: 'Maven') {
                    sh 'mvn clean install'
                }
            }
        }
    }

    post {
        always {
            // Archive artifacts and send notification regardless of build outcome
            archiveArtifacts artifacts: '**/target/*', allowEmptyArchive: true
        }
        failure {
            echo 'Build failed. Check logs and test reports for details.'
        }
    }
}