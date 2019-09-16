pipeline {
    agent any
    tools { 
        maven 'Maven 3.6.0' 
    }
    environment {
        DISABLE_AUTH = 'true'
    }
    stages {
        stage ('Initialize') {
            steps {
 				sh 'echo "Jenkins, Mavin, JUnit, and Github Demo"'
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
                sh 'mvn --version'
                sh 'printenv'
                sh 'mvn clean compile'
            }
        }
        

        stage ('Testing Stage') {
            steps {
                sh 'mvn test'
            }
        }


//		stage('Sanity check') {
//            steps {
//                input "Does the staging environment look ok?"
//            }
//        }
        
        stage ('Deployment Stage') {
            steps {
                 timeout(time: 3, unit: 'MINUTES') {
                    retry(2) {
                        sh 'mvn deploy'
                    }
                }                  
            }
        }
    }
    
    post {
        always {
            echo 'This will always run'
            deleteDir() /* clean up our workspace */
            //archiveArtifacts artifacts: '**/*.jar', fingerprint: true
            //junit 'build/reports/**/*.xml'   
	    	//junit 'target/surefire-reports/*.xml'       
        }
        success {
            echo 'This will run only if successful'
            mail to: 'jiayin.zhuo@gmail.com',
            subject: "Successful Pipeline: ${currentBuild.fullDisplayName}",
            body: "Everything is fine with ${env.BUILD_URL}"
        }
        failure {
            echo 'This will run only if failed'
            mail to: 'jiayin.zhuo@gmail.com',
            subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
            body: "Something is wrong with ${env.BUILD_URL}"
            
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
