package com.soulyaroslav.weatherapp.data;

import java.util.List;

/**
 * Created by yaroslav on 6/22/17.
 */

public class DayForecast {
    private List<Forecast> forecasts;

    public DayForecast(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }
}
