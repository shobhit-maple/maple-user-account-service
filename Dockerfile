FROM openjdk:21-slim
RUN apt-get update && apt-get install -y curl
COPY build/libs/maple-user-account-service-1.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]