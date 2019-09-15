package com.reviewapi.reviewapi.service.implementation;

import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResult;
import com.reviewapi.reviewapi.dto.MyLocationDto;
import com.reviewapi.reviewapi.dto.MyLocationReviewDto;
import com.reviewapi.reviewapi.service.MyLocationReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MyLocationReviewServiceImpl implements MyLocationReviewService {

    private static final Logger LOG = LoggerFactory.getLogger(MyLocationReviewServiceImpl.class);

    @Autowired
    private GoogleServiceImpl googleServiceImpl;

    @Autowired
    private MyLocationServiceImpl myLocationServiceImpl;

    @Autowired
    private MongoDBClientImpl mongoDBClientImpl;


    public MyLocationReviewDto getGoogleMyLocationReview(String inputText){
        LOG.info("getGoogleMyLocationReview() -> inputText: {}", inputText);
        List<MyLocationDto> myLocationDtos = new ArrayList<>();

        Optional<Map<String, PlacesSearchResult>> googlePlacesSearchResultMap = Optional.ofNullable(googleServiceImpl.getPlacesSearchResult(inputText));
        Optional<Map<String, PlaceDetails>> googlePlacesDetailsMap = Optional.ofNullable(googleServiceImpl.getMapPlaceDetails(googlePlacesSearchResultMap));

        if(googlePlacesSearchResultMap.isPresent() && googlePlacesDetailsMap.isPresent()) {
            myLocationDtos = myLocationServiceImpl.getGoogleLocationList(googleServiceImpl.getPlaceIdList(), googlePlacesSearchResultMap.get(), googlePlacesDetailsMap.get());
        }

        LOG.info("getGoogleMyLocationReview() -> myLocationDtos: {}", myLocationDtos);
        return new MyLocationReviewDto(inputText, myLocationDtos);
    }

    public MyLocationReviewDto getGoogleMyLocationReview(String inputText, double lat, double lng, int radius){
        LOG.info("getGoogleMyLocationReview() -> inputText: {}, lat: {}, lng: {}, radius: {}", inputText, lat, lng, radius);
        List<MyLocationDto> myLocationDtos = new ArrayList<>();

        Optional<Map<String, PlacesSearchResult>> googlePlacesSearchResultMap = Optional.ofNullable(googleServiceImpl.getPlacesSearchResult(inputText,lat,lng,radius));
        Optional<Map<String, PlaceDetails>> googlePlacesDetailsMap = Optional.ofNullable(googleServiceImpl.getMapPlaceDetails(googlePlacesSearchResultMap));

        if(googlePlacesSearchResultMap.isPresent() && googlePlacesDetailsMap.isPresent()) {

            myLocationDtos = myLocationServiceImpl.getGoogleLocationList(
                    googleServiceImpl.getPlaceIdList(), googlePlacesSearchResultMap.get(), googlePlacesDetailsMap.get());

        }

        LOG.info("getGoogleMyLocationReview() -> myLocationDtos: {}", myLocationDtos);
        return new MyLocationReviewDto(inputText, myLocationDtos);
    }

    public MyLocationReviewDto getGoogleMyLocationReview(double lat, double lng, int radius){
        LOG.info("getGoogleMyLocationReview() ->  lat: {}, lng: {}, radius: {}", lat, lng, radius);
        List<MyLocationDto> myLocationDtos = new ArrayList<>();

        Optional<Map<String, PlacesSearchResult>> googlePlacesSearchResultMap = Optional.ofNullable(googleServiceImpl.getPlacesSearchResult(lat,lng,radius));
        Optional<Map<String, PlaceDetails>> googlePlacesDetailsMap = Optional.ofNullable(googleServiceImpl.getMapPlaceDetails(googlePlacesSearchResultMap));

        if(googlePlacesSearchResultMap.isPresent() && googlePlacesDetailsMap.isPresent()) {

            myLocationDtos = myLocationServiceImpl.getGoogleLocationList(googleServiceImpl.getPlaceIdList(), googlePlacesSearchResultMap.get(), googlePlacesDetailsMap.get());

        }

        LOG.info("getGoogleMyLocationReview() -> myLocationDtos: {}", myLocationDtos);
        return new MyLocationReviewDto(getGeometry(lat, lng, radius), myLocationDtos);
    }

    private String getGeometry(double lat, double lng, int radius){
        return String.valueOf(lat) + " " + String.valueOf(lng) + " " + String.valueOf(radius);
    }
}
