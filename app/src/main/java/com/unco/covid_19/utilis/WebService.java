package com.unco.covid_19.utilis;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unco.covid_19.base.BaseActivity;
import com.unco.covid_19.model.JResponse;


import org.json.JSONObject;


public class WebService {
    public static <T> void request(String url, final JSONObject body, final Class<T> clazz, final IWebService<T> iWebService) {
        if (!NetworkAvailable.isNetworkAvailable(BaseActivity.getContext())) {
            return;
        }

        final SharedPreferences sharedPreferences = SharedPreferences.getCashoutsSaveFilter();
        RequestQueue requestQueue = BaseActivity.getInstance().getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    Gson gson = MyGson.create();
                    JResponse<T> jResponse = gson.fromJson(response, new TypeToken<JResponse<T>>() {
                    }.getType());
                    jResponse.data = MyGson.create().fromJson(gson.toJson(jResponse.data), clazz);
                    iWebService.onSuccess(jResponse);
                } catch (Exception e){
e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (response != null && response.data != null) {
                    String s = new String(error.networkResponse.data);
                    Gson gson = MyGson.create();
                    try {
                        JResponse<T> jResponse = gson.fromJson(s, new TypeToken<JResponse<T>>() {
                        }.getType());

                        jResponse.data = MyGson.create().fromJson(gson.toJson(jResponse.data), clazz);
                        iWebService.onFailure(jResponse);
                    }catch (Exception e) {
                        iWebService.onFailure(JResponse.serverDown());
                    }
                } else {
                    iWebService.onFailure(JResponse.serverDown());
                }
            }
        }) ;

        // todo : temporarily timeout was increased due to delay in package/buy.
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(stringRequest);
    }
}


