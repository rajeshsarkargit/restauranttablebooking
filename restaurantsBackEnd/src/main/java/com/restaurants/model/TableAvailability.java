package com.restaurants.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tableAvailability")
public class TableAvailability {
    @Id
    private String id;
    private String restaurantId;
    private String bookingDate;
    private List<String> bookingTimes;
    private Integer availableTables;
}

