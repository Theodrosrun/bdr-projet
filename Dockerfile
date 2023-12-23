FROM tomcat:10.1.17-jdk17-temurin

EXPOSE 8080

CMD ["catalina.sh", "run"]