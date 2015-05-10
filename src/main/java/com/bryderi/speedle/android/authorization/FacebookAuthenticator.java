package com.bryderi.speedle.android.authorization;

import com.bryderi.speedle.android.model.User;
import com.bryderi.speedle.android.services.AsyncCallback;
import com.bryderi.speedle.android.services.FacebookAuthenticationService;
import com.bryderi.speedle.android.services.Users;

/**
 * Created by Mattias on 21/04/15.
 */
public class FacebookAuthenticator extends Authenticator {
    private String facebookAccessToken;
    private String speedleToken;

    public FacebookAuthenticator(String accessToken, AsyncCallback callback) {
        this.facebookAccessToken = accessToken;
        renewSpeedleToken(callback);
    }

    @Override
    public String getSpeedleToken() {
        // if( speedleToken == null && facebookAccessToken != null ) {
        //     renewSpeedleToken( );
        // }
        return speedleToken;
    }

    @Override
    public void setSpeedleToken(String speedleToken) {
        this.speedleToken = speedleToken;
    }

    private void requestMe(String speedleToken, final AsyncCallback callback) {
        new Users().getMe(speedleToken, new AsyncCallback() {
            @Override
            public void processFinish(Object output) {
                setUser((User) output);
                callback.processFinish(output);
            }
        });

    }

    @Override
    public void renewSpeedleToken(final AsyncCallback callback) {
        new FacebookAuthenticationService().postAccessToken(this.facebookAccessToken, new AsyncCallback() {
            @Override
            public void processFinish(Object output) {
                speedleToken = (String) output;
                requestMe(speedleToken, callback);
            }


        });

    }
}
