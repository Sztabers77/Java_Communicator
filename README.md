## JAVA COMMUNICATOR PROJECT

Project for Another Java API Communicator [AJAPIC] with use of MySQL database.

### Prerequisites


- for git high-level repository operations  use *[git-flow](https://danielkummer.github.io/git-flow-cheatsheet/index.pl_PL.html)*
- to connect to db use ` docker-compose up -d`, db running on `http://localhost:3306`, db name: `communicator`, user/pass: `user/user`

## Requirements

- Java 8+ (JDK)

### Settings 

In order to change DB change file `src/main/resources/application-local.properties` data.

### App Running

- To install use `mvnw clean install`

Go to `target` folder.

Execute:

`java -jar wsisz-project.jar`

### Authors:

- [Wojtek Sala](https://github.com/salalo)
- [Bartosz Sztąberski](https://github.com/Sztabers)
- [Łukasz Świtalski](https://github.com/Luckjamz)
