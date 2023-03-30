FROM openjdk:19
ARG JAR_FILE=target/spring-backend-*.jar
COPY ${JAR_FILE} backend.jar
ENTRYPOINT ["java", "-jar", "/backend.jar"]
EXPOSE 8080
