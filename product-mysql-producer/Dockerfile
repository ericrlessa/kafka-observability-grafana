FROM amazoncorretto:19-al2-jdk

RUN mkdir -m777 /etc/product_mysql_producer
WORKDIR /etc/product_mysql_producer
EXPOSE 8082

ADD target/product-mysql-producer-0.0.1-SNAPSHOT.jar /etc/product_mysql_producer
ENTRYPOINT ["java", "-jar", "/etc/product_mysql_producer/product-mysql-producer-0.0.1-SNAPSHOT.jar"]