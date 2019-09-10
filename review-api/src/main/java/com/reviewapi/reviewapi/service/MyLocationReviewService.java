package com.reviewapi.reviewapi.service;

import com.reviewapi.reviewapi.dto.MyLocationReviewDto;

public interface MyLocationReviewService {

    MyLocationReviewDto getGoogleMyLocationReview(String inputText);

    MyLocationReviewDto getGoogleMyLocationReview(String inputText, double lat, double lng, int radius);

    MyLocationReviewDto getGoogleMyLocationReview(double lat, double lng, int radius);


}
