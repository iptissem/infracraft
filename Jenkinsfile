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
                    
                    // Crée un fichier docker-compose.yml dynamique
                    def dockerComposeContent = """
version: '3'
services:
  web:
    build:
      context: .
      dockerfile: web/Dockerfile.${serviceImage}
    image: ${serviceImage}
    ports:
      - "80:80"
"""

                    // Ajouter conditionnellement les autres services selon le choix
                    if (params.DOCKER_CHOICE == 'db') {
                        dockerComposeContent += """
  db:
    build:
      context: .
      dockerfile: db/Dockerfile.${serviceImage}
    container_name: db_server
    image: ${serviceImage}:latest
    environment:
      MYSQL_ROOT_PASSWORD: ${DB_ROOT_PASSWORD}
      MYSQL_DATABASE: ${DB_DATABASE}
    ports:
      - "3306:3306"
    networks:
      - app-network
"""
                    }

                    // Générer le fichier docker-compose.yml
                    writeFile file: 'docker-compose.yml', text: dockerComposeContent

                    // Mettre à jour .env avec bash pour éviter "Bad substitution"
                    sh 'bash -c "echo WEB_IMAGE=${serviceImage} > .env"'
                    sh 'bash -c "echo DB_IMAGE=${serviceImage} >> .env"'
                    sh 'bash -c "echo CACHE_IMAGE=${serviceImage} >> .env"'
                    sh 'bash -c "echo DNS_IMAGE=${serviceImage} >> .env"'
                    sh 'bash -c "echo MONITORING_IMAGE=${serviceImage} >> .env"'
                }
            }
        }

        stage('Déploiement avec Docker Compose') {
            steps {
                script {
                    echo "Lancement de Docker Compose pour le service ${params.DOCKER_CHOICE}..."
                    // Exécute Docker Compose avec le fichier généré dynamiquement
                    sh 'docker-compose -f docker-compose.yml up -d --build'
                }
            }
        }
    }
}
