package com.soulyaroslav.weatherapp.binding;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.soulyaroslav.weatherapp.data.DayForecast;
import com.soulyaroslav.weatherapp.data.Forecast;
import com.soulyaroslav.weatherapp.util.Constants;
import com.soulyaroslav.weatherapp.view.ParallaxViewGroup;
import com.soulyaroslav.weatherapp.view.weather.animation.AnimationType;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by yaroslav on 6/22/17.
 */

public final class BindingAdapters {

    @BindingAdapter("items")
    public static void setItems(ParallaxViewGroup parallaxViewGroup, DayForecast dayForecast) {
        List<Forecast> forecasts = dayForecast.getForecasts();
        parallaxViewGroup.setForecasts(forecasts);
    }

    @BindingAdapter({"animateDescription", "descriptionAnimType"})
    public static void animateDescription(View view, boolean isAnimate, AnimationType animationType) {
        if(isAnimate) {
            int y = animationType == AnimationType.TOP ? Constants.Y : 0;
            if (animationType == AnimationType.TOP) {
                view.animate().translationY(y).setInterpolator(new DecelerateInterpolator()).setDuration(700).start();
            } else {
                view.animate().translationY(y).setInterpolator(new AccelerateInterpolator()).setDuration(700).start();
            }
        }
    }

    @BindingAdapter({"defaultIconUpAnimation", "skipItemPosition"})
    public static void defaultIconUpAnimation(final ImageView imageView, boolean isAnimate, int position) {
        if(isAnimate) {
            if(position > 0) {
                animateView(imageView);
            } else {
                imageView.setVisibility(View.VISIBLE);
            }
        }
    }

    private static void animateView(final ImageView imageView) {
        imageView.animate().translationY(-300).setDuration(100)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setVisibility(View.VISIBLE);
                    }
                }).start();
    }

    @BindingAdapter({"upIconAnimation", "upIconPosition"})
    public static void upIconAnimation(final ImageView imageView, boolean isAnimate, int position) {
        if(isAnimate) {
            if(position > 0) {
                imageView.animate().translationY(0).setDuration(1000).start();
            } else {
                imageView.animate().translationY(220).setDuration(700).start();
            }
        }
    }

    @BindingAdapter("upIconAnimateFinish")
    public static void upIconAnimateFinish(final ImageView imageView, boolean isFinish) {
        if(isFinish) {
            imageView.animate().translationY(220).setDuration(700).start();
        }
    }

    @BindingAdapter({"downIconAnimation", "downIconPosition"})
    public static void downIconAnimation(final ImageView imageView, boolean isAnimate, int position) {
        if(isAnimate) {
            if(position < 3) {
                imageView.animate().translationY(0).setDuration(1000).start();
            } else {
                imageView.animate().translationY(-300).setDuration(400).start();
            }
        }
    }

    @BindingAdapter("downIconAnimateFinish")
    public static void downIconAnimateFinish(final ImageView imageView, boolean isFinish) {
        if(isFinish) {
            imageView.animate().translationY(-300).setDuration(400).start();
        }
    }

    @BindingAdapter("image")
    public static void setImage(ImageView imageView, @DrawableRes int image) {
        Picasso.with(imageView.getContext()).load(image).into(imageView);
    }
}
