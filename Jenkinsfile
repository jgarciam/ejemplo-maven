
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
                        sh 'git checkout -b feature-sonar'
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
        stage("SonarQube analysis") {
            steps {
                script {
                    dir('src'){
                        def scannerHome = tool 'SonarScanner';
                        withSonarQubeEnv('My SonarQube Server') {  
                            sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-maven -Dsonar.java.binaries=build/classes"
                        }
                    }
                }
            }
        }

        stage("Upload Nexus") {
            steps {
                script {
                    dir('src'){
                        nexusArtifactUploader artifacts: [[artifactId: 'DevOpsUsach2020', classifier: '', file: 'target/DevOpsUsach2020-0.0.1.jar', type: 'jar']], credentialsId: 'nexus', groupId: 'com.devopsusach2020', nexusUrl: 'nexus:8081', nexusVersion: 'nexus2', protocol: 'http', repository: 'test-nexus', version: '0.0.1'
                    }
                }
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
