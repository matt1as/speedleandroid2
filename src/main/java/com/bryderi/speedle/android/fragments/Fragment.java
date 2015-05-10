package com.bryderi.speedle.android.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bryderi.speedle.android.activity.ActionBarActivity;

/**
 * Created by Mattias on 27/04/15.
 */
public abstract class Fragment extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater layoutInfalter, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(layoutInfalter, container, savedInstanceState);
        ((ActionBarActivity) getActivity()).setVisibleFragment(this);
        return view;
    }
}
