FROM maven:3.9.4-eclipse-temurin-17

WORKDIR /challange-api-rest-banco

COPY . .

RUN mvn clean install

CMD ["mvn", "spring-boot:run"]
