pipeline {
    agent {
        label 'ubuntu-1604'
    }

    tools { 
        maven 'M3' 
        jdk 'jdk8'
    }
    environment {
        DISABLE_AUTH = 'true'
    }
    stages {
        stage ('Initialize') {
            steps {
 				sh 'echo "Jenkins, Github, JUnit Maven and MavenRepo Demo"'
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

        stage ('Build Stage Uses Maven') {
            steps {
                sh 'mvn --version'
                sh 'printenv'
                sh 'mvn clean compile'
            }
        }
        

        stage ('Testing Stage Uses JUnit') {
            steps {
                sh 'mvn test'
            }
        }


//		stage('Sanity check') {
//            steps {
//                input "Does the staging environment look ok?"
//            }
//        }
        
        stage ('Deployment Stage Uses Maven Repo') {
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
            archiveArtifacts artifacts: '**/*.jar', fingerprint: true
	    	junit 'target/surefire-reports/*.xml'       
            deleteDir() /* clean up our workspace */
        }
        success {
            echo 'This will run only if successful'
            mail to: 'jonathanzhuo.demo@gmail.com',
            subject: "Successful Pipeline: ${currentBuild.fullDisplayName}",
            body: "Everything is fine with ${env.BUILD_URL}"
        }
        failure {
            echo 'This will run only if failed'
            mail to: 'jonathanzhuo.demo@gmail.com',
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
