FROM openjdk:8

WORKDIR /

COPY config/app.yml config/app.yml
COPY build/libs/app.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "server", "config/app.yml"]
