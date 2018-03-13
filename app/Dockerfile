FROM java:8-jre

WORKDIR /

COPY build/libs/app.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "server", "config/app.yml"]
