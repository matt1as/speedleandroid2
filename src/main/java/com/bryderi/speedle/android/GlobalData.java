package com.bryderi.speedle.android;

import com.bryderi.speedle.android.authorization.Authenticator;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Mattias on 21/04/15.
 */
public class GlobalData {
    private static GlobalData instance;

    // Global variables

    private Authenticator authenticator;
    private GoogleApiClient googleApiClient;

    // Restrict the constructor from being instantiated
    private GlobalData() {

    }

    public static synchronized GlobalData getInstance() {
        if (instance == null) {
            instance = new GlobalData();
        }
        return instance;
    }

    public GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }

    public Authenticator getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(Authenticator authenticator) {
        this.authenticator = authenticator;
    }
}

