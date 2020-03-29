package com.unco.covid_19.utilis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyGson {
    public static Gson create() {
        return new GsonBuilder().serializeNulls().create();
    }
}
