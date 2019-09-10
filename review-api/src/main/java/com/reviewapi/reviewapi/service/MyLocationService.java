package com.reviewapi.reviewapi.service;

import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResult;
import com.reviewapi.reviewapi.dto.MyLocationDto;
import com.reviewapi.reviewapi.dto.ReviewDto;

import java.util.List;
import java.util.Map;

public interface MyLocationService {

    List<MyLocationDto> generateMyLocationList(Map<String, PlacesSearchResult> googlePlacesSearchResultMap,
                                               Map<String, PlaceDetails> googlePlacesDetailstMap );

    List<MyLocationDto> getGoogleLocationList(List<String> placeIds, Map<String, PlacesSearchResult> placesSearchResultMap, Map<String, PlaceDetails> googlePlacesDetailstMap);

}
