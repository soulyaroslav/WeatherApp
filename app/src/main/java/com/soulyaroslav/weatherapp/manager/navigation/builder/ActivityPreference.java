package com.soulyaroslav.weatherapp.manager.navigation.builder;

import android.os.Bundle;

import steelkiwi.com.navigationmanager.navigation.AnimationType;

/**
 * Created by yaroslav on 6/26/17.
 */

public class ActivityPreference {
    private Class<?> screen;
    private Bundle bundle;
    private boolean isActivityRoot;
    private AnimationType animationType;

    public ActivityPreference(Class<?> screen, Bundle bundle, boolean isActivityRoot, AnimationType animationType) {
        this.screen = screen;
        this.bundle = bundle;
        this.isActivityRoot = isActivityRoot;
        this.animationType = animationType;
    }

    public Class<?> getScreen() {
        return screen;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public boolean isActivityRoot() {
        return isActivityRoot;
    }

    public AnimationType getAnimationType() {
        return animationType;
    }
}
