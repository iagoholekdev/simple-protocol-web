pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // This step checks out the code from your version control system
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // This step uses Gradle to build your project
                script {
                    def gradleHome = tool 'Gradle'
                    def gradleCMD = "${gradleHome}/bin/gradle"

                    sh "${gradleCMD} clean build"
                }
            }
        }

        stage('Test') {
            steps {
                // This step runs your tests
                script {
                    def gradleHome = tool 'Gradle'
                    def gradleCMD = "${gradleHome}/bin/gradle"

                    sh "${gradleCMD} test"
                }
            }
        }

        stage('Deploy') {
            steps {
                // This stage can include steps for deploying your application
                echo 'Deploying...'
                // Add deployment steps here as needed
            }
        }
    }

    post {
        success {
            // This block runs if the pipeline is successful
            echo 'Build successful!'

            // You can add further notifications or steps here
        }

        failure {
            // This block runs if the pipeline fails
            echo 'Build failed!'

            // You can add further notifications or steps here
        }
    }
}
