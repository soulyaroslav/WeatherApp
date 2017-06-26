package com.soulyaroslav.weatherapp.manager.navigation.builder;

import android.os.Bundle;

/**
 * Created by yaroslav on 6/26/17.
 */

public class FragmentBuilder {
    private Class<?> screen;
    private Bundle bundle;
    private boolean isAddToBackStack;
    private boolean isAnimate;

    public FragmentBuilder() {
        // empty constructor
    }

    public FragmentBuilder setScreen(Class<?> screen) {
        this.screen = screen;
        return this;
    }

    public FragmentBuilder setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

    public FragmentBuilder setIsAddToBackStack(boolean isAddToBackStack) {
        this.isAddToBackStack = isAddToBackStack;
        return this;
    }

    public FragmentBuilder setIsAnimate(boolean isAnimate) {
        this.isAnimate = isAnimate;
        return this;
    }

    public FragmentPreference build() {
        return new FragmentPreference(screen, bundle, isAddToBackStack, isAnimate);
    }
}
