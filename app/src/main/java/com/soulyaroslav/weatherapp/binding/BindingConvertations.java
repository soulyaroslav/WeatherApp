package com.soulyaroslav.weatherapp.binding;

import android.databinding.BindingConversion;
import android.view.View;

/**
 * Created by yaroslav on 6/26/17.
 */

public final class BindingConvertations {

    @BindingConversion
    public static int convertBooleanToVisibility(boolean visible) {
        return visible ? View.GONE : View.VISIBLE;
    }
}
