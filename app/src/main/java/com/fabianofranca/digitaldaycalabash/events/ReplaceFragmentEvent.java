package com.fabianofranca.digitaldaycalabash.events;

import android.support.v4.app.Fragment;

/**
 * Created by ffranca on 30/08/16.
 */
public class ReplaceFragmentEvent {

    private Fragment fragment;

    private boolean animate;

    public Fragment getFragment() {
        return fragment;
    }

    public boolean isAnimate() {
        return animate;
    }

    public ReplaceFragmentEvent(Fragment fragment, boolean animate) {
        this.fragment = fragment;
        this.animate = animate;
    }
}
