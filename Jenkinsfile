
pipeline {
    agent any
    options {
        timestamps()
    }
    stages {
        stage("SCM"){
            steps {
                cleanWs()
                script {
                    dir('src'){                    
                        sh 'git clone https://github.com/jgarciam/ejemplo-maven.git/ .'
                    }
                }
            }
        }    
        stage("Compile"){
            steps {
                script {
                    dir('src'){
                        sh './mvnw clean compile -e'
                    }
                }
            }
        }
        stage("Test"){
            steps {
                script {
                    dir('src'){
                        sh './mvnw clean test -e'
                    }
                }
            }
        }
        stage('SonarQube analysis') {
            def scannerHome = tool 'SonarScanner 4.0';
            withSonarQubeEnv('My SonarQube Server') {  
                sh "${scannerHome}/bin/sonar-scanner"
            }
        }
        stage("Jar"){
            steps {
                script {
                    dir('src'){
                        sh './mvnw clean package -e'
                    }
                }
            }
        }    
        stage("Run"){
            steps {
                script {
                    dir('src'){
                        sh 'nohup bash mvnw spring-boot:run &'
                        sleep(30)
                    }
                }
            }
        }
        stage("Curl API"){
            steps {
                script {
                    sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
                }
            }
        }
    }
}
