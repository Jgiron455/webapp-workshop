FROM java:openjdk-8u111-jre-alpine

RUN adduser -D app

ENV HOME=/home/app
WORKDIR $HOME

ENV APP_NAME=review-api

COPY build/libs/*.jar $HOME/$APP_NAME/app.jar

RUN chown -R app:app $HOME/*

USER app
WORKDIR $HOME/$APP_NAME

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
EXPOSE 9091