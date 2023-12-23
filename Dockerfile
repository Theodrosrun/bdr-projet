FROM tomcat:10.1.17-jdk17-temurin

COPY target/myamazingfitness-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]