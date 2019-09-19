package com.workshop.workshop.service;

import com.google.maps.model.PlacesSearchResponse;
import com.workshop.workshop.model.MyLocation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class MyLocationService {

    @Autowired
    private GoogleClient googleClient;


    public MyLocation buildMyLocation(String inputText){
        MyLocation myLocation  = new MyLocation();
        Optional<PlacesSearchResponse> response = googleClient.searchByInputText(inputText);

        if(response.isPresent()){

            myLocation.setName(response.get().results.length > 0 ? response.get().results[0].name : "Name not available");
            myLocation.setRating(response.get().results[0].rating);

        } else {
            System.out.println("Empty");
        }

        return myLocation;
    }
}
