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
                    
                    // Mettez à jour le fichier .env en fonction du service sélectionné
                    def envVars = [
                        'WEB_IMAGE': '',
                        'DB_IMAGE': '',
                        'CACHE_IMAGE': '',
                        'DNS_IMAGE': '',
                        'MONITORING_IMAGE': ''
                    ]
                    
                    if (params.DOCKER_CHOICE == 'web') {
                        envVars.WEB_IMAGE = serviceImage
                    } else if (params.DOCKER_CHOICE == 'db') {
                        envVars.DB_IMAGE = serviceImage
                    } else if (params.DOCKER_CHOICE == 'cache') {
                        envVars.CACHE_IMAGE = serviceImage
                    } else if (params.DOCKER_CHOICE == 'dns') {
                        envVars.DNS_IMAGE = serviceImage
                    } else if (params.DOCKER_CHOICE == 'monitoring') {
                        envVars.MONITORING_IMAGE = serviceImage
                    }

                    echo "Fichier .env mis à jour avec ${serviceImage}"
                }
            }
        }

        stage('Déploiement avec Docker Compose') {
            steps {
                script {
                    echo "Lancement de Docker Compose pour le service ${params.DOCKER_CHOICE}..."
                    
                    sh 'docker-compose -f docker-compose.yml up -d --build --no-deps ${params.DOCKER_CHOICE}'
                }
            }
        }
    }
}
