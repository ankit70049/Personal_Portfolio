FROM maven:3.8.4-openjdk-8 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:8u302-jdk-slim
COPY  --from=build /target/myproject-0.0.1-SNAPSHOT.jar myproject.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "myproject.jar" ]
