package com.pierce.mvpgank.app;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Author: pierce
 * Date: 2015/9/6
 */
public class App extends Application {
    private final static String TAG="App";
    private static App mInstance;
    private RequestQueue mRequestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }
    public static synchronized App getInstance(){
        return mInstance;
    }
    public RequestQueue getRequestqueue(){
        if (mRequestQueue==null)
            mRequestQueue=Volley.newRequestQueue(this);
        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> request,String tag){
        request.setTag(TextUtils.isEmpty(tag)?TAG:tag);
        getRequestqueue().add(request);
    }
    public <T> void addToRequestQueue(Request<T> request){
        request.setTag(TAG);
        getRequestqueue().add(request);
    }
    public void cancelRequests(String tag){
        if (!tag.isEmpty())
            getRequestqueue().cancelAll(tag);
    }

}
