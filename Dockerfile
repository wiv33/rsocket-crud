FROM hirokimatsumoto/alpine-openjdk-11

MAINTAINER PS
VOLUME /tmp
EXPOSE 7000

# --build-arg build/libs/*.jar
ARG JAR_FILE
COPY ${JAR_FILE} app-server.jar

#"-Djava.security.egd=file:/dev/./urandom",
#"-Dspring.profiles.active=prod",
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app-server.jar"]