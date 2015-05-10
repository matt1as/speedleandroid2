package com.bryderi.speedle.android.services;

import android.os.AsyncTask;
import android.util.Log;

import com.bryderi.speedle.android.GlobalData;
import com.bryderi.speedle.android.model.Classified;
import com.bryderi.speedle.android.model.User;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Mattias on 22/03/15.
 */
public class Classifieds {

    public void getClassifieds(AsyncCallback callback) {
        new GetClassifieds(callback).execute();
    }

    public void getMyFavourites(AsyncCallback callback) {
        new GetMyFavourites(callback).execute();
    }

    public void updateMyFavourites(AsyncCallback callback) {
    }

    public void getMyClassifieds(AsyncCallback callback) {
        new GetMyClassifieds(callback).execute();
    }

    public void saveClassified(Classified classified, AsyncCallback callback) {
        new SaveClassified(callback).execute(classified);
    }

    public void createClassified(Classified classified, AsyncCallback callback) {
        new CreateClassified(callback).execute(classified);
    }

    public void getClassified(String id, AsyncCallback callback) {
        new GetClassified(callback).execute(id);
    }

    /*----------------------------------------
    Classes calling the Speedle API
    **/
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

    private class GetMyClassifieds extends AsyncTask<Void, Void, Classified[]> {

        AsyncCallback delegate;

        public GetMyClassifieds(AsyncCallback asyncResponse) {
            delegate = asyncResponse;//Assigning call back interfacethrough constructor
        }


        @Override
        protected Classified[] doInBackground(Void... params) {
            try {
                User user = GlobalData.getInstance().getAuthenticator().getUser();
                final String url = "http://api.speedle.se/api/classifieds/owner/" + user.get_id();
                RestTemplate restTemplate = new RestTemplate();

                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Classified[] classifieds = restTemplate.getForObject(url, Classified[].class);
                return classifieds;
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

    private class GetMyFavourites extends AsyncTask<Void, Void, Classified[]> {

        AsyncCallback delegate;

        public GetMyFavourites(AsyncCallback asyncResponse) {
            delegate = asyncResponse;//Assigning call back interfacethrough constructor
        }


        @Override
        protected Classified[] doInBackground(Void... params) {
            try {
                String speedleToken = GlobalData.getInstance().getAuthenticator().getSpeedleToken();
                final String url = "http://api.speedle.se/api/users/me?access_token=" + speedleToken;
                RestTemplate restTemplate = new RestTemplate();

                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                User user = restTemplate.getForObject(url, User.class);
                return user.getFavourites();
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

    private class GetClassified extends AsyncTask<String, Void, Classified> {

        AsyncCallback delegate;

        public GetClassified(AsyncCallback asyncResponse) {
            delegate = asyncResponse;//Assigning call back interfacethrough constructor
        }


        @Override
        protected Classified doInBackground(String... params) {
            try {
                final String url = "http://api.speedle.se/api/classifieds/" + params[0];
                RestTemplate restTemplate = new RestTemplate();

                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Classified classified = restTemplate.getForObject(url, Classified.class);
                return classified;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Classified classified) {
            delegate.processFinish(classified);
        }

    }

    private class SaveClassified extends AsyncTask<Classified, Void, Classified[]> {
        AsyncCallback delegate;

        public SaveClassified(AsyncCallback asyncResponse) {
            delegate = asyncResponse;//Assigning call back interfacethrough constructor
        }

        @Override
        protected Classified[] doInBackground(Classified... params) {
            try {
                String speedleToken = GlobalData.getInstance().getAuthenticator().getSpeedleToken();
                for (Classified classified : params) {
                    final String url = "http://api.speedle.se/api/classifieds/" + classified.get_id() + "?access_token=" + speedleToken;
                    RestTemplate restTemplate = new RestTemplate();

                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    restTemplate.put(url, classified);
                }
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return params;
        }

        @Override
        protected void onPostExecute(Classified[] classifieds) {
            delegate.processFinish(classifieds);
        }

    }

    private class CreateClassified extends AsyncTask<Classified, Void, Classified[]> {
        AsyncCallback delegate;

        public CreateClassified(AsyncCallback asyncResponse) {
            delegate = asyncResponse;//Assigning call back interfacethrough constructor
        }

        @Override
        protected Classified[] doInBackground(Classified... params) {
            try {
                String speedleToken = GlobalData.getInstance().getAuthenticator().getSpeedleToken();
                for (Classified classified : params) {
                    final String url = "http://api.speedle.se/api/classifieds?access_token=" + speedleToken;
                    RestTemplate restTemplate = new RestTemplate();

                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    restTemplate.postForLocation(url, classified);
                }
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return params;
        }

        @Override
        protected void onPostExecute(Classified[] classifieds) {
            delegate.processFinish(classifieds);
        }

    }
}
