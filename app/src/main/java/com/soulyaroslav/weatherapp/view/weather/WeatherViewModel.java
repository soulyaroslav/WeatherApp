package com.soulyaroslav.weatherapp.view.weather;

import com.soulyaroslav.weatherapp.data.DayForecast;
import com.soulyaroslav.weatherapp.data.Forecast;
import com.soulyaroslav.weatherapp.data.WeatherService;
import com.soulyaroslav.weatherapp.view.base.ActivityViewModel;

import java.util.List;

import rx.Observer;

/**
 * Created by yaroslav on 6/22/17.
 */

public class WeatherViewModel extends ActivityViewModel<WeatherActivity> implements WeatherContract {

    private WeatherService service;
    private DayForecast dayForecast;

    public WeatherViewModel(WeatherActivity activity) {
        super(activity);
        init();
    }

    private void init() {
        service = new WeatherService();
        getWeatherForecast();
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

    public DayForecast getDayForecast() {
        return dayForecast;
    }
}
