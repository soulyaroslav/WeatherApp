package com.soulyaroslav.weatherapp.view.base;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.soulyaroslav.weatherapp.util.Constants;

/**
 * Created by yaroslav on 3/23/17.
 */

public abstract class ActivityViewModel<A extends AppCompatActivity> extends BaseObservable {

    private A activity;

    public ActivityViewModel(A activity) {
        this.activity = activity;
    }

    public void onStart() {
        Log.d(Constants.TAG, "onStart()");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(Constants.TAG, "onActivityResult()");
    }

    public void onResume() {
        Log.d(Constants.TAG, "onResume()");
    }
    public void onPause() {
        Log.d(Constants.TAG, "onPause()");
    }

    public void onStop() {
        Log.d(Constants.TAG, "onStop()");
    }

    public A getActivity() {
        return activity;
    }
}
