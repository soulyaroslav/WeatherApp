package com.soulyaroslav.weatherapp.manager.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import steelkiwi.com.navigationmanager.BaseActivity;
import steelkiwi.com.navigationmanager.R;
import steelkiwi.com.navigationmanager.navigation.builder.ActivityPreference;
import steelkiwi.com.navigationmanager.navigation.builder.FragmentPreference;
import steelkiwi.com.navigationmanager.navigation.factory.ActivityFactory;
import steelkiwi.com.navigationmanager.navigation.factory.FragmentFactory;

/**
 * Created by yaroslav on 4/12/17.
 */

public class NavigationManager implements NavigationController {

    private BaseActivity activity;
    private @IdRes
    int container;
    private FragmentManager fragmentManager;
    private ActivityFactory activityFactory;
    private FragmentFactory fragmentFactory;
    private List<Class<?>> fragments = new ArrayList<>();
    private Class<?> currentScreen;

    public NavigationManager(BaseActivity activity) {
        this.activity = activity;
        this.fragmentManager = activity.getSupportFragmentManager();
        activityFactory = new ActivityFactory(activity);
        fragmentFactory = new FragmentFactory(activity);
    }

    @Override
    public void navigateTo(ActivityPreference activityPreference) {
        navigateToActivity(activityPreference);
    }

    @Override
    public void navigateTo(FragmentPreference fragmentPreference) {
        navigateToFragment(fragmentPreference);
    }

    private void navigateToActivity(ActivityPreference preferences) {
        currentScreen = preferences.getScreen();
        switchActivity(preferences);
        activity.hideKeyboard();
    }

    private void navigateToFragment(FragmentPreference fragmentPreference) {
        currentScreen = fragmentPreference.getScreen();
        switchFragment(fragmentPreference);
        fragments.add(fragmentPreference.getScreen());
    }

    private void switchFragment(FragmentPreference fragmentPreference) {
        if (isSameFragmentAlreadyPlaced(fragmentPreference.getScreen())) {
            return;
        }
        FragmentTransaction tran = fragmentManager.beginTransaction();
        Fragment fragment = fragmentFactory.getFragmentByName(fragmentPreference.getScreen());
        Bundle bundle = fragmentPreference.getBundle();
        if (bundle != null && !bundle.isEmpty()) {
            fragment.setArguments(bundle);
        }
        if (fragmentPreference.isAnimate()) {
            tran.setCustomAnimations(R.anim.right_to_left_in, R.anim.right_to_left_out, R.anim.left_to_right_in, R.anim.left_to_right_out);
        }
        if (fragmentPreference.isAddToBackStack()) {
            tran.replace(container, fragment, fragment.getClass().getSimpleName());
            tran.addToBackStack(fragment.getClass().getSimpleName());
        } else {
            tran.replace(container, fragment);
        }
        tran.commit();
    }

    private void switchActivity(ActivityPreference preferences) {
        Intent intent = activityFactory.getActivityByName(preferences.getScreen());
        if (preferences.isActivityRoot()) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        Bundle bundle = preferences.getBundle();
        if (bundle != null && !bundle.isEmpty()) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
        switch (preferences.getAnimationType()) {
            case FADE_TYPE:
                activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case RIGHT_TO_LEFT_TYPE:
                activity.overridePendingTransition(R.anim.right_to_left_in, R.anim.right_to_left_out);
                break;
            case LEFT_TO_RIGHT_TYPE:
                activity.overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out);
                break;
        }
    }

    private boolean isSameFragmentAlreadyPlaced(Class<?> requested) {
        Fragment existing = activity.getSupportFragmentManager().findFragmentById(container);
        if (existing != null) {
            if (existing.getClass().equals(requested)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Clear all fragment from our stack
     * */
    public void clearScreenStack() {
        fragments.clear();
    }

    /**
     * Clear fragment by position from our stack
     * */
    public void removeFragmentsFromStack(int position) {
        fragments.remove(position);
    }
    /**
     * Method for checking if screen is current
     * @param screen instance that need to check
     * @return flag if screen is current
     * */
    public boolean isCurrentScreen(Class<?> screen) {
        return currentScreen == screen;
    }

    /**
     * Set container id recourse for putting fragment inside
     * @param container container id recourse
     * */
    public void setFragmentContainer(@IdRes int container) {
        this.container = container;
    }

    /**
     * Set FragmentManager for handle replace logic in the
     * activity(SupportFragmentManager) and fragment(ChildFragmentManager)
     * @param fragmentManager fragment manager instance
     * */
    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }
}
