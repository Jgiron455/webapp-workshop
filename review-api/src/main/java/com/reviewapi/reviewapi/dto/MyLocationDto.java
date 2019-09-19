package com.reviewapi.reviewapi.dto;

import com.reviewapi.reviewapi.dto.enumeration.ReviewTypeEnum;
import dev.morphia.annotations.*;
import dev.morphia.utils.IndexType;
import org.bson.types.ObjectId;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.Objects;

@Indexes(
        @Index(fields =
        @Field(value = "$**",
                type = IndexType.TEXT)))
@Entity("MyLocationDto")
public class MyLocationDto {

    @Property("reviewType")
    private String reviewType;

    @Property("alias")
    private String alias;

    @Property("name")
    private String name;

    @Property("isClosed")
    private Boolean isClosed;

    @Property("address")
    private String address;

    @Property("phoneNumber")
    private String phoneNumber;

    @Property("displayNumber")
    private String displayNumber;

    @Property("url")
    private String url;

    @Property("displayName")
    private List<String> displayName;

    @Property("ratings")
    private Double ratings;

    @Property("categories")
    private List<String> categories;

    @Property("photos")
    private List<String> photos;

    @Reference("reviews")
    private List<ReviewDto> reviews;

    public MyLocationDto() {
    }

    public MyLocationDto(ReviewTypeEnum reviewTypeEnum, String alias, String name, Boolean isClosed, String address, String phoneNumber, String displayNumber, String url, List<String> displayName, Double ratings, List<String> categories, List<String> photos, List<ReviewDto> reviews) {
        this.reviewType = reviewTypeEnum.getReviewType();
        this.alias = alias;
        this.name = name;
        this.isClosed = isClosed;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.displayNumber = displayNumber;
        this.url = url;
        this.displayName = displayName;
        this.ratings = ratings;
        this.categories = categories;
        this.photos = photos;
        this.reviews = reviews;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getClosed() {
        return isClosed;
    }

    public void setClosed(Boolean closed) {
        isClosed = closed;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDisplayNumber() {
        return displayNumber;
    }

    public void setDisplayNumber(String displayNumber) {
        this.displayNumber = displayNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getDisplayName() {
        return displayName;
    }

    public void setDisplayName(List<String> displayName) {
        this.displayName = displayName;
    }

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyLocationDto that = (MyLocationDto) o;
        return Double.compare(that.ratings, ratings) == 0 &&
                Objects.equals(reviewType, that.reviewType) &&
                Objects.equals(alias, that.alias) &&
                Objects.equals(name, that.name) &&
                Objects.equals(isClosed, that.isClosed) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(displayNumber, that.displayNumber) &&
                Objects.equals(url, that.url) &&
                Objects.equals(displayName, that.displayName) &&
                Objects.equals(categories, that.categories) &&
                Objects.equals(photos, that.photos) &&
                Objects.equals(reviews, that.reviews);
    }

    @Override
    public int hashCode() {

        return Objects.hash(reviewType, alias, name, isClosed, address, phoneNumber, displayNumber, url, displayName, ratings, categories, photos, reviews);
    }

    @Override
    public String toString() {
        return "MyLocationDto{" +
                "reviewType='" + reviewType + '\'' +
                ", alias='" + alias + '\'' +
                ", name='" + name + '\'' +
                ", isClosed=" + isClosed +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", displayNumber='" + displayNumber + '\'' +
                ", url=" + url +
                ", displayName=" + displayName +
                ", ratings=" + ratings +
                ", categories=" + categories +
                ", photos=" + photos +
                ", reviews=" + reviews +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static class Builder {

        private MyLocationDto obj = new MyLocationDto();

        public Builder withReviewType(ReviewTypeEnum reviewType){
            obj.reviewType = reviewType.getReviewType();
            return this;
        }

        public Builder withAlias(String alias){
            obj.alias = alias;
            return this;
        }

        public Builder withName(String name){
            obj.name = name;
            return this;
        }

        public Builder withIsClosed(Boolean isClosed){
            obj.isClosed = isClosed;
            return this;
        }

        public Builder withAddress(String address){
            obj.address = address;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber){
            obj.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withDisplayNumber(String displayNumber){
            obj.displayNumber = displayNumber;
            return this;
        }

        public Builder withUrl(String url){
            obj.url = url;
            return this;
        }

        public Builder withDisplayName(List<String> displayName){
            obj.displayName = displayName;
            return this;
        }

        public Builder withRatings(Double ratings){
            obj.ratings = ratings;
            return this;
        }

        public Builder withCategories(List<String> categories){
            obj.categories = categories;
            return this;
        }

        public Builder withPhotos(List<String> photos){
            obj.photos = photos;
            return this;
        }

        public Builder withReview(List<ReviewDto> reviews){
            obj.reviews = reviews;
            return this;
        }

        public MyLocationDto build() {return obj;}
    }
}

