package com.soulyaroslav.weatherapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.soulyaroslav.weatherapp.R;

/**
 * Created by yaroslav on 6/22/17.
 */

public class ParallaxItemView extends View {

    public ParallaxItemView(Context context) {
        super(context);
    }

    public ParallaxItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParallaxItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflate();
    }

    private void inflate() {
        LayoutInflater.from(getContext()).inflate(R.layout.weather_day_layout, null);
    }
}
