package com.soulyaroslav.weatherapp.view.weather;

import android.databinding.ObservableBoolean;

import com.soulyaroslav.weatherapp.data.DayForecast;
import com.soulyaroslav.weatherapp.data.WeatherService;
import com.soulyaroslav.weatherapp.view.base.ActivityViewModel;

import rx.Observer;

/**
 * Created by yaroslav on 6/22/17.
 */

public class WeatherViewModel extends ActivityViewModel<WeatherActivity> implements WeatherContract {

    private WeatherService service;
    private DayForecast dayForecast;
    private ObservableBoolean isSearchHide;
    private ObservableBoolean isSearchResultHide;

    public WeatherViewModel(WeatherActivity activity) {
        super(activity);
        init();
    }

    private void init() {
        service = new WeatherService();
        initObservables();
        getWeatherForecast();
    }

    @Override
    public void initObservables() {
        isSearchHide = new ObservableBoolean(false);
        isSearchResultHide = new ObservableBoolean(true);
    }

    @Override
    public void getWeatherForecast() {
        service.getDayForecast().subscribe(new Observer<DayForecast>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                // show error
            }

            @Override
            public void onNext(DayForecast forecast) {
                dayForecast = forecast;
            }
        });
    }

    public ObservableBoolean getIsSearchHide() {
        return isSearchHide;
    }

    public ObservableBoolean getIsSearchResultHide() {
        return isSearchResultHide;
    }

    public DayForecast getDayForecast() {
        return dayForecast;
    }
}
