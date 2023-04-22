FROM openjdk:19-alpine as build

# Set the current working directory inside the image
WORKDIR /app-server

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .
COPY src src

RUN ./mvnw clean package

FROM openjdk:19-alpine

COPY --from=build /app-server/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
