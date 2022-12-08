FROM openjdk:14-jdk
#WORKDIR /app
COPY build/libs/ /target
ENTRYPOINT ["java","-jar", "/target/kafkaService-0.0.1-SNAPSHOT.jar"]