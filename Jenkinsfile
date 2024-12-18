 stages {
        stage('Sélection des services') {
            steps {
                script {
                    echo "Docker choisi: ${params.DOCKER_CHOICE}"
                    echo "Service choisi: ${params.SERVICE_CHOICE}"
                    echo "Nom du conteneur: ${params.CONTAINER_NAME}"
                }
            }
        }

        stage('Création du conteneur Docker') {
            steps {
                script {
                    // Appel à Ansible pour déployer le service choisi
                    echo "Démarrage du conteneur ${params.CONTAINER_NAME} avec le service ${params.SERVICE_CHOICE}..."
                    sh """
                        ansible-playbook playbooks/web_server.yml -i localhost, --extra-vars "server_choice=${params.SERVICE_CHOICE} container_name=${params.CONTAINER_NAME}"
                    """
                }
            }
        }

        stage('Installation du Service') {
            steps {
                script {
                    echo "Service ${params.SERVICE_CHOICE} installé dans le conteneur ${params.CONTAINER_NAME}."
                }
            }
        }
    }
}