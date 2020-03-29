package com.unco.covid_19.app.main;

import com.unco.covid_19.model.JResALL;

public interface IMainInteractor {
    void getAll (IgetAllFinishedListener igetAllFinishedListener);
    interface IgetAllFinishedListener{
        void successGetAll(JResALL success);
        void errorGetAll(String error);
    }


}
