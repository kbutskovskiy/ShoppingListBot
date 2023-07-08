FROM openjdk:17

WORKDIR /app

COPY target/Shopping_list-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Shopping_list-0.0.1-SNAPSHOT.jar"]
