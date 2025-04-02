FROM tomcat
EXPOSE 8080
COPY target/order-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-Xmx512m","-Dspring.profiles.active=Devl","-jar","order-service-0.0.1-SNAPSHOT.jar"]