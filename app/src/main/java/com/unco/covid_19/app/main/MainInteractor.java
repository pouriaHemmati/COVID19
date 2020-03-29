package com.unco.covid_19.app.main;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;
import com.unco.covid_19.base.BaseActivity;
import com.unco.covid_19.model.JResALL;
import com.unco.covid_19.model.JResponse;
import com.unco.covid_19.utilis.IWebService;
import com.unco.covid_19.utilis.WebService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainInteractor implements IMainInteractor {

    JResALL jResALL;

    @Override
    public void getAll(IgetAllFinishedListener igetAllFinishedListener) {
        String url = "https://corona.lmao.ninja/all";
        final JSONObject jsonBody = new JSONObject();
        RequestQueue requestQueue = BaseActivity.getInstance().getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    igetAllFinishedListener.successGetAll(fetchData(response));
                },
                error -> {
                    igetAllFinishedListener.errorGetAll("خطا در برقراری ارتباط");
                });
        RetryPolicy mRetryPolicy = new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(mRetryPolicy);
        requestQueue.add(jsonObjectRequest);
    }

    private JResALL fetchData(JSONObject response) {

        try {
            jResALL = new JResALL();
            if (!response.isNull("cases")){
                jResALL.cases = response.getInt("cases");
            }
            if (!response.isNull("deaths")){
                jResALL.deaths = response.getInt("deaths");
            }
            if (!response.isNull("recovered")){
                jResALL.recovered = response.getInt("recovered");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jResALL;
    }
}



