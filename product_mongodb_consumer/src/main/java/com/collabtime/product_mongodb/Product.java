package com.collabtime.product_mongodb;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("productdb.product")
public class Product {
   
    @Id
    private String id;

    private String name;

    private BigDecimal price;

    public Product() {
	}

    public Product(String id, String name, BigDecimal price) {
		this.id = id;
        this.name = name;
        this.price = price;
	}

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
