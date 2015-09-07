package com.pierce.mvpgank.model.impl;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pierce.mvpgank.app.App;
import com.pierce.mvpgank.app.Config;
import com.pierce.mvpgank.model.MainModel;
import com.pierce.mvpgank.model.entity.*;
import com.pierce.mvpgank.model.entity.Error;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Author: pierce
 * Date: 2015/9/6
 */
public class MainModelImpl implements MainModel {
    private final String TAG=MainModelImpl.class.getSimpleName();
    @Override
    public void loadGank(final int state, int page) {
            String fuli="福利";
            try {
                fuli=URLEncoder.encode("福利","UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String url= Config.URL_BY_NEW+fuli+"/20/"+page;
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").serializeNulls().create();
                            Log.i(TAG, response.toString());
                            Gank gank=gson.fromJson(response.toString(),Gank.class);
                            gank.setState(state);
                            send(gank);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Error err=new Error(error.toString());
                            send(err);
                        }
                    }
            );
            App.getInstance().addToRequestQueue(request);
    }
    private void send(Object o){
        EventBus.getDefault().postSticky(o);
    }
}
