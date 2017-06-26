package com.soulyaroslav.weatherapp.manager.navigation.builder;

import android.os.Bundle;

import steelkiwi.com.navigationmanager.navigation.AnimationType;

/**
 * Created by yaroslav on 6/26/17.
 */

public class ActivityBuilder {
    private Class<?> screen;
    private Bundle bundle;
    private boolean isActivityRoot;
    private AnimationType animationType;

    public ActivityBuilder() {
        // empty constructor
    }

    public ActivityBuilder setScreen(Class<?> screen) {
        this.screen = screen;
        return this;
    }

    public ActivityBuilder setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

    public ActivityBuilder setActivityRoot(boolean isActivityRoot) {
        this.isActivityRoot = isActivityRoot;
        return this;
    }

    public ActivityBuilder setAnimationType(AnimationType animationType) {
        this.animationType = animationType;
        return this;
    }

    public ActivityPreference build() {
        return new ActivityPreference(screen, bundle, isActivityRoot, animationType);
    }
}
