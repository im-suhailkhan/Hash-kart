FROM openjdk:8-jdk-alpine
EXPOSE 8761
RUN addgroup -S springdocker && adduser -S springdocker -G springdocker
USER springdocker:springdocker
ARG JAR_FILE=target/*.jar
COPY target/*.jar app.jar
ENTRYPOINT ["java/*","-jar","/app.jar"]