FROM adoptopenjdk/openjdk11:latest
EXPOSE 8761
ADD target/*.jar agency-service.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-jar","eureka-service.jar"]