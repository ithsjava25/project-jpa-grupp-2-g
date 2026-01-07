FROM eclipse-temurin:21-jdk

COPY src/main/java/backend/App.java /App.java

ENTRYPOINT ["java", "/App.java"]
