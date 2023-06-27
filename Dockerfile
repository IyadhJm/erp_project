FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/GestionCollaborator-1.0.0.jar GestionCollaborator-1.0.0.jar
ENTRYPOINT ["java","-jar","/GestionCollaborator-1.0.0.jar"]
