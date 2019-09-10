package com.reviewapi.reviewapi.dto;

import java.util.Objects;

public class ReviewDto {

    private String id;

    private Double rating;

    private String name;

    private String text;

    private String created;

    public ReviewDto() {
    }

    public ReviewDto(String id, Double rating, String name, String text, String created) {
        this.id = id;
        this.rating = rating;
        this.name = name;
        this.text = text;
        this.created = created;
    }

    public ReviewDto(Double rating, String name, String text, String created) {
        this.rating = rating;
        this.name = name;
        this.text = text;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewDto reviewDto = (ReviewDto) o;
        return Objects.equals(id, reviewDto.id) &&
                Objects.equals(rating, reviewDto.rating) &&
                Objects.equals(name, reviewDto.name) &&
                Objects.equals(text, reviewDto.text) &&
                Objects.equals(created, reviewDto.created);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, rating, name, text, created);
    }

    @Override
    public String toString() {
        return "ReviewDto{" +
                "id='" + id + '\'' +
                ", rating=" + rating +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", created='" + created + '\'' +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static class Builder {

        private ReviewDto obj = new ReviewDto();

        public Builder withId(String id){
            obj.id = id;
            return this;
        }

        public Builder withRating(Double rating){
            obj.rating = rating;
            return this;
        }

        public Builder withName(String name){
            obj.name = name;
            return this;
        }

        public Builder withText(String text){
            obj.text = text;
            return this;
        }

        public Builder withCreated(String created){
            obj.created = created;
            return this;
        }

        public ReviewDto build() {return obj;}
    }
}
