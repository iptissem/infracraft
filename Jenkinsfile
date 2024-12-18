pipeline {
    agent any

    parameters {
        // Choix du Docker à déployer
        choice(name: 'DOCKER_CHOICE', choices: ['Serveur Web', 'Serveur de BD', 'Serveur d\'Application', 'Serveur de Cache', 'Serveur DNS', 'Serveur de Monitoring', 'Contrôleur de Domaine'], description: 'Sélectionnez le Docker à déployer')

        // Choix dynamique des services en fonction du Docker sélectionné
        choice(name: 'SERVICE_CHOICE', choices: [], description: 'Choisissez le service à installer')

        // Paramètre pour le nom du conteneur
        string(name: 'CONTAINER_NAME', defaultValue: 'my-container', description: 'Nom du conteneur Docker')
    }

    stages {
        stage('Sélection des services') {
            steps {
                script {
                    // Définir les options de services en fonction du Docker choisi
                    if (params.DOCKER_CHOICE == 'Serveur Web') {
                        params.SERVICE_CHOICE = input message: 'Choisissez un service pour le serveur Web', parameters: [
                            choice(name: 'WEB_SERVICE', choices: ['Nginx', 'Apache'], description: 'Choisir le serveur Web')
                        ]
                    } else if (params.DOCKER_CHOICE == 'Serveur de BD') {
                        params.SERVICE_CHOICE = input message: 'Choisissez un service pour la base de données', parameters: [
                            choice(name: 'DB_SERVICE', choices: ['MySQL', 'PostgreSQL', 'MongoDB'], description: 'Choisir la base de données')
                        ]
                    } else if (params.DOCKER_CHOICE == 'Serveur d\'Application') {
                        params.SERVICE_CHOICE = input message: 'Choisissez un service pour le serveur d\'application', parameters: [
                            choice(name: 'APP_SERVICE', choices: ['Node.js', 'Java (Spring Boot)', 'Python (Django/Flask)'], description: 'Choisir le serveur d\'application')
                        ]
                    } else if (params.DOCKER_CHOICE == 'Serveur de Cache') {
                        params.SERVICE_CHOICE = input message: 'Choisissez un service pour le serveur de cache', parameters: [
                            choice(name: 'CACHE_SERVICE', choices: ['Redis', 'Memcached'], description: 'Choisir le serveur de cache')
                        ]
                    } else if (params.DOCKER_CHOICE == 'Serveur DNS') {
                        params.SERVICE_CHOICE = input message: 'Choisissez un service pour le serveur DNS', parameters: [
                            choice(name: 'DNS_SERVICE', choices: ['BIND', 'dnsmasq'], description: 'Choisir le serveur DNS')
                        ]
                    } else if (params.DOCKER_CHOICE == 'Serveur de Monitoring') {
                        params.SERVICE_CHOICE = input message: 'Choisissez un service pour le serveur de monitoring', parameters: [
                            choice(name: 'MONITORING_SERVICE', choices: ['Prometheus', 'Grafana'], description: 'Choisir le serveur de monitoring')
                        ]
                    } else if (params.DOCKER_CHOICE == 'Contrôleur de Domaine') {
                        params.SERVICE_CHOICE = input message: 'Choisissez un service pour le contrôleur de domaine', parameters: [
                            choice(name: 'DOMAIN_CONTROLLER', choices: ['Active Directory'], description: 'Choisir le contrôleur de domaine')
                        ]
                    }
                }
            }
        }

        stage('Création du conteneur Docker (Simulé)') {
            steps {
                script {
                    echo "Simuler la création du conteneur Docker: ${params.CONTAINER_NAME}..."
                    echo "Choix du Docker: ${params.DOCKER_CHOICE}"
                    echo "Service choisi: ${params.SERVICE_CHOICE}"
                    echo "Simuler l'exécution de la commande 'docker run' pour ${params.SERVICE_CHOICE} dans ${params.CONTAINER_NAME}..."
                }
            }
        }

        stage('Installation du Service (Simulé)') {
            steps {
                script {
                    echo "Simuler l'installation du service ${params.SERVICE_CHOICE} dans le conteneur Docker..."
                    // Afficher un message pour simuler l'installation
                    echo "Service ${params.SERVICE_CHOICE} configuré avec succès dans le conteneur ${params.CONTAINER_NAME}."
                }
            }
        }
    }
}
