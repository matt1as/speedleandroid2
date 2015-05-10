package com.bryderi.speedle.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.bryderi.speedle.android.GlobalData;
import com.bryderi.speedle.android.R;
import com.bryderi.speedle.android.authorization.Authenticator;
import com.bryderi.speedle.android.authorization.FacebookAuthenticator;
import com.bryderi.speedle.android.services.AsyncCallback;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class LoginActivity extends ActionBarActivity {

    @InjectView(R.id.connectWithFbButton)
    LoginButton facebookLoginButton;
    private String TAG = this.getClass().getCanonicalName();
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (GlobalData.getInstance().getAuthenticator() != null) {
            setResult(0);
            finish();
            return;
        }

        FacebookSdk.sdkInitialize(getApplicationContext());

        if (AccessToken.getCurrentAccessToken() != null) {
            final Authenticator authenticator = new FacebookAuthenticator(AccessToken.getCurrentAccessToken().getToken(),
                    new AsyncCallback() {

                        @Override
                        public void processFinish(Object output) {
                            finish();
                            return;
                        }
                    });
            GlobalData.getInstance().setAuthenticator(authenticator);

        } else {
            setContentView(R.layout.activity_login);
            ButterKnife.inject(this);

            // Callback registration
            registerFacebookCallbacks();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setResult(0);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void registerFacebookCallbacks() {
        callbackManager = CallbackManager.Factory.create();

        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GlobalData.getInstance().setAuthenticator(new FacebookAuthenticator(loginResult.getAccessToken().getToken(), new AsyncCallback() {
                    @Override
                    public void processFinish(Object output) {
                        finish();
                    }
                }));
            }

            @Override
            public void onCancel() {
                Log.i(TAG, "Cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.e(TAG, "Login error", exception);
            }
        });
    }
}
