pipeline {
    agent any
    tools { 
        maven 'Maven 3.6.0' 
    }
    stages {
        stage ('Initialize') {
            steps {
 				sh 'echo "Hello World"'
                sh '''
                    echo "Multiline shell steps works too"
                    ls -lah
                '''               
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }

        stage ('Build Stage') {
            steps {
                echo 'mvn --version'
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
                 timeout(time: 3, unit: 'MINUTES') {
                    retry(2) {
                        echo 'mvn deploy'
                        sh 'mvn deploy'
                    }
                }                  
            }
        }
    }
    
    post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }
}
