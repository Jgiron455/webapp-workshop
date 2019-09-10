package com.reviewapi.reviewapi.service.implementation;

import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResult;
import com.reviewapi.reviewapi.dto.enumeration.ReviewTypeEnum;
import com.reviewapi.reviewapi.dto.MyLocationDto;
import com.reviewapi.reviewapi.service.MyLocationService;
import com.reviewapi.reviewapi.util.GoogleClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyLocationServiceImpl implements MyLocationService {

    private static final Logger LOG = LoggerFactory.getLogger(MyLocationServiceImpl.class);

    @Autowired
    GoogleClientUtil googleClientUtil;

    public List<MyLocationDto> generateMyLocationList(
                                                      Map<String, PlacesSearchResult> googlePlacesSearchResultMap,
                                                      Map<String, PlaceDetails> googlePlacesDetailstMap ){
        LOG.info("generateMyLocationList() -> googlePlacesSearchResultMap: {} googlePlacesDetailstMap: {}",
               googlePlacesSearchResultMap, googlePlacesDetailstMap);
        List<MyLocationDto> myLocationDtos = new ArrayList<>();

        if(googlePlacesSearchResultMap != null && googlePlacesDetailstMap != null){
            myLocationDtos.addAll(getGoogleLocationList(
                    new ArrayList<>(googlePlacesSearchResultMap.keySet()),
                    googlePlacesSearchResultMap,
                    googlePlacesDetailstMap));
        }

        Collections.sort(myLocationDtos, (l1, l2) -> l1.getName().compareTo(l2.getName()));

        LOG.debug("generateMyLocationList() -> myLocationDtos: {}", myLocationDtos);
        return myLocationDtos;
    }


    public List<MyLocationDto> getGoogleLocationList(List<String> placeIds, Map<String, PlacesSearchResult> placesSearchResultMap, Map<String, PlaceDetails> googlePlacesDetailstMap){
        List<MyLocationDto> myLocationDtos = new ArrayList<>();

        if(placesSearchResultMap != null && googlePlacesDetailstMap != null) {
            LOG.debug("generateMyLocationList() -> placeIds: {}", placeIds);
            if (!placeIds.isEmpty()) {
                placeIds.forEach(placeId -> {
                    myLocationDtos.add(generateGoogleLocation(placesSearchResultMap.get(placeId), googlePlacesDetailstMap.get(placeId)));
                });
            }
        } else {
            LOG.warn("getGoogleLocationList() -> Invalid Google Data");
        }

        Collections.sort(myLocationDtos, (l1, l2) -> l1.getName().compareTo(l2.getName()));
        return myLocationDtos;
    }


    private MyLocationDto generateGoogleLocation(PlacesSearchResult googleResult, PlaceDetails googleDetails){
        LOG.debug("generateGoogleLocation() -> googleResult: {}, googleDetails: {}", googleResult, googleDetails);
        return  MyLocationDto.builder()
                .withReviewType(ReviewTypeEnum.GOOGLE)
                .withName(googleClientUtil.getPlaceName(googleResult, googleDetails))
                .withIsClosed(googleClientUtil.isOpen(googleResult))
                .withAddress(googleClientUtil.getStringValue(googleDetails.formattedAddress))
                .withPhoneNumber(googleClientUtil.getStringValue(googleDetails.formattedPhoneNumber))
                .withDisplayNumber(googleClientUtil.getStringValue(googleDetails.formattedPhoneNumber))
                .withDisplayName(Arrays.asList(googleResult.formattedAddress))
                .withRatings((double) googleResult.rating)
                .withCategories(googleClientUtil.getCategories(googleResult))
                .withReview(googleClientUtil.getReviews(googleDetails))
                .build();
    }


}
