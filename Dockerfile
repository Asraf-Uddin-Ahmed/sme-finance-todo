FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/sme-finance-todo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dkr","/app.jar"]