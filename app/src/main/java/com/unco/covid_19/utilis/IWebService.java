package com.unco.covid_19.utilis;


import com.unco.covid_19.model.JResponse;

public interface IWebService<T> {
    void onSuccess(JResponse<T> response);
    void onFailure(JResponse<T> response);
}
