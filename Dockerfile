FROM openjdk:8
COPY /target/ProjectCloud-0.0.1-SNAPSHOT.war project-cloud.war
ENTRYPOINT ["java","-jar","target/ProjectCloud-0.0.1-SNAPSHOT.war"]