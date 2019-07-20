FROM openjdk:8
ADD ./target/watchers-1.0.war watchers-1.0.war
ENTRYPOINT ["java", "-jar", "watchers-1.0.war"]