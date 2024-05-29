# Use a Maven image with OpenJDK 17 for the build stage
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml file and the source code to /app

COPY . .
# Run the Maven build to package the application
RUN mvn clean package -DskipTests

# Use an OpenJDK 17 image for the runtime stage
FROM openjdk:17.0.1-jdk-slim

# Copy the JAR file from the build stage
COPY --from=build /app/target/book.rent-0.0.1-SNAPSHOT.jar bookRentApp.jar

# Expose the application port
EXPOSE 8088

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "bookRentApp.jar"]
