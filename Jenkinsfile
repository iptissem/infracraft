pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/iptissem/infracraft.git'  // Remplace par l'URL de ton dépôt
            }
        }

        stage('Terraform Init') {
            steps {
                dir('terraform') {
                    sh 'terraform init'
                }
            }
        }

        stage('Terraform Apply') {
            steps {
                dir('terraform') {
                    sh 'terraform apply -auto-approve'
                }
            }
        }

        stage('Ansible Playbook') {
            steps {
                dir('ansible') {
                    sh 'ansible-playbook -i inventory.ini playbook.yml'
                }
            }
        }
    }

    post {
        always {
            echo 'Build finished.'
        }
    }
}
