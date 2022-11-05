FROM openjdk:8
COPY /target/ProjectCloud-0.0.1-SNAPSHOT.war project-cloud.war
ENTRYPOINT ["java","-jar","project-cloud.war"]