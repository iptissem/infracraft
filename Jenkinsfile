pipeline {
    agent any

    // parameters {
    //     // Choix du Docker à déployer
    //     choice(name: 'DOCKER_CHOICE', choices: ['Serveur Web', 'Serveur de BD', 'Serveur d\'Application', 'Serveur de Cache', 'Serveur DNS', 'Serveur de Monitoring', 'Contrôleur de Domaine'], description: 'Sélectionnez le Docker à déployer')

    //     // Paramètre dynamique pour choisir le service
    //     activeChoiceParam(name: 'SERVICE_CHOICE') {
    //         description('Choisissez un service à installer')

    //         // Dépend des choix faits dans 'DOCKER_CHOICE'
    //         groovyScript {
    //             script("""
    //                 if (DOCKER_CHOICE == 'Serveur Web') {
    //                     return ['Nginx', 'Apache']
    //                 } else if (DOCKER_CHOICE == 'Serveur de BD') {
    //                     return ['MySQL', 'PostgreSQL', 'MongoDB']
    //                 } else if (DOCKER_CHOICE == 'Serveur d\'Application') {
    //                     return ['Node.js', 'Java (Spring Boot)', 'Python (Django/Flask)']
    //                 } else if (DOCKER_CHOICE == 'Serveur de Cache') {
    //                     return ['Redis', 'Memcached']
    //                 } else if (DOCKER_CHOICE == 'Serveur DNS') {
    //                     return ['BIND', 'dnsmasq']
    //                 } else if (DOCKER_CHOICE == 'Serveur de Monitoring') {
    //                     return ['Prometheus', 'Grafana']
    //                 } else if (DOCKER_CHOICE == 'Contrôleur de Domaine') {
    //                     return ['Active Directory']
    //                 } else {
    //                     return []
    //                 }
    //             """)
    //             fallbackScript('return []') // Si aucune option, renvoyer une liste vide
    //         }
    //     }

    //     // Paramètre pour le nom du conteneur
    //     string(name: 'CONTAINER_NAME', defaultValue: 'my-container', description: 'Nom du conteneur Docker')
    // }

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
                    // Appel à Ansible pour déployer le service choisi
                    echo "Démarrage du conteneur ${params.CONTAINER_NAME} avec le service ${params.SERVICE_CHOICE}..."
                    sh """
                        ansible-playbook ansible/playbooks/web_server.yml -i localhost, --extra-vars "server_choice=${params.SERVICE_CHOICE} container_name=${params.CONTAINER_NAME}"
                    """
                    // echo "Simuler la création du conteneur Docker: ${params.CONTAINER_NAME}..."
                    // echo "Service choisi: ${params.SERVICE_CHOICE}"
                    // echo "Simuler l'exécution de la commande 'docker run' pour ${params.SERVICE_CHOICE} dans ${params.CONTAINER_NAME}..."
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
    }
}
