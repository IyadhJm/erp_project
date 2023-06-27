pipeline {
    agent any
    stages {
        stage('Checkout GIT') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: 'main']],
                    userRemoteConfigs: [[url: 'https://github.com/IyadhJm/erp_project.git', credentialsId: '5e6bff41-5fe5-41aa-ab64-e80be8dde2e1']]
                ])
            }
        }
         stage('Build Api-gateway ') {
                                                    steps {
                           dir('Aoi-gateway') {
                    sh 'mvn clean compile package'
                    sh 'mvn clean verify sonar:sonar \
                          -Dsonar.projectKey=apiGateway \
                          -Dsonar.host.url=http://localhost:9000 \
                          -Dsonar.login=sqp_69e3742e7d8eb31cdc276c0ad0351be6b61b1bb9'
                    sh 'docker build -t iyadhj/aoi-gateway-1.0.0.jar .'
                    sh 'docker login -u iyadhj -p ijyaamdehi'
                    sh 'mvn org.owasp:dependency-check-maven:check'
                    archiveArtifacts(artifacts: 'target/dependency-check-report.html', fingerprint: true)
                }
            }
        }
        stage('Build GestionBdg microservice') {
            steps {
                dir('GestionBdg') {
                    sh 'mvn clean compile package'
                    sh 'mvn test'
                    sh 'mvn clean verify sonar:sonar \
                          -Dsonar.projectKey=gBdj \
                          -Dsonar.host.url=http://localhost:9000 \
                          -Dsonar.login=sqp_f47171e248df23510cc9b47715f0c3020e2d5aba'
                    sh 'docker build -t iyadhj/gestionbdg-1.0.0.jar .'
                    sh 'docker login -u iyadhj -p ijyaamdehi'
                    sh 'mvn org.owasp:dependency-check-maven:check'
                    archiveArtifacts(artifacts: 'target/dependency-check-report.html', fingerprint: true)
                }
            }
        }
        stage('Build GestionFdp microservice') {
            steps {
                dir('GestionFdp') {
                    sh 'mvn clean compile package'
                    sh 'mvn test'
                    sh 'mvn clean verify sonar:sonar \
                          -Dsonar.projectKey=gFdp \
                          -Dsonar.host.url=http://localhost:9000 \
                          -Dsonar.login=sqp_c88e8450f288ff79940525d8a75dd9e85bfb35c4'
                }
                sh 'docker build -t iyadhj/gestionbdg-1.0.0.jar .'
                sh 'docker login -u iyadhj -p ijyaamdehi'
                sh 'mvn org.owasp:dependency-check-maven:check'
                archiveArtifacts(artifacts: 'target/dependency-check-report.html', fingerprint: true)
            }
        }
    }
}
