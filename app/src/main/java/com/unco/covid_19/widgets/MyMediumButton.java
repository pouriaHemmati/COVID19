package com.unco.covid_19.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

public class MyMediumButton extends AppCompatButton {


    public MyMediumButton(Context context) {
        super(context);
        customize();
    }

    public MyMediumButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        customize();
    }

    public MyMediumButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        customize();
    }

    private void customize() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "font/IRANSansMobile(FaNum)_Medium.ttf");
        this.setTypeface(typeface);

    }

}