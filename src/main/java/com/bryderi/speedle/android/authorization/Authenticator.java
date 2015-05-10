package com.bryderi.speedle.android.authorization;

import com.bryderi.speedle.android.model.User;
import com.bryderi.speedle.android.services.AsyncCallback;

/**
 * Created by Mattias on 21/04/15.
 */
public abstract class Authenticator {

    private User user;

    public abstract String getSpeedleToken();

    public abstract void setSpeedleToken(String speedleToken);

    public abstract void renewSpeedleToken(AsyncCallback callback);

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return getSpeedleToken() != null;
    }
}
