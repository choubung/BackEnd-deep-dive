FROM eclipse-temurin:21.0.9_10-jre-ubi9-minimal

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/app.jar"]
