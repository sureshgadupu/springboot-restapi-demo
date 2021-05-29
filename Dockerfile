FROM openjdk:8
ADD target/springboot-restapi-demo-0.0.1-SNAPSHOT.jar   springboot-restapi-demo-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","springboot-restapi-demo-0.0.1-SNAPSHOT.jar"]