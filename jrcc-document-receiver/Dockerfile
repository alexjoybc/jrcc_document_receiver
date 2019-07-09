FROM maven:3.6.1-jdk-8 AS build  

RUN mkdir github
WORKDIR /github

RUN git clone https://github.com/bcgov/jrcc-document-access-libs.git

# Get and install the jrcc-document-access-libs
WORKDIR /github/jrcc-document-access-libs
RUN git checkout v0.3.3

RUN mvn clean install

WORKDIR /

# copy pom.xml and get the dependencies
COPY pom.xml /usr/src/app/pom.xml
RUN mvn dependency:go-offline -f /usr/src/app/pom.xml

# copy the src code and package application
COPY src /usr/src/app/src  
RUN mvn -f /usr/src/app/pom.xml clean package

# from build machine copy jar and execute
FROM openjdk:8-jdk-alpine3.9

ARG REDIS_HOST
ARG REDIS_PORT
ARG RABBITMQ_HOST
ARG RABBITMQ_PORT
ARG RABBITMQ_USERNAME
ARG RABBITMQ_PASSWORD

ENV REDIS_HOST=$REDIS_HOST
ENV REDIS_PORT=$REDIS_PORT
ENV RABBITMQ_HOST=$RABBITMQ_HOST
ENV RABBITMQ_PORT=$RABBITMQ_PORT
ENV RABBITMQ_USERNAME=$RABBITMQ_USERNAME
ENV RABBITMQ_PASSWORD=$RABBITMQ_PASSWORD

COPY --from=build /usr/src/app/target/document-receiver.jar /usr/app/document-receiver.jar
ENTRYPOINT ["java","-jar","/usr/app/document-receiver.jar"]
