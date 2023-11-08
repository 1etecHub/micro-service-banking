#start with a base image containing java runtime
FROM openjdk:17-jdk-slim

#the maintainer of the docker image
MAINTAINER kokorobank

#add the application jar to the image
COPY target/account-service-0.0.1-SNAPSHOT.jar account-service-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java", "-jar", "account-service-0.0.1-SNAPSHOT"]
