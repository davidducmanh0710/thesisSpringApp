FROM tomcat:9.0.87
RUN rm -rvf /usr/local/tomcat/webapps/ROOT
ADD ./target/thesisSpringApplication-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/thesisSpringApplication.war
CMD ["catalina.sh", "run"]
EXPOSE 8080