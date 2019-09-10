package com.reviewapi.reviewapi.dto.enumeration;

public enum QueryEnum {
    SEARCH("search"),
    YELPSEARCH("yelpSearch"),
    GOOGLESEARCH("googleSearch");

    private String queryEnum;

    QueryEnum(String queryEnum) {
        this.queryEnum = queryEnum;
    }

    public String getQueryEnum() {
        return queryEnum;
    }

}
