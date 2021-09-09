# README #

This is a porqueeuprogramo learn tool (java spring maven) application.

### What is this repository for? ###

* Quick summary
* Version 0.0.1
* [Repository](https://bitbucket.org/porqueeuprogramo/pep-restaurant/)

### How do I get set up? ###

* Install Java 8 or later version
* Add Dependencies already presents in pom
* Configure database (docker compose config)
* Clean, install and run app

### How do I set up docker? ###

* change localhost on application yml to your local ip
* mvn clean install

* docker build -t pep-restaurant-backend.jar .
* docker run -p 8080:8080 --name pep-restaurant-backend pep-restaurant-backend.jar
or
* docker-compose up (docker-compose.env has already the env variables values)

### How do I check code quality
* Run the following cmd on sonar:
* mvn sonar:sonar -Dsonar.projectKey=PROJECT_KEY -Dsonar.host.url=http://localhost:9000 -Dsonar.login=TOKEN
  
### Who do I talk to? ###

* discord https://discord.gg/v2MgpUT4QC
* email porqueeuprogramo@gmail.com