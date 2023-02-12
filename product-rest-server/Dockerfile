FROM amazoncorretto:19-al2-jdk

RUN mkdir -m777 /etc/product_rest_server
WORKDIR /etc/product_rest_server
EXPOSE 8088

ADD target/product-rest-server-0.0.1-SNAPSHOT.jar /etc/product_rest_server
ENTRYPOINT ["java", "-jar", "/etc/product_rest_server/product-rest-server-0.0.1-SNAPSHOT.jar"]