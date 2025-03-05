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
                    
                    // Mettez à jour les variables d’environnement et créez un fichier .env avec le service sélectionné
                    sh """
                        echo "WEB_IMAGE=${serviceImage}" > .env
                        echo "DB_IMAGE=${serviceImage}" >> .env
                        echo "CACHE_IMAGE=${serviceImage}" >> .env
                        echo "DNS_IMAGE=${serviceImage}" >> .env
                        echo "MONITORING_IMAGE=${serviceImage}" >> .env
                    """

                    echo "Fichier .env mis à jour avec ${serviceImage}"
                }
            }
        }

        stage('Déploiement avec Docker Compose') {
            steps {
                script {
                    echo "Lancement de Docker Compose pour le service ${params.DOCKER_CHOICE}..."
                    
                    // Modifiez le docker-compose.yml pour faire référence aux variables dans .env
                    sh 'docker-compose -f docker-compose.yml up -d --build'
                }
            }
        }
    }
}
