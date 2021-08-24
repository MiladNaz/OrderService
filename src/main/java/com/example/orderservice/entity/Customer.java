package com.example.orderservice.entity;



import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
public class Customer {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String pizzaName;

    private String timeOfOrder = String.valueOf(new Timestamp(System.currentTimeMillis())).substring(0,19);


    public Customer(String pizzaName) {
        this.pizzaName = pizzaName;

    }

    public Customer() {
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

}
