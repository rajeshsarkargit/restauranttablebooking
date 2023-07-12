package com.restaurants.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingWrapper {
     String bookingId;
     String bookingDate;
     String bookingTime;
     Integer tableNumber;
     String restaurantName;
     String restaurantPhoneNo;
     String  customerName;
     String  customerPhoneNo;

}
