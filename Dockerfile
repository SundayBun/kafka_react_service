FROM openjdk:17-jdk-slim as build
#WORKDIR kafkaService
ARG JAR_FFILE=build/libs/kafkaService-0.0.1.jar
COPY ${JAR_FFILE} kafkaService-0.0.1.jar
ENTRYPOINT ["java","-jar", "/kafkaService-0.0.1.jar"]