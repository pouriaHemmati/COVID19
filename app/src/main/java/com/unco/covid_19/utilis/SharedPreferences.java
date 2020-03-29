package com.unco.covid_19.utilis;

import android.content.Context;

import com.unco.covid_19.base.BaseActivity;


public class SharedPreferences {

    private Context context;
    private String infoToken = "infoToken";
    private String shpToAmount = "ToAmount";
    private String token = "";

    private static SharedPreferences cashoutsSaveFilter = null;

    public static SharedPreferences getCashoutsSaveFilter() {
        if (cashoutsSaveFilter == null)
            cashoutsSaveFilter = new SharedPreferences(BaseActivity.getContext());
        return cashoutsSaveFilter;
    }

    public SharedPreferences(Context context) {
        this.context = context;

    }

    // Token
    public void saveToken() {
        clearToken();
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(infoToken, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(shpToAmount, token);
        editor.apply();
    }

    public String loadToken() {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(infoToken, Context.MODE_PRIVATE);
        this.token = sharedPreferences.getString(shpToAmount, "");
        return null;
    }

    public void clearToken() {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(infoToken, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(shpToAmount);
        editor.apply();
    }

    public void setToken(String tokenKey) {
        this.token = tokenKey;
    }

    public String getToken() {
        return token;
    }


    //// userName

    private String infoUser = "infoUser";
    private String userName = "UserName";
    private String userNameAccount = "UserNameAccount";

    public void saveUserName() {
        clearUserName();
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(infoUser, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(userName, userNameAccount);
        editor.apply();
    }

    public void loadUserName() {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(infoUser, Context.MODE_PRIVATE);
        this.userNameAccount = sharedPreferences.getString(userName, "");
    }

    public void clearUserName() {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(infoUser, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(userName);
        editor.apply();
    }


    public void setUserName(String userKey) {
        this.userNameAccount = userKey;
    }

    public String getUserName() {
        return userNameAccount;
    }

}

