package com.soulyaroslav.weatherapp.manager.navigation;

import steelkiwi.com.navigationmanager.navigation.builder.ActivityPreference;
import steelkiwi.com.navigationmanager.navigation.builder.FragmentPreference;

/**
 * Created by yaroslav on 4/12/17.
 */

public interface NavigationController {
    void navigateTo(ActivityPreference activityPreference);
    void navigateTo(FragmentPreference fragmentPreference);
}
