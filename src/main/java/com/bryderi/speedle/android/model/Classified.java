package com.bryderi.speedle.android.model;

import com.bryderi.speedle.android.R;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import me.alexrs.recyclerviewrenderers.interfaces.Renderable;


/**
 * Created by Mattias on 20/03/15.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Classified  implements Renderable {

    private String _id;
    private String name;
    private String description;
    private String price;
    private String email;
    private String ownerName;

    public Classified( )
    {
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
