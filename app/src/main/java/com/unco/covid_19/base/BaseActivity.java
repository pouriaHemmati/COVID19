package com.unco.covid_19.base;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDexApplication;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class BaseActivity extends MultiDexApplication {

    private static BaseActivity instance = null;
    private static Context context;
    private static Activity currentActivity;
    private static Handler handler;
    private static LayoutInflater layoutInflater;
    private static TransitionInflater transitionInflater;
    public static final String TAG = BaseActivity.class.getSimpleName();

    private RequestQueue requestQueue;
    public boolean socketIsAuth = false;




    // app in background
    private Timer mActivityTransitionTimer;
    private TimerTask mActivityTransitionTimerTask;
    public boolean wasInBackground;
    private final long MAX_ACTIVITY_TRANSITION_TIME_MS = 3000;



    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();



        handler = new Handler() {
            @Override
            public void publish(LogRecord logRecord) {

            }

            @Override
            public void flush() {

            }

            @Override
            public void close() throws SecurityException {

            }

        };
        layoutInflater = LayoutInflater.from(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            transitionInflater = TransitionInflater.from(context);
        }

        // details socket

    }


    // app in background
    public void startActivityTransitionTimer() {
        this.mActivityTransitionTimer = new Timer();
        this.mActivityTransitionTimerTask = new TimerTask() {
            public void run() {
                BaseActivity.this.wasInBackground = true;

            }
        };

        this.mActivityTransitionTimer.schedule(mActivityTransitionTimerTask,
                MAX_ACTIVITY_TRANSITION_TIME_MS);
    }

    public void stopActivityTransitionTimer() {
        if (this.mActivityTransitionTimerTask != null) {
            this.mActivityTransitionTimerTask.cancel();
        }

        if (this.mActivityTransitionTimer != null) {
            this.mActivityTransitionTimer.cancel();
        }

        this.wasInBackground = false;
    }


    public static Context getContext() {
        if (currentActivity != null) {
            return currentActivity;
        }
        return context;
    }

    public static Context getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity activity) {
        currentActivity = activity;
    }

    public static synchronized BaseActivity getInstance() {
        if (instance == null) {
            instance = new BaseActivity();
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(BaseActivity.getContext());
        }
        return requestQueue;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public static TransitionInflater getTransitionInflater() {
        return transitionInflater;
    }

    @SuppressLint("ResourceType")
    public static Transition transition(@LayoutRes int res) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return transitionInflater.inflateTransition(res);
        }
        return null;
    }

    public static View inflateLayout(@LayoutRes int res) {
        return layoutInflater.inflate(res, null);
    }

    public static View inflateLayout(@LayoutRes int res, @Nullable ViewGroup root) {
        return layoutInflater.inflate(res, root);
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void removeFromRequestQue(String tag) {
        getRequestQueue().cancelAll(tag);
    }




}
