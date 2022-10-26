FROM adoptopenjdk:11-jre-hotspot
WORKDIR src
ADD impl/target/microservice-dialog-impl-1.0.0-SNAPSHOT.jar microservice-dialog-impl.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "microservice-dialog-impl.jar"]