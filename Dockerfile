FROM openjdk:17-jdk

ADD target/client-manager-service-docker.jar client-manager-service-docker.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "client-manager-service-docker.jar"]

