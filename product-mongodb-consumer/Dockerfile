FROM amazoncorretto:19-al2-jdk

RUN mkdir -m777 /etc/product_mongodb
WORKDIR /etc/product_mongodb
EXPOSE 8081

ADD target/product-mongodb-consumer-0.0.1-SNAPSHOT.jar /etc/product_mongodb
ENTRYPOINT ["java", "-jar", "/etc/product_mongodb/product-mongodb-consumer-0.0.1-SNAPSHOT.jar"]