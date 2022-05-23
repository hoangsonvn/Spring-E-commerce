#FROM openjdk:8
#WORKDIR /app
#COPY ./target/demo6-1.0-SNAPSHOT.war .
#ADD target/demo6-1.0-SNAPSHOT.war app.war
#ENTRYPOINT ["java","-jar","app.jar"]

#ROM tomcat:8.5.78
#ADD target/ROOT.jar app.jar
#ENTRYPOINT ["java","-jar","app.jar"]

#FROM tomcat:8.0.51-jre8-alpine
#CMD ["catalina.sh","run"]

#FROM tomcat:8.0.51-jre8-alpine
#RUN rm -rf /usr/local/tomcat/webapps/*
#COPY ./target/demo6-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
#CMD ["catalina.sh","run"]

FROM openjdk:8-jdk-alpine
ENV DATABASE_HOST=localhost
ENV DATABASE_PORT=3306
ENV DATABASE_NAME=demotest
ENV DATABASE_USERNAME=root
ENV DATABASE_PASSWORD=1234
ADD ./target/demo6-1.0-SNAPSHOT.war app.war
ENTRYPOINT ["java","-war","/app.war"]
#COPY ./target/demo6-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
#ENTRYPOINT ["java","-war","ROOT.war"]

