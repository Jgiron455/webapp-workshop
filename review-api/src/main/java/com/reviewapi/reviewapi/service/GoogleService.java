package com.reviewapi.reviewapi.service;

import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResult;

import java.util.*;

public interface GoogleService {

    Map<String, PlacesSearchResult> getPlacesSearchResult(String intputText);

    Map<String, PlacesSearchResult> getPlacesSearchResult(double lat, double lng, int radius);

    Map<String, PlacesSearchResult> getPlacesSearchResult(String intputText, double lat, double lng, int radius);

    Map<String, PlaceDetails> getMapPlaceDetails(Optional<Map<String, PlacesSearchResult>> placesSearchResultMap);
}
