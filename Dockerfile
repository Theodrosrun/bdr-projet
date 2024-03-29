FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY .mvn .mvn
COPY mvnw mvnw
COPY pom.xml pom.xml
COPY src src
RUN mvn dependency:go-offline
RUN mvn package

FROM tomcat:10-jdk17-temurin
COPY --from=build /app/target/myamazingfitness-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]

