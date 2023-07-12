package com.restaurants.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "restaurant")
public class Restaurant {
    @Id
    private String id;
    private String name;
    private String phoneNo;
    private String category;
    private String address;
    private Integer totalTables;

}
