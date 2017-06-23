package com.soulyaroslav.weatherapp.data;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;

/**
 * Created by yaroslav on 6/22/17.
 */

public class Forecast {
    private String dayType;
    private String degree;
    private String wind;
    private String humidity;
    private String weather;
    private @DrawableRes int iconType;

    public Forecast(String dayType, String degree, String wind, String humidity, String weather, @DrawableRes int iconType) {
        this.dayType = dayType;
        this.degree = degree;
        this.wind = wind;
        this.humidity = humidity;
        this.weather = weather;
        this.iconType = iconType;
    }

    public String getDayType() {
        return dayType;
    }

    public String getDegree() {
        return degree;
    }

    public String getWind() {
        return wind;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWeather() {
        return weather;
    }

    public @DrawableRes int getIconType() {
        return iconType;
    }
}
