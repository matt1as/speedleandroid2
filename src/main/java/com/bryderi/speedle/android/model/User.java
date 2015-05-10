package com.bryderi.speedle.android.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Mattias on 22/04/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String _id;
    private String name;
    private String email;
    private Classified[] favourites;

    public User(String _id, String name, String email) {
        this._id = _id;
        this.name = name;
        this.email = email;
    }

    public User() {

    }

    public Classified[] getFavourites() {
        return favourites;
    }

    public void setFavourites(Classified[] favourites) {
        this.favourites = favourites;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!_id.equals(user._id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return _id.hashCode();
    }


}
