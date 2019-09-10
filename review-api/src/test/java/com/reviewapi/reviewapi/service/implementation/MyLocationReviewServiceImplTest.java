package com.reviewapi.reviewapi.service.implementation;

import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResult;
import com.reviewapi.reviewapi.dto.MyLocationDto;
import com.reviewapi.reviewapi.dto.MyLocationReviewDto;
import com.reviewapi.reviewapi.dto.ReviewDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MyLocationReviewServiceImplTest {

    @Mock
    GoogleServiceImpl googleService;

    @Mock
    MyLocationServiceImpl myLocationServiceImpl;

    @Mock
    MyLocationReviewDto myLocationReviewDto;

    @Mock
    MyLocationDto myLocationDto;

    @InjectMocks
    MyLocationReviewServiceImpl myLocationReviewService;

    @Spy
    Map<String, List<ReviewDto>> yelpBusinessReviewsMap = new HashMap<>();

    @Spy
    Map<String, PlacesSearchResult> googlePlacesSearchResultMap = new HashMap<>();

    @Spy
    Map<String, PlaceDetails> googlePlacesDetailstMap = new HashMap<>();

    @Spy
    List<MyLocationDto> locationDtos = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        locationDtos.add(myLocationDto);
        verify(locationDtos).add(myLocationDto);

        when(myLocationDto.getName()).thenReturn("myLocationDtoName");
    }

    @Test
    public void getGoogleMyLocationReviewInputText() {

        when(googleService.getPlacesSearchResult("Belfast")).thenReturn(googlePlacesSearchResultMap);
        when(googleService.getMapPlaceDetails(Optional.ofNullable(googlePlacesSearchResultMap))).thenReturn(googlePlacesDetailstMap);
        when(myLocationServiceImpl.getGoogleLocationList(googleService.getPlaceIdList(), googlePlacesSearchResultMap, googlePlacesDetailstMap)).thenReturn(locationDtos);
        MyLocationReviewDto expected = myLocationReviewService.getGoogleMyLocationReview("Belfast");

        Assert.assertEquals("Should return correct name", expected.getMyLocationDtoList().get(0).getName() , "myLocationDtoName");
    }

    @Test
    public void getGoogleMyLocationReviewInputAndGeometry() {

        when(googleService.getPlacesSearchResult("Belfast", 0.1, 0.1, 10)).thenReturn(googlePlacesSearchResultMap);
        when(googleService.getMapPlaceDetails(Optional.ofNullable(googlePlacesSearchResultMap))).thenReturn(googlePlacesDetailstMap);
        when(myLocationServiceImpl.getGoogleLocationList(googleService.getPlaceIdList(), googlePlacesSearchResultMap, googlePlacesDetailstMap)).thenReturn(locationDtos);
        MyLocationReviewDto expected = myLocationReviewService.getGoogleMyLocationReview("Belfast", 0.1, 0.1, 10);

        Assert.assertEquals("Should return correct name", expected.getMyLocationDtoList().get(0).getName() , "myLocationDtoName");

    }


    @Test
    public void getGoogleMyLocationReviewGeometry() {

        when(googleService.getPlacesSearchResult( 0.1, 0.1, 10)).thenReturn(googlePlacesSearchResultMap);
        when(googleService.getMapPlaceDetails(Optional.ofNullable(googlePlacesSearchResultMap))).thenReturn(googlePlacesDetailstMap);
        when(myLocationServiceImpl.getGoogleLocationList(googleService.getPlaceIdList(), googlePlacesSearchResultMap, googlePlacesDetailstMap)).thenReturn(locationDtos);
        MyLocationReviewDto expected = myLocationReviewService.getGoogleMyLocationReview( 0.1, 0.1, 10);

        Assert.assertEquals("Should return correct name", expected.getMyLocationDtoList().get(0).getName() , "myLocationDtoName");

    }

}