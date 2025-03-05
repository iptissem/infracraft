# Utilisation de l'image Java
FROM openjdk:11

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR et l'exécuter
COPY target/myapp.jar myapp.jar

# Exposer le port de l'application
EXPOSE 8080

# Lancer l'application
CMD ["java", "-jar", "myapp.jar"]
