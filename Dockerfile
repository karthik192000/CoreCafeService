FROM openjdk:11
ENV spring.cloud.config.uri http://configserver:7070
COPY target/CoreCafeService-1.0.0-SNAPSHOT.jar /CoreCafeService-1.0.0-SNAPSHOT.jar
CMD ["java", "-jar", "-noverify", "/CoreCafeService-1.0.0-SNAPSHOT.jar"]
EXPOSE 8080