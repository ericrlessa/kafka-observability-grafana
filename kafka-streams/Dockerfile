FROM amazoncorretto:19-al2-jdk

RUN mkdir -m777 /etc/kafka-streams
WORKDIR /etc/kafka-streams

ADD target/product-kafka-stream-0.0.1-SNAPSHOT.jar /etc/kafka-streams
ENTRYPOINT ["java", "-jar", "/etc/kafka-streams/product-kafka-stream-0.0.1-SNAPSHOT.jar"]
