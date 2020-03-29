package com.unco.covid_19.app.show_all;

import com.unco.covid_19.model.JShowAll;

import java.util.ArrayList;

public interface IShowAllInteractor {
    void ShowAll(IShowAllFinishedListener iShowAllFinishedListener);
    interface IShowAllFinishedListener{
        void successShowAll(ArrayList<JShowAll> success);
        void errorShowAll(String error);
    }


}
