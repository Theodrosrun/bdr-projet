FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY .mvn .mvn
COPY mvnw mvnw
COPY pom.xml pom.xml
RUN mvn dependency:go-offline
COPY src src
RUN mvn package

FROM tomcat:9-jdk17-openjdk
COPY --from=build /app/target/myamazingfitness-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
