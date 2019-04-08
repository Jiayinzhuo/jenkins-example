pipeline {
    agent any
    tools { 
        maven 'Maven 3.6.0' 
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }

        stage ('Build Stage') {
            steps {
                echo 'mvn clean compile'
                sh 'mvn --version'
                sh 'mvn clean compile'
            }
        }
        

        stage ('Testing Stage') {
            steps {
                echo 'mvn test' 
                sh 'mvn test'
            }
        }


        stage ('Deployment Stage') {
            steps {
                echo 'mvn deploy'
                sh 'mvn deploy'
            }
        }
    }
}
