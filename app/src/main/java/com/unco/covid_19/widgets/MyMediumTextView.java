package com.unco.covid_19.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;


public class MyMediumTextView extends AppCompatTextView {


    public MyMediumTextView(Context context) {
        super(context);
        customize();
    }

    public MyMediumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        customize();
    }

    public MyMediumTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        customize();
    }

    private void customize() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "font/IRANSansMobile(FaNum)_Medium.ttf");
        this.setTypeface(typeface);

    }

}