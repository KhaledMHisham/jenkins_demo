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
                    sh 'mvn clean package spring-boot:run &'
                }
            }
        }
        stage('Wait for Application to Start') {
            steps {
                script {
                    def appStarted = false
                    for (int i = 0; i < 30; i++) {
                        sleep 10
                        if (sh(script: "curl -s http://localhost:7997/actuator/health | grep 'UP'", returnStatus: true) == 0) {
                            appStarted = true
                            break
                        }
                    }
                    if (!appStarted) {
                        error "Spring Boot application did not start in time."
                    }
                }
            }
        }

        stage('Robot Tests') {
            steps {
                script{
                    if (sh(script: 'robot --outputdir results employees.robot', returnStatus: true) != 0) {
                        error "Robot Framework tests failed."
                    }
                }
                step([$class: 'RobotPublisher', outputPath: 'results', reportFileName: 'report.html', logFileName: 'log.html', outputFileName: 'output.xml'])
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