FROM maven:alpine

COPY . /

RUN mvn clean install

ENTRYPOINT ["java", "-jar", "/target/takeaway-producer-1.0-SNAPSHOT.jar"]
