pipeline {
    agent any

    stages {
        stage('compile-and-test') {
            steps {
                script {
                    sh "apk add maven"
                    sh "mvn -q clean compile"
                    sh "mvn test -DskipITs"
                    sh "mvn -q package -DskipTests"
                }
            }
        }
        stage('integration-test') {
            steps {
                script {
                    sh "mvn -ntp verify -DtestITs"
                }
            }
        }
        stage('build-docker-image') {
            steps {
                script {
                    echo 'Built Docker image'
                }
            }
        }
        stage('deploy-to-dev') {
            steps {
                script {
                    input 'Do you want to proceed with deployment to dev?'
                    echo 'Deployed to dev'
                }
            }
        }
        stage('deploy-to-prod') {
            steps {
                script {
                    input 'Do you want to proceed with deployment to prod?'
                    echo 'Deployed to prod'
                }
            }
        }
    }
}
