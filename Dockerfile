FROM openjdk:11
ADD target/pep-restaurant-ms-manager.jar pep-restaurant-ms-manager.jar
ENTRYPOINT ["java", "-jar", "pep-restaurant-ms-manager.jar"]
EXPOSE 8082
