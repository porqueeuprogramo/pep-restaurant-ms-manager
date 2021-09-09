FROM openjdk:11
ADD target/pep-restaurant-backend.jar pep-restaurant-backend.jar
ENTRYPOINT ["java", "-jar", "pep-restaurant-backend.jar"]
EXPOSE 8080
