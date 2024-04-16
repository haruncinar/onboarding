FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/onboarding-0.0.1-SNAPSHOT.jar docker-onboarding-0.0.1-SNAPSHOT.jar
EXPOSE 8080

CMD ["java", "-jar", "docker-onboarding-0.0.1-SNAPSHOT.jar"]