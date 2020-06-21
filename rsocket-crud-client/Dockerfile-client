FROM hirokimatsumoto/alpine-openjdk-11

MAINTAINER PS

VOLUME /tmp
EXPOSE 8080

# --build-arg build/libs/*.jar
ARG JAR_FILE
COPY ${JAR_FILE} app-client.jar

#"-Djava.security.egd=file:/dev/./urandom",
#"-Dspring.profiles.active=prod",

ENTRYPOINT ["java", "-jar", "/app-client.jar"]