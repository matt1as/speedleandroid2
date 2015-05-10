package com.bryderi.speedle.android.services;

import android.os.AsyncTask;
import android.util.Log;

import com.bryderi.speedle.android.model.SpeedleToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by Mattias on 21/04/15.
 */
public class FacebookAuthenticationService {


    public void postAccessToken(String accessToken, AsyncCallback callback) {
        new PostAccessToken(callback).execute(accessToken);
    }

    public String postAccessToken(String accessToken) {
        return getSpeedleToken(accessToken);
    }

    private String getSpeedleToken(String accessToken) {
        final String url = "http://api.speedle.se/auth/facebook/token/?access_token=" + accessToken;
        RestTemplate restTemplate = new RestTemplate();
        //Since this does not return a http/json we need to manually parse the string
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        String responseBody = restTemplate.postForObject(url, null, String.class);
        ObjectMapper mapper = new ObjectMapper();
        SpeedleToken speedleToken = null;
        try {
            speedleToken = mapper.readValue(responseBody, SpeedleToken.class);
        } catch (IOException e) {
            Log.e("FacebookAuthServ", e.getMessage(), e);
        }
        return speedleToken.getToken();
    }

    private class PostAccessToken extends AsyncTask<String, Void, String> {

        AsyncCallback delegate;

        public PostAccessToken(AsyncCallback callback) {
            this.delegate = callback;
        }

        @Override
        protected String doInBackground(String... params) {
            return getSpeedleToken(params[0]);
        }

        @Override
        protected void onPostExecute(String string) {
            delegate.processFinish(string);
        }

    }
}
