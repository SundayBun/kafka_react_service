FROM gradle:7-jdk17 as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN --mount=type=cache,target=./.gradle gradle :clean :build -x test --no-daemon

FROM openjdk:17-jdk-slim as runner
EXPOSE 8087:8087
RUN mkdir /app

COPY --from=builder /home/gradle/src/build/libs/*.jar /app/kafkaService.jar
ENTRYPOINT ["java","-jar","/app/kafkaService.jar"]
