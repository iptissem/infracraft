pipeline {
    agent any

    parameters {
        choice(name: 'DOCKER_CHOICE', choices: ['web', 'db', 'cache', 'dns', 'monitoring'], description: 'Sélectionnez le type de service')
        choice(name: 'SERVICE_CHOICE', choices: ['Nginx', 'Apache', 'MySQL', 'PostgreSQL', 'Redis', 'Memcached', 'BIND', 'dnsmasq', 'Prometheus', 'Grafana'], description: 'Choisissez le service spécifique')
        string(name: 'CONTAINER_NAME', defaultValue: 'my-container', description: 'Nom du conteneur Docker')
    }

    stages {
        stage('Configuration des Variables') {
            steps {
                script {
                    // Mapping des services à leurs images Docker
                    def dockerfileMap = [
                        'Nginx': 'nginx',
                        'Apache': 'apache',
                        'MySQL': 'mysql',
                        'PostgreSQL': 'postgresql',
                        'Redis': 'redis',
                        'Memcached': 'memcached',
                        'BIND': 'bind',
                        'dnsmasq': 'dnsmasq',
                        'Prometheus': 'prometheus',
                        'Grafana': 'grafana'
                    ]
                    
                    // Obtenez l'image correspondant au service choisi
                    def serviceImage = dockerfileMap[params.SERVICE_CHOICE]
                    
                    // Vérification si l'image est valide
                    if (!serviceImage) {
                        error "Service non reconnu : ${params.SERVICE_CHOICE}"
                    }

                    // Affichez le service sélectionné
                    echo "Déploiement du service ${params.SERVICE_CHOICE} pour ${params.DOCKER_CHOICE}..."

                    // Mettez à jour les variables d’environnement et créez un fichier .env
                    if (params.DOCKER_CHOICE == 'web') {
                        sh """
                            echo "WEB_IMAGE=${serviceImage}" > .env
                        """
                    }
                    else if (params.DOCKER_CHOICE == 'db') {
                        // Si tu choisis db, ne pas mettre à jour WEB_IMAGE
                        echo "Ne pas modifier WEB_IMAGE si service choisi est db"
                        sh """
                            echo "DB_IMAGE=${serviceImage}" > .env
                        """
                    }
                    else if (params.DOCKER_CHOICE == 'cache') {
                        sh """
                            echo "CACHE_IMAGE=${serviceImage}" > .env
                        """
                    }
                    else if (params.DOCKER_CHOICE == 'dns') {
                        sh """
                            echo "DNS_IMAGE=${serviceImage}" > .env
                        """
                    }
                    else if (params.DOCKER_CHOICE == 'monitoring') {
                        sh """
                            echo "MONITORING_IMAGE=${serviceImage}" > .env
                        """
                    }

                    echo "Fichier .env mis à jour avec ${serviceImage}"
                }
            }
        }

        stage('Déploiement avec Docker Compose') {
            steps {
                script {
                    echo "Lancement de Docker Compose pour le service ${params.DOCKER_CHOICE}..."
                    
                    // Utiliser la commande docker-compose avec le fichier .env mis à jour
                    sh 'docker-compose -f docker-compose.yml up -d --build'
                }
            }
        }
    }
}
