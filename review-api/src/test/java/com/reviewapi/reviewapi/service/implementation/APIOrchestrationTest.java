package com.reviewapi.reviewapi.service.implementation;

import com.reviewapi.reviewapi.dto.MyLocationReviewDto;
import com.reviewapi.reviewapi.dto.enumeration.QueryEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class APIOrchestrationTest {

    @Mock
    MyLocationReviewServiceImpl myLocationReviewService;

    @Mock
    MyLocationReviewDto myLocationReviewDto;

    @InjectMocks
    APIOrchestration apiOrchestration;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getMyLocationReviewTestBySearchHasNoInputAndNoGeometry(){

        Optional<MyLocationReviewDto> expected = apiOrchestration.getMyLocationReview(QueryEnum.SEARCH, null, null, null, null);
        Assert.assertEquals("Should be empty", !expected.isPresent(), true);

    }

    @Test
    public void getMyLocationReviewTestByGoogleSearchHasInputAndGeometry(){

        when(myLocationReviewService.getGoogleMyLocationReview("Belfast", 0.1, 0.1, 10)).thenReturn(myLocationReviewDto);
        Optional<MyLocationReviewDto> expected = apiOrchestration.getMyLocationReview(QueryEnum.SEARCH, "Belfast", 0.1, 0.1, 10);
        Assert.assertEquals("Should be present", expected.isPresent(), true);

    }

    @Test
    public void getMyLocationReviewTestByGoogleSearchHasNoInputAndGeometry(){

        when(myLocationReviewService.getGoogleMyLocationReview(0.1, 0.1, 10)).thenReturn(myLocationReviewDto);
        Optional<MyLocationReviewDto> expected = apiOrchestration.getMyLocationReview(QueryEnum.SEARCH, null, 0.1, 0.1, 10);
        Assert.assertEquals("Should be present", expected.isPresent(), true);

    }

    @Test
    public void getMyLocationReviewTestByGoogleSearchHasInputAndNoGeometry(){

        when(myLocationReviewService.getGoogleMyLocationReview("Belfast")).thenReturn(myLocationReviewDto);
        Optional<MyLocationReviewDto> expected = apiOrchestration.getMyLocationReview(QueryEnum.SEARCH, "Belfast", null,  null, null);
        Assert.assertEquals("Should be present", expected.isPresent(), true);

    }
}