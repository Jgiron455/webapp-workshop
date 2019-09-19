package com.reviewapi.reviewapi.dto;

import javax.persistence.*;

@Entity
@Table(name = "user_review")
public class UserReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="forename")
    private String forename;

    @Column(name="surname")
    private String surname;

    @Column(name="email")
    private String email;

    @Column(name="comment")
    private String comment;

    @Column(name="rating")
    private Float rating;

    public UserReview() {
    }

    public UserReview(int id, String forename, String surname, String email, String comment, Float rating) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.comment = comment;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserReview that = (UserReview) o;

        if (id != that.id) return false;
        if (forename != null ? !forename.equals(that.forename) : that.forename != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        return rating != null ? rating.equals(that.rating) : that.rating == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (forename != null ? forename.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserReview{" +
                "id=" + id +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static class Builder{

        private UserReview obj = new UserReview();

        public Builder withId(int id){
            obj.id = id;
            return this;
        }

        public Builder withForename(String forename){
            obj.forename = forename;
            return this;
        }

        public Builder withSurname(String surname){
            obj.surname = surname;
            return this;
        }

        public Builder withEmail(String email){
            obj.email = email;
            return this;
        }

        public Builder withComment(String comment){
            obj.comment = comment;
            return this;
        }

        public Builder withRating(float rating){
            this.obj.rating = new Float(rating);
            return this;
        }

        public UserReview build(){return obj;}
    }
}
