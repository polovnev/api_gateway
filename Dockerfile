FROM openjdk:8u131-jdk-alpine
MAINTAINER Oleksandr Polovnev "polovnevad@gmail.com"
EXPOSE 8080
WORKDIR /usr/local/bin/
COPY ./target/api_gateway-0.0.1-SNAPSHOT.jar webapp.jar
ENTRYPOINT ["java","-jar","webapp.jar"]