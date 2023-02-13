# kafka-observability-grafana

<p align="center">🚀 Observability applied to services that communicate via kafka and REST</p>

### Big picture of services 

![enter image description here](https://github.com/ericrlessa/kafka-observability-grafana/raw/main/architecture.png)

### 🎲 Running services

```bash
# Clone this repo
$ git clone <https://github.com/ericrlessa/kafka-observability-grafana.git>

# start services
$ docker compose up -d

# Enter in mysql service
$ docker compose exec -it mysql bash

# Call mysql client with password "root"
$ mysql -u root -p

# run the following scripts in mysql
$ CREATE TABLE IF NOT EXISTS product (
$    id INT AUTO_INCREMENT PRIMARY KEY,
$    name VARCHAR(255) NOT NULL,
$    price decimal(10,4) NOT NULL
$ );
$ insert into product (name, price) values ('Galaxy Sql', 2000.40);
```

# URls to access the services:

- Service to list products: http://localhost:8082
- Service to add a product: http://localhost:8082/form
- Service to list products from mongodb: http://localhost:8081
- grafana: http://localhost:3000
- control center for kafka: http://localhost:9021/
- prometheus: http://localhost:9090/
- mongo express: http://localhost:8085/
