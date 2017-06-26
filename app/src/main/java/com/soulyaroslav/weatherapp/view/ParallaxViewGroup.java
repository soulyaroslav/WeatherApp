package com.soulyaroslav.weatherapp.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.soulyaroslav.weatherapp.R;
import com.soulyaroslav.weatherapp.data.Forecast;
import com.soulyaroslav.weatherapp.databinding.WeatherDayLayoutBinding;
import com.soulyaroslav.weatherapp.view.weather.Direction;
import com.soulyaroslav.weatherapp.view.weather.ForecastHolder;
import com.soulyaroslav.weatherapp.view.weather.animation.AnimationType;
import com.soulyaroslav.weatherapp.view.weather.animation.OnAnimationListener;
import com.soulyaroslav.weatherapp.view.weather.animation.OnResetListener;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by yaroslav on 6/21/17.
 */

public class ParallaxViewGroup extends ViewGroup {

    private final static int ITEMS_SIZE = 3;
    // gesture monitoring
    private GestureDetector gestureDetector;
    // views list
    private List<ForecastHolder> holders;
    // direction to handle right position of view to expand it
    private Direction direction = Direction.TOP;
    // current position for view
    private int expandViewPosition = 1;
    // view animation offset
    private int yOffset;
    // flag for check if animation finish
    private boolean isExpandFinish = true;

    public ParallaxViewGroup(Context context) {
        this(context, null);
    }

