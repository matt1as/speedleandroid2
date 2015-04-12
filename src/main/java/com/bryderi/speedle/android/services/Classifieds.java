package com.bryderi.speedle.android.services;

import android.os.AsyncTask;
import android.util.Log;

import com.bryderi.speedle.android.model.Classified;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Mattias on 22/03/15.
 */
public class Classifieds {

    public void getClassifieds(AsyncCallback callback) {
        new GetClassifieds(callback).execute();
    }



    private class GetClassifieds extends AsyncTask<Void, Void, Classified[]> {

        AsyncCallback delegate;

        public GetClassifieds(AsyncCallback asyncResponse) {
            delegate = asyncResponse;//Assigning call back interfacethrough constructor
        }


        @Override
        protected Classified[] doInBackground(Void... params) {
            try {
                final String url = "http://api.speedle.se/api/classifieds";
                RestTemplate restTemplate = new RestTemplate();

                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Classified[] classified = restTemplate.getForObject(url, Classified[].class);
                return classified;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Classified[] classifieds) {
            delegate.processFinish(classifieds);
        }

    }
}
