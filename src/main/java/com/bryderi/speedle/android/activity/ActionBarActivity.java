package com.bryderi.speedle.android.activity;


import com.bryderi.speedle.android.fragments.Fragment;

/**
 * Created by Mattias on 27/04/15.
 */
public class ActionBarActivity extends android.support.v7.app.ActionBarActivity {

    private Fragment currentFragment;

    //public Fragment getVisibleFragment() {
    //    return this.currentFragment;
    //}

    public void setVisibleFragment(Fragment fragment) {
        this.currentFragment = fragment;
    }

    ;


}
