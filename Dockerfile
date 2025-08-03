# Stage 1: Build the application using Maven
FROM maven:3.8.5-openjdk-19 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Create the runtime image
FROM openjdk:19-jre-slim
WORKDIR /app

# Install dependencies for JavaFX and X11 forwarding
RUN apt-get update && apt-get install -y \
    libgtk-3-0 \
    libxtst-dev \
    libxrender1 \
    && rm -rf /var/lib/apt/lists/*

# Copy the JAR file from the build stage
COPY --from=build /app/target/TweetAP-1.0-SNAPSHOT.jar /app/app.jar

# Set the entrypoint
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
