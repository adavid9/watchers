FROM openjdk:8-jdk-alpine

VOLUME /tmp

COPY target/*.war watchers.war

ENTRYPOINT ["java", "-jar", "-Dspring-boot.run.profiles=dev", "/watchers.war"]