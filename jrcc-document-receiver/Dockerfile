FROM maven:3.6.1-jdk-8-alpine AS build  

# copy pom.xml and get the dependencies
COPY pom.xml /usr/src/app/pom.xml
RUN mvn dependency:go-offline -f /usr/src/app/pom.xml

# copy the src code and package application
COPY src /usr/src/app/src  
RUN mvn -f /usr/src/app/pom.xml clean package

# from build machine copy jar and execute
FROM openjdk:8-jdk-alpine3.9
COPY --from=build /usr/src/app/target/document-receiver.jar /usr/app/document-receiver.jar  
ENTRYPOINT ["java","-jar","/usr/app/document-receiver.jar"]
