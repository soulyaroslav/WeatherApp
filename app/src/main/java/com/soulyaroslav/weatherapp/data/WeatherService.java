package com.soulyaroslav.weatherapp.data;

import com.soulyaroslav.weatherapp.R;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by yaroslav on 6/22/17.
 */

public class WeatherService {

    public Observable<DayForecast> getDayForecast() {
        return Observable.just(new DayForecast(getForecasts()));
    }

    private List<Forecast> getForecasts() {
        List<Forecast> forecasts = new ArrayList<>();
        forecasts.add(new Forecast("Morning", "-1", "E 7 mph", "91%", "Sunny", R.mipmap.mostly_sunny));
        forecasts.add(new Forecast("Day", "+3", "M 5 mph", "45%", "Mostly Sunny", R.mipmap.mostly_sunny));
        forecasts.add(new Forecast("Evening", "-1", "E 7 mph", "91%", "Rain", R.mipmap.rain));
        forecasts.add(new Forecast("Night", "-1", "E 7 mph", "91%", "Cloudy", R.mipmap.snow));
        return forecasts;
    }
}
