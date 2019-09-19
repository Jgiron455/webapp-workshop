package com.workshop.workshop.model;

public class MyLocation {

    private String name;

    private float rating;

    public MyLocation() {
    }

    public MyLocation(String name, float rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyLocation that = (MyLocation) o;

        if (Float.compare(that.rating, rating) != 0) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MyLocation{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}

