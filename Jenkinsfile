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
                    
                    def serviceImage = dockerfileMap[params.SERVICE_CHOICE]
                    
                    if (!serviceImage) {
                        error "Service non reconnu : ${params.SERVICE_CHOICE}"
                    }

                    echo "Déploiement du service ${params.SERVICE_CHOICE} pour ${params.DOCKER_CHOICE}..."
                    
                    // Met à jour les variables d’environnement
                    sh """
                        echo "WEB_IMAGE=${serviceImage}" > .env
                    """

                    echo "Fichier .env mis à jour avec ${serviceImage}"
                }
            }
        }

        stage('Déploiement avec Docker Compose') {
            steps {
                script {
                    echo "Lancement de Docker Compose..."
                    sh 'docker-compose up -d --build'
                }
            }
        }
    }
}
