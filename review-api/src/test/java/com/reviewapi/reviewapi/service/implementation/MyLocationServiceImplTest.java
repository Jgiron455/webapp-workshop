package com.reviewapi.reviewapi.service.implementation;

import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResult;
import com.reviewapi.reviewapi.dto.MyLocationDto;
import com.reviewapi.reviewapi.dto.ReviewDto;
import com.reviewapi.reviewapi.util.GoogleClientUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MyLocationServiceImplTest {

    @Mock
    GoogleClientUtil googleClientUtil;


    @Mock
    ReviewDto reviewDto;

    @Mock
    PlacesSearchResult placesSearchResult;

    @Mock
    PlaceDetails placeDetails;

    @InjectMocks
    MyLocationServiceImpl myLocationService;

    @Spy
    Map<String, List<ReviewDto>> yelpBusinessReviewsMap = new HashMap<>();

    @Spy
    Map<String, PlacesSearchResult> googlePlacesSearchResultMap = new HashMap<>();

    @Spy
    Map<String, PlaceDetails> googlePlacesDetailstMap = new HashMap<>();

    @Spy
    List<ReviewDto> reviewDtos = new ArrayList<>();

    @Spy
    List<String> businessIds = new ArrayList<>();

    @Spy
    List<String> placeIds = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void generateMyLocationList() {

        reviewDtos.add(reviewDto);
        verify(reviewDtos).add(reviewDto);

        googlePlacesSearchResultMap.put("placeId", placesSearchResult);
        verify(googlePlacesSearchResultMap).put("placeId", placesSearchResult);

        googlePlacesDetailstMap.put("placeId", placeDetails);
        verify(googlePlacesDetailstMap).put("placeId", placeDetails);


        when(googleClientUtil.getPlaceName(placesSearchResult, placeDetails)).thenReturn("name");
        when(googlePlacesSearchResultMap.get("placeId")).thenReturn(placesSearchResult);
        when(googlePlacesDetailstMap.get("placeId")).thenReturn(placeDetails);

        List<MyLocationDto> expect = myLocationService.generateMyLocationList(googlePlacesSearchResultMap, googlePlacesDetailstMap );

        Assert.assertEquals("Should return a yelp review type", expect.get(0).getReviewType(), "google");
        Assert.assertEquals("Should return a yelp name", expect.get(0).getName(), "name");
    }

    @Test
    public void getGoogleLocationList() {

        placeIds.add("placeId");
        verify(placeIds).add("placeId");

        googlePlacesSearchResultMap.put("placeId", placesSearchResult);
        verify(googlePlacesSearchResultMap).put("placeId", placesSearchResult);

        googlePlacesDetailstMap.put("placeId", placeDetails);
        verify(googlePlacesDetailstMap).put("placeId", placeDetails);

        when(googleClientUtil.getPlaceName(placesSearchResult, placeDetails)).thenReturn("name");
        when(googlePlacesSearchResultMap.get("placeId")).thenReturn(placesSearchResult);
        when(googlePlacesDetailstMap.get("placeId")).thenReturn(placeDetails);

        List<MyLocationDto> expect = myLocationService.getGoogleLocationList(placeIds, googlePlacesSearchResultMap, googlePlacesDetailstMap );

        Assert.assertEquals("Should return a google review type", expect.get(0).getReviewType(), "google");
        Assert.assertEquals("Should return a google name", expect.get(0).getName(), "name");
    }
}