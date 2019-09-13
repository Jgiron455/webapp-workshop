package com.reviewapi.reviewapi.service.implementation;

import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;

@Service
public class GoogleClient {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleClient.class);

    private GeoApiContext context;

    @Value("${google.api}")
    private String API_KEY;

    public Optional<PlaceDetails> getPlaceDetails(String id){
        try {
            return Optional.ofNullable(PlacesApi.placeDetails(this.context, id).await());
        } catch (ApiException e) {
            LOG.error("getPlaceDetails() -> ApiException: {}", e);
            e.printStackTrace();
        } catch (InterruptedException e) {
            LOG.error("getPlaceDetails() -> InterruptedException: {}", e);
            e.printStackTrace();
        } catch (IOException e) {
            LOG.error("getPlaceDetails() -> IOException: {}", e);
            e.printStackTrace();
        }
        return null;
    }

    public Optional<PlacesSearchResponse> searchByInputText(String inputText){
        try {
            initialise();
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

    public Optional<PlacesSearchResponse> searchByInputText(String inputText, LatLng latLng, int radius) {
        try {
            initialise();
        return Optional.ofNullable(
                PlacesApi
                        .textSearchQuery(this.context, inputText)
                        .location(latLng)
                        .radius(radius)
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

    public Optional<PlacesSearchResponse> nearbySearch(LatLng latLng, int radius) {
        try {
            initialise();
            return Optional.ofNullable(PlacesApi.nearbySearchQuery(this.context, latLng)
                    .radius(radius)
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

    private void initialise(){
        this.context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();

    }
}
