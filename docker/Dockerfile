FROM openjdk:8-jre-alpine

COPY target/blog-demo.jar /app/

EXPOSE 8080

CMD java -jar /app/blog-demo.jar --spring.profiles.active=prod