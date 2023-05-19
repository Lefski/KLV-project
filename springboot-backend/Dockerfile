FROM openjdk:19-alpine as build

WORKDIR /app-server

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .
COPY src src

RUN ./mvnw clean package

FROM openjdk:19-alpine

RUN apk add --no-cache bash dos2unix

COPY wait-for-it.sh /wait-for-it.sh

RUN dos2unix /wait-for-it.sh && chmod +x /wait-for-it.sh

COPY --from=build /app-server/target/*.jar app.jar

EXPOSE 8080

CMD ["/bin/sh", "-c", "/wait-for-it.sh klv-project_app-db:${DB_PORT} -- java -jar app.jar"]
