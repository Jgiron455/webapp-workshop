package com.reviewapi.reviewapi.service.implementation;

import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.reviewapi.reviewapi.service.GoogleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("googleServiceImpl")
public class GoogleServiceImpl implements GoogleService {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleServiceImpl.class);

    @Autowired
    private GoogleClient googleClient;

    private List<String> placeIdList;

    public Map<String, PlacesSearchResult> getPlacesSearchResult(String intputText){
        Map<String, PlacesSearchResult> placesSearchResultMap = new HashMap<>();
        LOG.info("getPlacesSearchResult() -> inputText: {}", intputText);

        Optional<PlacesSearchResponse> placesSearchResponse = googleClient.searchByInputText(intputText);
        LOG.info("getPlacesSearchResult() -> placesSearchResponse: {}", placesSearchResponse);

        if(placesSearchResponse.isPresent()) {
            placesSearchResultMap = mapPlaceSearchResult(placesSearchResultMap, placesSearchResponse.get());
            placesSearchResultMap.putAll(getPlacesNearby(placesSearchResponse.get()));

        }
        LOG.info("getPlacesSearchResult() -> placesSearchResultMap: {}", placesSearchResultMap);
        return placesSearchResultMap;
    }

    public Map<String, PlacesSearchResult> getPlacesNearby(PlacesSearchResponse placesSearchResponse){
        Map<String, PlacesSearchResult> placesSearchResultMap = new HashMap<>();
        LOG.info("getPlacesNearby() -> placesSearchResponse: {}", placesSearchResponse);
        Arrays.stream(placesSearchResponse.results).forEach(place -> {
            Optional<PlacesSearchResponse> neardyPlaceSearchResponse = googleClient.nearbySearch(place.geometry.location, 100);
            if(neardyPlaceSearchResponse.isPresent()) {
                placesSearchResultMap.putAll(mapPlaceSearchResult(placesSearchResultMap, neardyPlaceSearchResponse.get()));
            }
        });

        LOG.info("getPlacesNearby() -> placesSearchResultMap: {}", placesSearchResultMap);
        return placesSearchResultMap;
    }

    public Map<String, PlacesSearchResult> getPlacesSearchResult(double lat, double lng, int radius){
        Map<String, PlacesSearchResult> placesSearchResultMap = new HashMap<>();
        LOG.info("getPlacesSearchResult() -> lat: {}, lng: {}, radius: {}", lat, lng, radius);
        Optional<PlacesSearchResponse> placesSearchResponse = googleClient.nearbySearch(new LatLng(lat, lng), radius);

        LOG.info("getPlacesSearchResult() -> placesSearchResponse: {}", placesSearchResponse);
        if(placesSearchResponse.isPresent() && placesSearchResponse.get().results != null ) {
            this.placeIdList = new ArrayList<>();
            Arrays.stream(placesSearchResponse.get().results)
                    .filter(place -> place != null && place.placeId != null && !place.placeId.isEmpty())
                    .forEach(place -> {
                        placesSearchResultMap.put(place.placeId, place);
                        if(!this.placeIdList.contains(place.placeId)) {
                            this.placeIdList.add(place.placeId);
                        }
                    });
        }
        LOG.info("getPlacesSearchResult() -> placesSearchResultMap: {}", placesSearchResultMap);
        return placesSearchResultMap;
    }

    public Map<String, PlacesSearchResult> getPlacesSearchResult(String intputText, double lat, double lng, int radius){
        Map<String, PlacesSearchResult> placesSearchResultMap = new HashMap<>();
        LOG.info("getPlacesSearchResult() -> inputText: {}, lat: {}, lng: {}, radius: {}", intputText, lat, lng, radius);

        if(intputText != null && !intputText.isEmpty() && lat <= 0 && lng <= 0 && radius <= 0){
            return  getPlacesSearchResult(intputText);
        } else if((intputText == null || intputText.isEmpty()) && (lat > 0 && lng > 0 && radius > 0)){
            return getPlacesSearchResult(lat, lng, radius);
        } else {
            Optional<PlacesSearchResponse> placesSearchResponse = (lat > 0 && lng > 0 && radius > 0) ?
                    googleClient.searchByInputText(intputText, new LatLng(lat, lng), radius) :
                    googleClient.searchByInputText(intputText);
            LOG.info("getPlacesSearchResult() -> placesSearchResponse: {}", placesSearchResponse);

            if(placesSearchResponse.isPresent()) {
                placesSearchResultMap = mapPlaceSearchResult(placesSearchResultMap, placesSearchResponse.get());
            }
        }
        LOG.info("getPlacesSearchResult() -> placesSearchResultMap: {}", placesSearchResultMap);
        return placesSearchResultMap;
    }

    public Map<String, PlaceDetails> getMapPlaceDetails(Optional<Map<String, PlacesSearchResult>> placesSearchResultMap){
        Map<String, PlaceDetails> placeDetailsMap = new HashMap<>();
        LOG.info("getPlaceDetails() -> placesSearchResultMap: {}", placesSearchResultMap);
        if(placesSearchResultMap.isPresent() && !placesSearchResultMap.get().isEmpty()) {
            placesSearchResultMap.get().entrySet().stream().forEach(map -> {
                Optional<PlaceDetails> placeDetails = googleClient.getPlaceDetails(map.getKey());
                if(placeDetails.isPresent()) {
                    placeDetailsMap.put(map.getKey(),placeDetails.get());
                }
            });
        } else {
            LOG.warn("getPlaceDetails() -> Invalid placesSearchResultMap");
        }
        LOG.info("getPlaceDetails() -> placeDetailsMap: {}", placeDetailsMap);
        return placeDetailsMap;
    }

    public List<String> getPlaceIdList() {
        return this.placeIdList;
    }

    private Map<String, PlacesSearchResult>  mapPlaceSearchResult(Map<String, PlacesSearchResult> placesSearchResultMap,
                                                                  PlacesSearchResponse placesSearchResponse) {
        if(placesSearchResponse != null && placesSearchResponse.results != null ) {
            this.placeIdList = new ArrayList<>();
            Arrays.stream(placesSearchResponse.results)
                    .filter(place -> place != null && place.placeId != null && !place.placeId.isEmpty())
                    .forEach(place -> {
                        placesSearchResultMap.put(place.placeId, place);
                        if(!this.placeIdList.contains(place.placeId)) {
                            this.placeIdList.add(place.placeId);
                        }
                    });
        }
        return placesSearchResultMap;
    }
}
