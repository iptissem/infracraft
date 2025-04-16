pipeline {
    agent any

    parameters {
        booleanParam(name: 'ENABLE_WEB', defaultValue: true, description: 'Déployer un service Web')
        choice(name: 'WEB_SERVICE', choices: ['Nginx', 'Apache'], description: 'Choix du service Web')

        booleanParam(name: 'ENABLE_DB', defaultValue: false, description: 'Déployer une base de données')
        choice(name: 'DB_SERVICE', choices: ['MySQL', 'PostgreSQL'], description: 'Choix du service BDD')

        booleanParam(name: 'ENABLE_CACHE', defaultValue: false, description: 'Déployer un cache')
        choice(name: 'CACHE_SERVICE', choices: ['Redis', 'Memcached'], description: 'Choix du cache')

        booleanParam(name: 'ENABLE_DNS', defaultValue: false, description: 'Déployer un service DNS')
        choice(name: 'DNS_SERVICE', choices: ['BIND', 'dnsmasq'], description: 'Choix du service DNS')

        booleanParam(name: 'ENABLE_MONITORING', defaultValue: false, description: 'Déployer un service de monitoring')
        choice(name: 'MONITORING_SERVICE', choices: ['Prometheus', 'Grafana'], description: 'Choix du monitoring')
    }

    stages {
        stage('Génération du docker-compose.yml') {
            steps {
                script {
                    def dockerfileMap = [
                        'Nginx'      : 'nginx',
                        'Apache'     : 'httpd',
                        'MySQL'      : 'mysql',
                        'PostgreSQL' : 'postgres',
                        'Redis'      : 'redis',
                        'Memcached'  : 'memcached',
                        'BIND'       : 'internetsystemsconsortium/bind9:9.16',
                        'dnsmasq'    : 'jpillora/dnsmasq',
                        'Prometheus' : 'prom/prometheus',
                        'Grafana'    : 'grafana/grafana'
                    ]

                    def dockerComposeContent = """
version: '3'
services:
"""

                    if (params.ENABLE_WEB) {
                        def image = dockerfileMap[params.WEB_SERVICE]
                        dockerComposeContent += """
  web:
    image: ${image}
    container_name: web_service
    ports:
      - "80:80"
"""
                    }

                    if (params.ENABLE_DB) {
                        def image = dockerfileMap[params.DB_SERVICE]
                        dockerComposeContent += """
  db:
    image: ${image}
    container_name: db_service
    ports:
      - "3306:3306"
    environment:
      - MYSQL_ROOT_PASSWORD=root
"""
                    }

                    if (params.ENABLE_CACHE) {
                        def image = dockerfileMap[params.CACHE_SERVICE]
                        dockerComposeContent += """
  cache:
    image: ${image}
    container_name: cache_service
    ports:
      - "6379:6379"
"""
                    }

                    if (params.ENABLE_DNS) {
                        def image = dockerfileMap[params.DNS_SERVICE]
                        dockerComposeContent += """
  dns:
    image: ${image}
    container_name: dns_service
    ports:
      - "53:53/udp"
"""
                    }

                    if (params.ENABLE_MONITORING) {
                        def image = dockerfileMap[params.MONITORING_SERVICE]
                        def port = (params.MONITORING_SERVICE == 'Prometheus') ? '9090' : '3000'
                        dockerComposeContent += """
  monitoring:
    image: ${image}
    container_name: monitoring_service
    ports:
      - "${port}:${port}"
"""
                    }

                    // Écrire le fichier docker-compose.yml
                    writeFile file: 'docker-compose.yml', text: dockerComposeContent

                    // Afficher le contenu pour debug
                    echo "Contenu de docker-compose.yml :\n${dockerComposeContent}"
                }
            }
        }

        stage('Déploiement Docker') {
            steps {
                script {
                    echo "Déploiement des services sélectionnés..."
                    sh 'docker-compose -f docker-compose.yml up -d --build'
                }
            }
        }
    }
}
