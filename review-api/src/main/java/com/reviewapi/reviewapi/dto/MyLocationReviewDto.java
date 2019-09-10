package com.reviewapi.reviewapi.dto;

import java.util.List;
import java.util.Objects;

public class MyLocationReviewDto {

    private String requestQuery;

    private List<MyLocationDto> myLocationDtoList;

    public MyLocationReviewDto() {
    }

    public MyLocationReviewDto(String requestQuery, List<MyLocationDto> myLocationDtoList) {
        this.requestQuery = requestQuery;
        this.myLocationDtoList = myLocationDtoList;
    }

    public String getRequestQuery() {
        return requestQuery;
    }

    public void setRequestQuery(String requestQuery) {
        this.requestQuery = requestQuery;
    }

    public List<MyLocationDto> getMyLocationDtoList() {
        return myLocationDtoList;
    }

    public void setMyLocationDtoList(List<MyLocationDto> myLocationDtoList) {
        this.myLocationDtoList = myLocationDtoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyLocationReviewDto that = (MyLocationReviewDto) o;
        return Objects.equals(requestQuery, that.requestQuery) &&
                Objects.equals(myLocationDtoList, that.myLocationDtoList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(requestQuery, myLocationDtoList);
    }

    @Override
    public String toString() {
        return "MyLocationReviewDto{" +
                "requestQuery='" + requestQuery + '\'' +
                ", myLocationDtoList=" + myLocationDtoList +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static class Builder {

        private MyLocationReviewDto obj = new MyLocationReviewDto();

        public Builder withRequestQuery(String requestQuery){
            obj.requestQuery = requestQuery;
            return this;
        }

        public Builder withMyLocationDtoList(List<MyLocationDto> myLocationDtoList){
            obj.myLocationDtoList = myLocationDtoList;
            return this;
        }

        public MyLocationReviewDto build() {return obj;}
    }
}
