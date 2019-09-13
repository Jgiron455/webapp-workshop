package com.reviewapi.reviewapi.service.implementation;

import com.reviewapi.reviewapi.dto.MyLocationReviewDto;
import com.reviewapi.reviewapi.dto.enumeration.QueryEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class APIOrchestration {

    private static final Logger LOG = LoggerFactory.getLogger(APIOrchestration.class);

    @Autowired
    private MyLocationReviewServiceImpl myLocationReviewServiceImpl;

    public Optional<MyLocationReviewDto> getMyLocationReview(QueryEnum queryEnum, String inputText, Double lat, Double lng, Integer radius){

        if(hasGeormetry(lat, lng, radius) && hasInputText(inputText)){
            return Optional.ofNullable(myLocationReviewServiceImpl.getGoogleMyLocationReview(inputText,lat, lng, radius));
        } else if(hasGeormetry(lat, lng, radius) && !hasInputText(inputText)){
            return Optional.ofNullable(myLocationReviewServiceImpl.getGoogleMyLocationReview(lat, lng, radius));
        } else if(!hasGeormetry(lat, lng, radius) && hasInputText(inputText)){
            return Optional.ofNullable(myLocationReviewServiceImpl.getGoogleMyLocationReview(inputText));
        } else {
            LOG.warn("getMyLocationReview() -> Invalid Params.");
            return Optional.ofNullable(null);
        }
    }

    private Boolean hasInputText(String inputText){
        return (inputText != null && !inputText.isEmpty());
    }

    private Boolean hasGeormetry(Double lat, Double lng, Integer radius){
        return (lat != null && lng != null && radius != null);
    }
}
