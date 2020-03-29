package com.unco.covid_19.app.show_all;


import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.unco.covid_19.base.BaseActivity;
import com.unco.covid_19.model.JShowAll;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ShowAllInteractor implements IShowAllInteractor {

    JShowAll jShowAll;
    JSONArray jsonArray;
    @Override
    public void ShowAll(IShowAllFinishedListener iShowAllFinishedListener) {
        String url = "https://corona.lmao.ninja/countries";
        RequestQueue requestQueue = BaseActivity.getInstance().getRequestQueue();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsonArray = new JSONArray(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                iShowAllFinishedListener.successShowAll(fetchData(jsonArray));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
            }
        });

        RetryPolicy mRetryPolicy = new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(mRetryPolicy);
        requestQueue.add(request);
    }

    private ArrayList<JShowAll> fetchData(JSONArray response) {
        ArrayList<JShowAll> jShowAllArrayList = new ArrayList<>();
        jShowAll = new JShowAll();
        try {
            for (int i=0; i < response.length();i++){
                JSONObject dataObject = response.getJSONObject(i);
                JShowAll jShowAll = new JShowAll();


                if (!dataObject.isNull("country")){
                jShowAll.country = dataObject.getString("country");
                    if (!dataObject.isNull("cases")){
                        jShowAll.cases = dataObject.getInt("cases");
                    }
                    if (!dataObject.isNull("deaths")){
                        jShowAll.deaths = dataObject.getInt("deaths");
                    }
                    if (!dataObject.isNull("todayCases")){
                        jShowAll.todayCases = dataObject.getInt("todayCases");
                    }
                    if (!dataObject.isNull("todayDeaths")){
                        jShowAll.todayDeaths = dataObject.getInt("todayDeaths");
                    }
                    if (!dataObject.isNull("active")){
                        jShowAll.active = dataObject.getInt("active");
                    }
                    if (!dataObject.isNull("critical")){
                        jShowAll.critical = dataObject.getInt("critical");
                    }
                    if (!dataObject.isNull("casesPerOneMillion")){
                        jShowAll.casesPerOneMillion = dataObject.getInt("casesPerOneMillion");
                    }
                    if (!dataObject.isNull("deathsPerOneMillion")){
                        jShowAll.deathsPerOneMillion = dataObject.getInt("deathsPerOneMillion");
                    }
                    if (!dataObject.isNull("recovered")){
                        jShowAll.recovered = dataObject.getInt("recovered");
                    }

                    JSONObject jCountryInfoObject;
                    jCountryInfoObject = dataObject.getJSONObject("countryInfo");
                    if (!jCountryInfoObject.isNull("_id")){
                        jShowAll.id = jCountryInfoObject.getInt("_id");
                    }
                    if (!jCountryInfoObject.isNull("flag")){
                        jShowAll.flag = jCountryInfoObject.getString("flag");
                    }
            }
                jShowAllArrayList.add(jShowAll);
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
        return jShowAllArrayList;
    }
}



