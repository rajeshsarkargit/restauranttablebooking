package com.restaurants.controller;

import com.restaurants.IRestaurantService;
import com.restaurants.exception.ErrorResponse;
import com.restaurants.exception.ServiceException;
import com.restaurants.model.Restaurant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.restaurants.constants.Constants.PLEASE_PROVIDE_RESTAURANT_ID;
//@Tag(name = "restaurants", description = "restaurants management APIs")
@RestController
@RequestMapping(value = "/api/v1/restaurants")
@CrossOrigin("http://localhost:4200/")
public class RestaurantController {
    @Autowired
    private IRestaurantService service;


    /*@Operation(
            summary = "Find All restaurants",
            description = "Find All restaurants",
            tags = { "restaurants", "get" })
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", content = { @Content(schema = @Schema(implementation = Restaurant.class) )}),
            @ApiResponse(responseCode  = "404", content = { @Content(schema = @Schema(implementation = Restaurant.class) )}),
            @ApiResponse(responseCode  = "500", content = { @Content(schema = @Schema(implementation = Restaurant.class) )})
    })*/
    @GetMapping(value = "/")
    public ResponseEntity<List<Restaurant>> getRestaurants() {
         List<Restaurant> Restaurants = service.getRestaurants();
         return new ResponseEntity<>(Restaurants,HttpStatus.OK);
    }

    @GetMapping(value = "/{restaurantId}")
    public Restaurant getRestaurants(
            @PathVariable(name = "restaurantId") String restaurantId) {
        return service.getRestaurantById(restaurantId);
    }

    @PostMapping(value = "/",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> saveRestaurants(@RequestBody Restaurant restaurant) {
        Restaurant RestaurantRes = service.saveRestaurant(restaurant);
        return new ResponseEntity<>(RestaurantRes,HttpStatus.CREATED);
    }

    @PutMapping(value = "/")
    public String updateRestaurants(@RequestBody Restaurant restaurant) throws Exception {
        if(StringUtils.isEmpty(restaurant.getId())){
            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    new ErrorResponse(PLEASE_PROVIDE_RESTAURANT_ID));
        }
        return service.updateRestaurants(restaurant);
    }

    @DeleteMapping(value = "/{restaurantId}")
    public String deleteRestaurants(@PathVariable(name = "restaurantId") String restaurantId) {
        return service.deleteRestaurantById(restaurantId);
    }

}
