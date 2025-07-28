#
##FROM eclipse-temurin:21
##
##LABEL mentainer="zonicultra@gmail.com"
#
##FROM maven:latest As build
#FROM eclipse-temurin:21-jdk-alpine
#ARG JAR_FILE=target/*.jar
##WORKDIR /app
#
##COPY pom.xml /app/
#
##COPY src /app/src
#
##COPY --from=build /app/target/odent-shop-0.0.1-SNAPSHOT.jar /app/odent-shop-0.0.1-SNAPSHOT.jar
#
#COPY ./target/odent-shop-0.0.1-SNAPSHOT app.jar
#
#
#ENTRYPOINT ["java", "jar", "/app.jar"]

# Use a lightweight base image with Java 21
FROM eclipse-temurin:21-jdk-alpine

# Copy the built JAR file from target directory
COPY ./target/odent-shop-inventory.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]

