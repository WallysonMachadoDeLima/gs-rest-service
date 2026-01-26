# ---- Build Stage ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace/app
COPY . .
# Build always with Maven
RUN ./mvnw clean package -DskipTests

# ---- Run Stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
