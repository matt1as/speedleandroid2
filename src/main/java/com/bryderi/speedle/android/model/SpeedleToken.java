package com.bryderi.speedle.android.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Mattias on 22/04/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpeedleToken {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
