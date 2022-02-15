FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} agency-service.jar
ENTRYPOINT ["java","-jar","/agency-service.jar"]