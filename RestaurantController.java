package com.example.demoHW;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController
{

    // private static List<RestaurantDTO> m_restaurants = new ArrayList<>();

    RestaurantREPO restaurantREPO = new RestaurantREPO("jdbc:sqlite:E:/SQLite/rest03062021.db");

    @GetMapping("/restaurant")
    public List<RestaurantDTO> getRestaurants()
    {
        return restaurantREPO.getAllRestaurants();
    }

    @GetMapping("/restaurant/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable("id") int id)
    {
        return restaurantREPO.getRestaurantById(id);
    }


    @PostMapping("/restaurant")
    public void addRestaurant(@RequestBody RestaurantDTO rest)
    {
        restaurantREPO.insertRestaurant(rest);
    }


    @PutMapping("/restaurant/{id}")
    public void updateRestaurantByID(@PathVariable("id") int id, @RequestBody RestaurantDTO rest)
    {
        restaurantREPO.updateRestaurant(rest, id);
    }


    @DeleteMapping("/restaurant/{id}")
    public void deleteRestaurantById(@PathVariable("id") int id)
    {
        restaurantREPO.deleteRestaurant(id);
    }


}
