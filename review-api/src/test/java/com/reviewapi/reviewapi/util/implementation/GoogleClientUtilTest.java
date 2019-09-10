package com.reviewapi.reviewapi.util.implementation;

import com.google.maps.model.OpeningHours;
import com.google.maps.model.Photo;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResult;
import com.reviewapi.reviewapi.dto.ReviewDto;
import com.reviewapi.reviewapi.util.GoogleClientUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;


public class GoogleClientUtilTest {

    @Mock
    PlacesSearchResult googleResult;

    @Mock
    PlaceDetails googleDetails;

    @Mock
    Photo photo;

    @Mock
    PlaceDetails.Review review;

    @Mock
    OpeningHours openingHours;

    @InjectMocks
    GoogleClientUtil googleClientUtil;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPlaceNameNull() {
        String expect = googleClientUtil.getPlaceName(googleResult, googleDetails );
        Assert.assertEquals("Should be Empty", expect, "");

    }


    @Test
    public void getPlaceNameFromSearchResult() {
        googleResult.name = "result";
        String expect = googleClientUtil.getPlaceName(googleResult, googleDetails );
        Assert.assertEquals("Should be result", expect, "result");
    }

    @Test
    public void getPlaceNameFromDetails() {
        googleDetails.name = "details";
        String expect = googleClientUtil.getPlaceName(googleResult, googleDetails );
        Assert.assertEquals("Should be details", expect, "details");
    }

    @Test
    public void getPlaceNameFromResultWithDetails() {
        googleResult.name = "result";
        googleDetails.name = "details";
        String expect = googleClientUtil.getPlaceName(googleResult, googleDetails );
        Assert.assertEquals("Should be result", expect, "result");
    }

    @Test
    public void getCategoriesEmpty() {
        List<String> categories = googleClientUtil.getCategories(googleResult);
        Assert.assertEquals("Should be empty", categories, Arrays.asList());
    }

    @Test
    public void getCategories() {
        String[] strArr = {"type"};
        googleResult.types = strArr;
        List<String> categories = googleClientUtil.getCategories(googleResult);
        Assert.assertEquals("Should be empty", categories, Arrays.asList("type"));
    }

    @Test
    public void getPhotosEmpty() {
        List<String> photos = googleClientUtil.getPhotos(googleDetails);
        Assert.assertEquals("Should be empty", photos, Arrays.asList());
    }

    @Test
    public void getPhotos() {
        String[] arr = {"htmlAttribution"};
        photo.htmlAttributions = arr;
        Photo[] photoArr = {photo};
        googleDetails.photos = photoArr;

        List<String> photos = googleClientUtil.getPhotos(googleDetails);
        Assert.assertEquals("Should be htmlAttribution", photos, Arrays.asList("htmlAttribution"));
    }

    @Test
    public void getReviewsEmpty() {
        List<ReviewDto> reviewDtoList = googleClientUtil.getReviews(googleDetails);
        Assert.assertEquals("Should be empty", reviewDtoList, Arrays.asList());
    }

    @Test
    public void getReviews() {
        review.authorName = "authorName";
        review.text = "text";
        review.rating = 4;
        PlaceDetails.Review[] reviews = {review};
        googleDetails.reviews = reviews;

        List<ReviewDto> reviewDtoList = googleClientUtil.getReviews(googleDetails);
        Assert.assertEquals("Should be review", reviewDtoList, Arrays.asList(ReviewDto.builder()
                .withId(null)
                .withRating(4.0)
                .withName("authorName")
                .withText("text")
                .withCreated("")
                .build()));
    }

    @Test
    public void isOpenNot() {
        Assert.assertEquals("Should be closed", googleClientUtil.isOpen(googleResult), false);
    }

    @Test
    public void isOpen() {
        openingHours.openNow = true;
        googleResult.openingHours = openingHours;
        Assert.assertEquals("Should be open", googleClientUtil.isOpen(googleResult), true);
    }
}