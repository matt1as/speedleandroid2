package com.bryderi.speedle.android.fragments;

import android.content.Intent;
import android.os.Bundle;

import com.bryderi.speedle.android.activity.LoginActivity;

/**
 * Created by Mattias on 23/04/15.
 */
public class AuthenticatedFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent loginIntent = new Intent(this.getActivity(), LoginActivity.class);
        startActivityForResult(loginIntent, 0);
    }

}


