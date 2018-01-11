FROM openjdk:8-jdk-alpine
MAINTAINER sylwekczmil.pl
VOLUME /tmp
WORKDIR /
ADD ma-backend/target/*.jar /app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar