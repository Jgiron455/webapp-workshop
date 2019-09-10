package com.reviewapi.reviewapi.service.implementation;

import com.google.maps.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class GoogleServiceImplTest {

    @Mock
    GoogleClient googleClient;

    @Mock
    PlacesSearchResponse placesSearchResponse;

    @Mock
    PlacesSearchResult placesSearchResult;

    @Mock
    PlaceDetails placeDetails;

    @Mock
    Geometry geometry;

    @Mock
    LatLng latLng;

    @InjectMocks
    GoogleServiceImpl googleService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPlacesSearchResultSearchByInputText() {

        placesSearchResult.placeId = "placeID";
        placesSearchResult.name = "name";
        placesSearchResult.formattedAddress = "formattedAddress";
        placesSearchResult.rating = 4.5f;
        placesSearchResult.permanentlyClosed = false;
        latLng.lat = 0.1;
        latLng.lng = 0.1;
        geometry.location = latLng;
        placesSearchResult.geometry = geometry;
        placesSearchResponse.results = new PlacesSearchResult[]{placesSearchResult};

        when(googleClient.nearbySearch(new LatLng(0.1, 0.1), 100)).thenReturn(Optional.ofNullable(placesSearchResponse));
        when(googleClient.searchByInputText("Belfast")).thenReturn(Optional.ofNullable(placesSearchResponse));

        Map<String, PlacesSearchResult> expected = googleService.getPlacesSearchResult("Belfast");

        Assert.assertEquals("Should return place search result", expected.get("placeID").placeId, "placeID");
    }

    @Test
    public void getPlacesSearchResultNearbySearch() {
        placesSearchResult.placeId = "placeID";
        placesSearchResult.name = "name";
        placesSearchResult.formattedAddress = "formattedAddress";
        placesSearchResult.rating = 4.5f;
        placesSearchResult.permanentlyClosed = false;
        latLng.lat = 0.1;
        latLng.lng = 0.1;
        geometry.location = latLng;
        placesSearchResult.geometry = geometry;
        placesSearchResponse.results = new PlacesSearchResult[]{placesSearchResult};

        when(googleClient.nearbySearch(new LatLng(0.1, 0.1), 100)).thenReturn(Optional.ofNullable(placesSearchResponse));

        Map<String, PlacesSearchResult> expected = googleService.getPlacesSearchResult( 0.1, 0.1, 100);

        Assert.assertEquals("Should return place search result", String.valueOf(expected.get("placeID").geometry.location.lat), "0.1");
    }

    @Test
    public void getPlacesSearchResult2() {

        placesSearchResult.placeId = "placeID";
        placesSearchResult.name = "name";
        placesSearchResult.formattedAddress = "formattedAddress";
        placesSearchResult.rating = 4.5f;
        placesSearchResult.permanentlyClosed = false;
        latLng.lat = 0.1;
        latLng.lng = 0.1;
        geometry.location = latLng;
        placesSearchResult.geometry = geometry;
        placesSearchResponse.results = new PlacesSearchResult[]{placesSearchResult};

        when(googleClient.searchByInputText("Belfast", new LatLng(0.1, 0.1), 10)).thenReturn(Optional.ofNullable(placesSearchResponse));
        Map<String, PlacesSearchResult> expected1 = googleService.getPlacesSearchResult( "Belfast", 0.1, 0.1, 10);

        when(googleClient.nearbySearch(new LatLng(0.1, 0.1), 10)).thenReturn(Optional.ofNullable(placesSearchResponse));
        when(googleClient.searchByInputText("", new LatLng(0.1, 0.1),10)).thenReturn(Optional.ofNullable(placesSearchResponse));
        Map<String, PlacesSearchResult> expected2 = googleService.getPlacesSearchResult( "", 0.1, 0.1, 10);
        Map<String, PlacesSearchResult> expected3 = googleService.getPlacesSearchResult( null, 0.1, 0.1, 10);
        Map<String, PlacesSearchResult> expected4 = googleService.getPlacesSearchResult( null, 0, 0, 10);


        Assert.assertEquals("Should return place search result", String.valueOf(expected1.get("placeID").geometry.location.lat), "0.1");

        Assert.assertEquals("Should return place search result", String.valueOf(expected2.get("placeID").geometry.location.lat), "0.1");

        Assert.assertEquals("Should return place search result", String.valueOf(expected3.get("placeID").geometry.location.lat), "0.1");

        Assert.assertEquals("Should return place search result", expected4.isEmpty(), true);

    }

    @Test
    public void getMapPlaceDetails() {

        placeDetails.name = "name";
        placeDetails.placeId = "placeID";

        placesSearchResult.placeId = "placeID";
        placesSearchResult.name = "name";
        placesSearchResult.formattedAddress = "formattedAddress";
        placesSearchResult.rating = 4.5f;
        placesSearchResult.permanentlyClosed = false;
        latLng.lat = 0.1;
        latLng.lng = 0.1;
        geometry.location = latLng;
        placesSearchResult.geometry = geometry;
        Optional<Map<String, PlacesSearchResult>> placesSearchResultMap = Optional.ofNullable(new HashMap<>());
        placesSearchResultMap.get().put("placeID", placesSearchResult);


        // Optional<PlaceDetails> placeDetails = googleClient.getPlaceDetails(map.getKey());
        when(googleClient.getPlaceDetails("placeID")).thenReturn(Optional.ofNullable(placeDetails));
        Map<String, PlaceDetails> expected = googleService.getMapPlaceDetails(placesSearchResultMap);

        Assert.assertEquals("Should return place search result", expected.get("placeID").placeId, "placeID");

    }
}