    public ParallaxViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParallaxViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        gestureDetector = new GestureDetector(context, new ScrollTouchListener());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec, heightMeasureSpec), measureHeight(widthMeasureSpec, heightMeasureSpec));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private int measureWidth(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int width = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int childWidth = child.getMeasuredWidth();
            width += childWidth;
        }
        return modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width;
    }

    private int measureHeight(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int height = 0;
        int childCount = getChildCount();
        if(childCount != 0) {
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                int childHeight = child.getMeasuredHeight();
                height += childHeight;
            }
            height = height / childCount;
        }
        return modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childTop = 0;
        int startOffset = getHeight() / 2;
        int offset = startOffset / ITEMS_SIZE;
        yOffset = -offset * 2;
        int height = getHeight();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(0, childTop, child.getMeasuredWidth(), childTop + height);
            childTop += increaseStartOffset(i, startOffset);
        }
    }

    private int increaseStartOffset(int position, int startOffset) {
        int offset;
        if(position == 0) {
            offset = startOffset;
        } else {
            offset = startOffset / ITEMS_SIZE;
        }
        return offset;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    private void animateViewsTop() {
        onDescriptionAnimating(expandViewPosition, AnimationType.TOP);
        if(expandViewPosition == 1) {
            onWeatherIconStartUpAnimating(expandViewPosition);
            onWeatherIconStartUpAnimating(expandViewPosition - 1);
        } else {
            onWeatherIconStartUpAnimating(expandViewPosition);
            onWeatherIconFinishUpAnimating(expandViewPosition - 1);
        }
    }

    private void animateViewsDown() {
        onWeatherIconStartDownAnimating(expandViewPosition);
        if(expandViewPosition == getChildCount() - 1) {
            onWeatherIconStartDownAnimating(expandViewPosition);
            onWeatherIconStartDownAnimating(expandViewPosition - 1);
        } else {
            onWeatherIconStartDownAnimating(expandViewPosition - 1);
            onWeatherIconFinishDownAnimating(expandViewPosition);
        }
    }

    private class ScrollTouchListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if(isExpandFinish) {
                View view = getChildAt(expandViewPosition);
                if (direction == Direction.TOP) {
                    onHolderReset();
                    animateTop(view);
                } else {
                    animateDown(view);
                }
            }
            return true;
        }
    }

    private void animateTop(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", yOffset);
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                isExpandFinish = false;
                animateViewsTop();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isExpandFinish = true;
                if (expandViewPosition < getChildCount() - 1) {
                    expandViewPosition++;
                } else {
                    direction = Direction.DOWN;
                }
            }
        });
        animator.start();
    }

    private void animateDown(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", 0);
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                isExpandFinish = false;
                animateViewsDown();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isExpandFinish = true;
                onDescriptionAnimating(expandViewPosition, AnimationType.DOWN);
                if (expandViewPosition > 1) {
                    expandViewPosition--;
                } else {
                    direction = Direction.TOP;
                }
            }
        });
        animator.start();
    }

    public void setForecasts(List<Forecast> forecasts) {
        holders = new ArrayList<>();
        for(int i = 0; i < forecasts.size(); i++) {
            WeatherDayLayoutBinding binding = WeatherDayLayoutBinding.inflate(LayoutInflater.from(getContext()), this, true);
            binding.root.setBackgroundResource(getBackground(i));
            ForecastHolder holder = new ForecastHolder(forecasts.get(i));
            holders.add(holder);
            binding.setHolder(holder);
            // animate default view positions
            onDefaultWeatherIconAnimating(i);
        }
        // animate first description
        onDescriptionAnimating(0, AnimationType.TOP);
    }

    private @ColorRes int getBackground(int position) {
        switch (position) {
            case 0:
                return R.color.background1;
            case 1:
                return R.color.background2;
            case 2:
                return R.color.background3;
            case 3:
                return R.color.background4;
        }
        return 0;
    }

    private Observable<AnimationType> getOnDescriptionAnimationStartObservable(final AnimationType type) {
        return Observable.create(new Observable.OnSubscribe<AnimationType>() {
            @Override
            public void call(final Subscriber<? super AnimationType> subscriber) {
                OnAnimationListener listener = new OnAnimationListener() {
                    @Override
                    public void onAnimate() {
                        subscriber.onNext(type);
                    }
                };
                listener.onAnimate();
            }
        });
    }

    private Observable<Integer> getIconUpAnimationStartObservable(final int position) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(final Subscriber<? super Integer> subscriber) {
                OnAnimationListener listener = new OnAnimationListener() {
                    @Override
                    public void onAnimate() {
                        subscriber.onNext(position);
                    }
                };
                listener.onAnimate();
            }
        });
    }

    private Observable<Integer> getIconUpAnimationFinishObservable() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(final Subscriber<? super Integer> subscriber) {
                OnAnimationListener listener = new OnAnimationListener() {
                    @Override
                    public void onAnimate() {
                        subscriber.onNext(null);
                    }
                };
                listener.onAnimate();
            }
        });
    }

    private Observable<Integer> getDefaultAnimationObservable(final int position) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(final Subscriber<? super Integer> subscriber) {
                OnAnimationListener listener = new OnAnimationListener() {
                    @Override
                    public void onAnimate() {
                        subscriber.onNext(position);
                    }
                };
                listener.onAnimate();
            }
        });
    }

    private Observable<Integer> getIconDownAnimationObservable(final int position) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(final Subscriber<? super Integer> subscriber) {
                OnAnimationListener listener = new OnAnimationListener() {
                    @Override
                    public void onAnimate() {
                        subscriber.onNext(position);
                    }
                };
                listener.onAnimate();
            }
        });
    }

    private Observable<Integer> getIconDownAnimationFinishObservable(final int position) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(final Subscriber<? super Integer> subscriber) {
                OnAnimationListener listener = new OnAnimationListener() {
                    @Override
                    public void onAnimate() {
                        subscriber.onNext(position);
                    }
                };
                listener.onAnimate();
            }
        });
    }

    private Observable<Void> getHolderResetObservable() {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                OnResetListener listener = new OnResetListener() {
                    @Override
                    public void onReset() {
                        subscriber.onNext(null);
                    }
                };
                listener.onReset();
            }
        });
    }

    private void onDescriptionAnimating(int position, AnimationType type) {
        ForecastHolder holder = getHolder(position);
        holder.setDescriptionAnimationStartObservable(getOnDescriptionAnimationStartObservable(type));
    }

    private void onDefaultWeatherIconAnimating(int position) {
        ForecastHolder holder = getHolder(position);
        holder.setDefaultAnimationObservable(getDefaultAnimationObservable(position));
    }

    private void onWeatherIconStartUpAnimating(int position) {
        ForecastHolder holder = getHolder(position);
        holder.setIconUpAnimationStartObservable(getIconUpAnimationStartObservable(position));
    }

    private void onWeatherIconFinishUpAnimating(int position) {
        ForecastHolder holder = getHolder(position);
        holder.setIconUpAnimationFinishObservable(getIconUpAnimationFinishObservable());
    }

    private void onWeatherIconStartDownAnimating(int position) {
        ForecastHolder holder = getHolder(position);
        holder.setIconDownAnimationObservable(getIconDownAnimationObservable(position));
    }

    private void onWeatherIconFinishDownAnimating(int position) {
        ForecastHolder holder = getHolder(position);
        holder.setDownIconAnimationFinishObservable(getIconDownAnimationFinishObservable(position));
    }

    private void onHolderReset() {
        for(int i = 0; i < holders.size(); i++) {
            ForecastHolder holder = getHolder(i);
            holder.setHolderResetObservable(getHolderResetObservable());
        }
    }

    private ForecastHolder getHolder(int position) {
        return holders.get(position);
    }
}
