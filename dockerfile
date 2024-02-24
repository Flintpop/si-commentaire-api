# Pull base image
FROM tomcat:10.1.19-jdk21-temurin

# Maintainer
LABEL maintainer="email@example.com"

# Copy to images tomcat path
ADD target/SITP3dernier-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

EXPOSE 8081

CMD ["catalina.sh", "run"]