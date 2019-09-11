package com.workshop.workshop.service;

import com.google.maps.model.*;
import com.workshop.workshop.service.GoogleClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class GoogleClientTest {

    GoogleClient googleClient;

    @Before
    public void setUp() throws Exception {
     googleClient = new GoogleClient();
     // Passing the API Key Temporary
     googleClient.initialise("");

    }

    // This would be an integration test and not a Unit Test
    // Propose is to test that you are able to use the Google Place Api
    @Test
    public void searchByInputText() throws Exception {
        Optional<PlacesSearchResponse> placesSearchResponse = googleClient.searchByInputText("Belfast");

        if(placesSearchResponse.isPresent()){
            System.out.println(placesSearchResponse.get().results.length);
            Assert.assertEquals("Should return place search result",placesSearchResponse.get().results.length, 1);
        }

    }

}