package com.reviewapi.reviewapi.dto.enumeration;

public enum ReviewTypeEnum {
    GOOGLE("google"),
    YELP("yelp");

    private String reviewType;

    ReviewTypeEnum(String reviewType) {
        this.reviewType = reviewType;
    }

    public String getReviewType() {
        return reviewType;
    }
}


