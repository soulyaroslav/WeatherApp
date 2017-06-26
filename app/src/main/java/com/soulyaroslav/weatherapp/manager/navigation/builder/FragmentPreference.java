package com.soulyaroslav.weatherapp.manager.navigation.builder;

import android.os.Bundle;

/**
 * Created by yaroslav on 6/26/17.
 */

public class FragmentPreference {
    private Class<?> screen;
    private Bundle bundle;
    private boolean isAddToBackStack;
    private boolean isAnimate;

    public FragmentPreference(Class<?> screen, Bundle bundle, boolean isAddToBackStack, boolean isAnimate) {
        this.screen = screen;
        this.bundle = bundle;
        this.isAddToBackStack = isAddToBackStack;
        this.isAnimate = isAnimate;
    }

    public Class<?> getScreen() {
        return screen;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public boolean isAddToBackStack() {
        return isAddToBackStack;
    }

    public boolean isAnimate() {
        return isAnimate;
    }
}
