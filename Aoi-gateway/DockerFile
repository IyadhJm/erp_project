FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/gestionbdg-1.0.0.jar gestionbdg-1.0.0.jar
ENTRYPOINT ["java","-jar","/gestionbdg-1.0.0.jar"]


FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/aoi-gateway-1.0.0.jar aoi-gateway-1.0.0.jar
ENTRYPOINT ["java","-jar","/aoi-gateway-1.0.0.jar"]

FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/server-1.0.0.jar server-1.0.0.jar
ENTRYPOINT ["java","-jar","/server-1.0.0.jar"]