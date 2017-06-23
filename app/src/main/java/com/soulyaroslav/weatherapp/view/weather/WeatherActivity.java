package com.soulyaroslav.weatherapp.view.weather;

import android.os.Bundle;

import com.soulyaroslav.weatherapp.BR;
import com.soulyaroslav.weatherapp.R;
import com.soulyaroslav.weatherapp.databinding.WeatherActivityBinding;
import com.soulyaroslav.weatherapp.view.base.BindingActivity;

public class WeatherActivity extends BindingActivity<WeatherActivityBinding, WeatherViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public WeatherViewModel onCreate() {
        return new WeatherViewModel(this);
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.weather_activity;
    }
}
