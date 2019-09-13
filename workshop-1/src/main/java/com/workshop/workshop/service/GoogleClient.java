package com.workshop.workshop.service;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class GoogleClient
{
    private static final Logger LOG = LoggerFactory.getLogger(GoogleClient.class);

    private GeoApiContext context;

    @Value("${google.api}")
    private String API_KEY;

    // Documentations can be found
    // https://developers.google.com/places/web-service/details

    public Optional<PlacesSearchResponse> searchByInputText(String inputText){
        try {
//            initialise();
            return Optional.ofNullable(PlacesApi
                    .textSearchQuery(this.context, inputText)
                    .radius(100)
                    .await());
        } catch (ApiException e) {
            LOG.error("getPlacesSearchResult() -> ApiException: {}", e);
            e.printStackTrace();
        } catch (InterruptedException e) {
            LOG.error("getPlacesSearchResult() -> InterruptedException: {}", e);
            e.printStackTrace();
        } catch (IOException e) {
            LOG.error("getPlacesSearchResult() -> IOException: {}", e);
            e.printStackTrace();
        }

        return Optional.ofNullable(null);
    }

    public void initialise(){
        this.context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();

    }

    public void initialise(String api){
        this.context = new GeoApiContext.Builder()
                .apiKey(api)
                .build();

    }
}
