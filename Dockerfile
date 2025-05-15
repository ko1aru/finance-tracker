# 1. Use an official OpenJDK image from Docker Hub
FROM openjdk:17-jdk-slim
# 2. Set the working directory inside the container
WORKDIR /fin-app
# 3. Copy the application JAR file to the container
# COPY target/FinanceTracker-0.0.1-SNAPSHOT.jar fin-app.jar
COPY . .
RUN ./mvnw clean package -DskipTests
# 4. Expose the port that Spring Boot runs on
EXPOSE 8080
# 5. Run the application
# ENTRYPOINT ["java", "-jar", "fin-app.jar"]
CMD ["java", "-jar", "target/FinanceTracker-0.0.1-SNAPSHOT.jar"]