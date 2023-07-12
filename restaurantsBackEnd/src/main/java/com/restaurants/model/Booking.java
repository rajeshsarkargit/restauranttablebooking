package com.restaurants.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "booking")
public class Booking {
    @Id
    private String id;
    private String restaurantId;
    private String customerId;
    private String bookingDate;
    private String bookingTime;
    private Integer tableNumber;
    private Integer persons;
    private Boolean isBookingCanceled;

}
