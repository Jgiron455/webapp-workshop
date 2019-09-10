package com.reviewapi.reviewapi.controller;

import com.reviewapi.reviewapi.dto.MyLocationReviewDto;
import com.reviewapi.reviewapi.dto.enumeration.QueryEnum;
import com.reviewapi.reviewapi.service.implementation.APIOrchestration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class APIOrchestrationControllerTest {

    @Mock
    MyLocationReviewDto myLocationReviewDto;

    @Mock
    APIOrchestration apiOrchestration;

    @InjectMocks
    APIOrchestrationController apiOrchestrationController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getLocationsReviewValid() {
        when(apiOrchestration.getMyLocationReview(QueryEnum.SEARCH, "Belfast", 0.1, 0.1, 10 )).thenReturn(Optional.ofNullable(myLocationReviewDto));
        ResponseEntity<?> expected =  apiOrchestrationController.getLocationsReview("Belfast", 0.1, 0.1, 10);
        Assert.assertEquals("Should return 200", expected.getStatusCodeValue(), 200);
    }

    @Test
    public void getLocationsReviewInvalid() {

       ResponseEntity<?> expected =  apiOrchestrationController.getLocationsReview("Belfast", 0.1, 0.1, 10);
       Assert.assertEquals("Should return 404", expected.getStatusCodeValue(), 404);
    }

}