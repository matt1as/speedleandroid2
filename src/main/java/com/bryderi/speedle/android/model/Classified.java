package com.bryderi.speedle.android.model;

import com.bryderi.speedle.android.R;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import me.alexrs.recyclerviewrenderers.interfaces.Renderable;


/**
 * Created by Mattias on 20/03/15.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Classified implements Renderable {

    private String _id;
    private String name;
    private String description;
    private String price;
    private String email;
    private String phoneNumber;
    private String ownerName;
    private String owner;
    private String[] thumbnails;
    private String[] tags;
    private String[] images;
    private String currency;

    public Classified() {
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String[] getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String[] thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Classified that = (Classified) o;

        if (!_id.equals(that._id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return _id.hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getRenderableId() {
        return R.layout.classified;
    }

}
