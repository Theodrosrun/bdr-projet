FROM tomcat:10.1.17-jdk17-temurin

EXPOSE 10000

CMD ["catalina.sh", "run"]