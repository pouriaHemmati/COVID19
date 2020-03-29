package com.unco.covid_19.app.show_all.adapter;

import com.unco.covid_19.model.JShowAll;

import java.util.ArrayList;



public class ModelItem_Country {


    ArrayList<JShowAll> jShowAlls;


    public ModelItem_Country( ) {


    }
    public ModelItem_Country(ArrayList jShowAlls ) {
        this.jShowAlls = jShowAlls;

    }


    public void setCountryList(ArrayList<JShowAll> jShowAlls) {
        this.jShowAlls = jShowAlls;
    }

    public ArrayList<JShowAll> jShowAlls() {
        return jShowAlls;
    }
}


