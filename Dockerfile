FROM openjdk:17-jdk-slim-bullseye

ARG JAR_FILE=build/libs/*[!plain].jar

COPY ${JAR_FILE} app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/app.jar"]