FROM gradle:6.0.1-jdk11 as builder
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle /usr/app/
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src
RUN gradle build || return 0
COPY . .
RUN gradle clean build
FROM openjdk:11
ENV ARTIFACT_NAME=iplocalization.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=builder $APP_HOME/build/libs/$ARTIFACT_NAME .
EXPOSE 8085
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}


