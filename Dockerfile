# Stage 1: Build Frontend
FROM node:18-alpine AS frontend-build
WORKDIR /app/frontend
COPY Frontend/package*.json ./
RUN npm install
COPY Frontend/ ./
RUN npm run build --configuration=production

# Stage 2: Build Backend
FROM maven:3.9.6-eclipse-temurin-17 AS backend-build
WORKDIR /app/backend
COPY Backend/TaskManager/pom.xml ./
RUN mvn dependency:go-offline
COPY Backend/TaskManager/src ./src
# Copy frontend build to static resources
COPY --from=frontend-build /app/frontend/dist/TaskManager-Frontend/browser ./src/main/resources/static
RUN mvn package -DskipTests

# Stage 3: Run Stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=backend-build /app/backend/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
