package com.bryderi.speedle.android.services;

import android.os.AsyncTask;
import android.util.Log;

import com.bryderi.speedle.android.model.User;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Mattias on 22/03/15.
 */
public class Users {

    public void getMe(String speedleToken, AsyncCallback callback) {
        new GetMe(this, callback).execute(speedleToken);
    }

    public User getMe(String speedleToken) {

        final String url = "http://api.speedle.se/api/users/me?access_token=" + speedleToken;
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }

    private class GetMe extends AsyncTask<String, Void, User> {
        AsyncCallback delegate;
        Users instance;

        public GetMe(Users instance, AsyncCallback asyncResponse) {
            delegate = asyncResponse;//Assigning call back interfacethrough constructor
            this.instance = instance;
        }

        @Override
        protected User doInBackground(String... params) {
            try {

                return instance.getMe(params[0]);
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(User user) {
            delegate.processFinish(user);
        }

    }


}
