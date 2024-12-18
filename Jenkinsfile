pipeline {
    agent any

    stages {
        stage('Sélection des services') {
            steps {
                script {
                    echo "Docker choisi: ${params.DOCKER_CHOICE}"
                    echo "Service choisi: ${params.SERVICE_CHOICE}"
                }
            }
        }

        stage('Création du conteneur Docker (Simulé)') {
            steps {
                script {
                    echo "Simuler la création du conteneur Docker: ${params.CONTAINER_NAME}..."
                    echo "Service choisi: ${params.SERVICE_CHOICE}"
                    echo "Simuler l'exécution de la commande 'docker run' pour ${params.SERVICE_CHOICE} dans ${params.CONTAINER_NAME}..."
                }
            }
        }

        stage('Installation du Service (Simulé)') {
            steps {
                script {
                    echo "Simuler l'installation du service ${params.SERVICE_CHOICE} dans le conteneur Docker..."
                    echo "Service ${params.SERVICE_CHOICE} configuré avec succès dans le conteneur ${params.CONTAINER_NAME}."
                }
            }
        }

        stage('Exécution du Playbook Ansible') {
            steps {
                script {
                    echo "Exécution du playbook Ansible pour déployer ${params.SERVICE_CHOICE}..."
                    sh """
                        ansible-playbook playbooks/web_server.yml \
                        -e "webserver=${params.SERVICE_CHOICE}" \
                        -i localhost,
                    """
                }
            }
        }
    }
}
