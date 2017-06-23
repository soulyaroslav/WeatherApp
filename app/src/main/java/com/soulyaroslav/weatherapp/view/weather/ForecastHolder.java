package com.soulyaroslav.weatherapp.view.weather;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.soulyaroslav.weatherapp.data.Forecast;
import com.soulyaroslav.weatherapp.view.weather.animation.AnimationType;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by yaroslav on 6/22/17.
 */

public class ForecastHolder {
    private Forecast forecast;
    private ObservableBoolean animateDescription = new ObservableBoolean(false);
    private ObservableField<AnimationType> descriptionAnimationType = new ObservableField<>(AnimationType.TOP);
    // up animation flag
    private ObservableBoolean upIconAnimation = new ObservableBoolean(false);
    private ObservableInt upIconPosition = new ObservableInt();
    // up animate finish flag
    private ObservableBoolean upIconAnimateFinish = new ObservableBoolean(false);
    // down animation flag
    private ObservableInt downIconPosition = new ObservableInt();
    private ObservableBoolean downIconAnimation = new ObservableBoolean(false);
    // down animate finish flag
    private ObservableBoolean downIconAnimateFinish = new ObservableBoolean(false);
    // default animation
    // skip animation for first item
    private ObservableBoolean defaultIconUpAnimation = new ObservableBoolean(false);
    private ObservableInt skipItemPosition = new ObservableInt();


    public ForecastHolder(Forecast forecast) {
        this.forecast = forecast;
    }

    public void setDescriptionAnimationStartObservable(Observable<AnimationType> observable) {
        observable.subscribe(new Action1<AnimationType>() {
            @Override
            public void call(AnimationType type) {
                animateDescription.set(true);
                descriptionAnimationType.set(type);
            }
        });
    }

    public void setDefaultAnimationObservable(Observable<Integer> observable) {
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer position) {
                defaultIconUpAnimation.set(true);
                skipItemPosition.set(position);
            }
        });
    }

    public void setIconUpAnimationStartObservable(Observable<Integer> observable) {
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer position) {
                upIconAnimation.set(true);
                upIconPosition.set(position);
            }
        });
    }

    public void setIconUpAnimationFinishObservable(Observable<Integer> observable) {
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer position) {
                upIconAnimateFinish.set(true);
            }
        });
    }

    public void setIconDownAnimationObservable(Observable<Integer> observable) {
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer position) {
                downIconAnimation.set(true);
                downIconPosition.set(position);
            }
        });
    }

    public void setDownIconAnimationFinishObservable(Observable<Integer> observable) {
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer position) {
                downIconAnimateFinish.set(true);
            }
        });
    }

    public void setHolderResetObservable(Observable<Void> observable) {
        observable.subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                reset();
            }
        });
    }

    private void reset() {
        animateDescription.set(false);
        defaultIconUpAnimation.set(false);
        upIconAnimation.set(false);
        upIconAnimateFinish.set(false);
        downIconAnimation.set(false);
        downIconAnimateFinish.set(false);
    }

    public Forecast getForecast() {
        return forecast;
    }

    public ObservableBoolean getAnimateDescription() {
        return animateDescription;
    }

    public ObservableField<AnimationType> getDescriptionAnimationType() {
        return descriptionAnimationType;
    }

    public ObservableBoolean getUpIconAnimation() {
        return upIconAnimation;
    }

    public ObservableInt getUpIconPosition() {
        return upIconPosition;
    }

    public ObservableBoolean getUpIconAnimateFinish() {
        return upIconAnimateFinish;
    }

    public ObservableInt getDownIconPosition() {
        return downIconPosition;
    }

    public ObservableBoolean getDownIconAnimation() {
        return downIconAnimation;
    }

    public ObservableBoolean getDefaultIconUpAnimation() {
        return defaultIconUpAnimation;
    }

    public ObservableInt getSkipItemPosition() {
        return skipItemPosition;
    }

    public ObservableBoolean getDownIconAnimateFinish() {
        return downIconAnimateFinish;
    }
}
