FROM openjdk:17-jdk

#COPY target/webbankingapp.jar .
ADD target/springboot-mysql-docker.jar springboot-mysql-docker.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "springboot-mysql-docker.jar"]

