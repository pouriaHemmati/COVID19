package com.unco.covid_19.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

public class MyEditText extends AppCompatEditText {

    public MyEditText(Context context) {
        super(context);
        customize();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        customize();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        customize();
    }

    private void customize() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "font/IRANSansMobile(FaNum).ttf");
        this.setTypeface(typeface);

    }

}