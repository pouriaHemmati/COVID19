package com.unco.covid_19.model;

public class JResponse<T> {
    public int code = 1010;
    public boolean error = false;
    public String message = "";
    public T data;


    // used in socket.io request
    public String id;

    public static JResponse serverDown() {
        JResponse jResponse = new JResponse();
        jResponse.error = true;
        jResponse.code = -1;
        jResponse.message = "خطا در اتصال به سرور";
        return jResponse;
    }
    public static JResponse noInternet() {
        JResponse jResponse = new JResponse();
        jResponse.error = true;
        jResponse.code = -1;
        jResponse.message = "خطا در اتصال به اینترنت";
        return jResponse;
    }
}
