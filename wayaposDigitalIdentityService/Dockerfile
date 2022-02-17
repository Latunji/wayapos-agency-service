FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
ADD target/*.jar agency-service.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /agency-service.jar" ]