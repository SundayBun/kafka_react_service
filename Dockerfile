FROM openjdk:14-jdk
#WORKDIR /app
COPY *.jar kafkaService-0.0.1.jar
ENTRYPOINT ["java","-jar", "/kafkaService-0.0.1.jar"]