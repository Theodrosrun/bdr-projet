# https://www.softwareyoga.com/docker-tomact-tutorial/
# docker build -t myamazingfitness:v1.0 .
# docker run -p 10000:8080 myamazingfitness:v1.0
# http://localhost:10000/

FROM tomcat:8.0-alpine

ADD target/myamazingfitness-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]