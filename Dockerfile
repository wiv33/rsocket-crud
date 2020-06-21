FROM hirokimatsumoto/alpine-openjdk-11

MAINTAINER PS
VOLUME /tmp

# --build-arg build/libs/*.jar
ARG JAR_FILE
COPY ${JAR_FILE} app-server.jar
EXPOSE 7000

#"-Djava.security.egd=file:/dev/./urandom",
#"-Dspring.profiles.active=prod",
ENTRYPOINT ["java","-jar","/app-server.jar"]