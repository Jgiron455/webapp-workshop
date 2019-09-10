package com.reviewapi.reviewapi.util;

import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResult;
import com.reviewapi.reviewapi.dto.ReviewDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GoogleClientUtil {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleClientUtil.class);

    public String getPlaceName(PlacesSearchResult googleResult, PlaceDetails googleDetails){
        if(googleResult != null && googleDetails != null) {

            String str1 = getStringValue(googleResult.name);
            String str2 = getStringValue(googleDetails.name);

            if(!str1.equalsIgnoreCase(str2)){
                LOG.warn("getPlaceName() -> Different name: {}, {}", str1, str2);
            }

            return str1.isEmpty() ? str2 : str1;

        } else {
            return "";
        }
    }

    public List<String> getCategories(PlacesSearchResult googleResult){
        List<String> categories = new ArrayList<>();

        if(googleResult != null && googleResult.types != null ){
            categories.addAll(Arrays.asList(googleResult.types));
        }
        LOG.debug("getGoogleCategories() -> categories: {}",  categories);
        return categories;
    }

    public List<String> getPhotos(PlaceDetails googleDetails){
        List<String> photoList = new ArrayList<>();
        if(googleDetails != null && googleDetails.photos != null){
            Arrays.stream(googleDetails.photos).forEach(photo -> {
                Arrays.stream(photo.htmlAttributions).forEach(htmlAttribute -> {
                    if(!htmlAttribute.isEmpty()) {
                        photoList.add(htmlAttribute);
                    }
                });
            });
        }
        LOG.debug("getGooglePhotos() -> photoList: {}",  photoList);
        return photoList;
    }

    public List<ReviewDto> getReviews(PlaceDetails googleDetails){
        List<ReviewDto> reviewList = new ArrayList<>();
        if(googleDetails != null && googleDetails.reviews != null){
            Arrays.stream(googleDetails.reviews).forEach(review -> {
                if(review != null) {
                    LOG.debug("getGoogleReviews() -> review: {}",  review);
                        reviewList.add(
                                ReviewDto.builder()
                                        .withRating((double)review.rating)
                                        .withText(getStringValue(review.text))
                                        .withName(getStringValue(review.authorName))
                                        .withCreated(getStringValue(review.time != null ? review.time.toString() : ""))
                                        .build());
                }
            });
        }
        LOG.debug("getGoogleReviews() -> reviewList: {}",  reviewList);
        return reviewList;
    }

    public Boolean isOpen(PlacesSearchResult googleResult){
        if(googleResult != null && googleResult.openingHours != null ) {
            return googleResult.openingHours.openNow != null ? googleResult.openingHours.openNow : false;
        }

        return false;
    }

    public String getStringValue(String string){
        return string != null && !string.isEmpty() ? string : "";
    }
}
