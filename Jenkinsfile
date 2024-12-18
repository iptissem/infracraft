pipeline {
    agent any

    parameters {
        choice(
            name: 'SELECT_DOCKER_SERVICE',
            choices: ['Web Server (Nginx)', 'Database Server (MySQL)', 'Cache Server (Redis)', 'Monitoring Server (Prometheus)', 'All'],
            description: 'Choisissez le service Docker à déployer'
        )
    }

    stages {
        stage('Sélection des Services Docker') {
            steps {
                echo "Vous avez sélectionné : ${params.SELECT_DOCKER_SERVICE}"
            }
        }

        stage('Test Sélection Docker') {
            steps {
                script {
                    switch (params.SELECT_DOCKER_SERVICE) {
                        case 'Web Server (Nginx)':
                            echo 'Simulation : Déploiement du container Docker Nginx...'
                            break
                        case 'Database Server (MySQL)':
                            echo 'Simulation : Déploiement du container Docker MySQL...'
                            break
                        case 'Cache Server (Redis)':
                            echo 'Simulation : Déploiement du container Docker Redis...'
                            break
                        case 'Monitoring Server (Prometheus)':
                            echo 'Simulation : Déploiement du container Docker Prometheus...'
                            break
                        case 'All':
                            echo 'Simulation : Déploiement de TOUS les services Docker...'
                            break
                        default:
                            echo 'Aucune option sélectionnée.'
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline terminé.'
        }
    }
}
