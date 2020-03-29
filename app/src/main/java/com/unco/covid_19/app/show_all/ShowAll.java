package com.unco.covid_19.app.show_all;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.unco.covid_19.R;
import com.unco.covid_19.app.show_all.adapter.Country_RecyclerAdapter;
import com.unco.covid_19.app.show_all.adapter.ModelItem_Country;
import com.unco.covid_19.app.show_all.adapter.OnItemClickListenerCountry;
import com.unco.covid_19.base.BaseActivity;
import com.unco.covid_19.base.BaseAppCompatActivity;
import com.unco.covid_19.model.JShowAll;
import com.unco.covid_19.utilis.IInternetConnection;
import com.unco.covid_19.utilis.NetworkAvailable;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowAll extends BaseAppCompatActivity implements OnItemClickListenerCountry {
    IShowAllInteractor iShowAllInteractor;
    // recycler courses
    ModelItem_Country modelItem_country;
    Country_RecyclerAdapter country_recyclerAdapter;
    ArrayList<JShowAll> jShowAllArrayList;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.menu)
    FloatingActionMenu menu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);
        ButterKnife.bind(this);
        menu.setVisibility(View.GONE);
        checkInterntConnection(new IInternetConnection() {
            @Override
            public void onRetry() {
                iShowAllInteractor = new ShowAllInteractor();
                iShowAllInteractor.ShowAll(new IShowAllInteractor.IShowAllFinishedListener() {
                    @Override
                    public void successShowAll(ArrayList<JShowAll> success) {

                        modelItem_country = new ModelItem_Country(success);
                        recycler();
                        menu.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void errorShowAll(String error) {

                    }
                });
            }
        });


        FloatingActionButton actionButtonup = findViewById(R.id.fab_up);
        actionButtonup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recycler_view
                        .getLayoutManager();
                layoutManager.scrollToPositionWithOffset(0, 0);
            }
        });

        FloatingActionButton actionButtondown = findViewById(R.id.fab_down);
        actionButtondown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycler_view.smoothScrollToPosition(country_recyclerAdapter.getItemCount() - 1);
            }
        });
        FloatingActionButton actionButtonabout = findViewById(R.id.fab_about);
        actionButtonabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAbout();
            }
        });
    }

    private void recycler(){

        country_recyclerAdapter = new Country_RecyclerAdapter(BaseActivity.getContext() , modelItem_country , this);
        recycler_view.setLayoutManager(new LinearLayoutManager(BaseActivity.getContext() , LinearLayoutManager.VERTICAL , false));
        recycler_view.setAdapter(country_recyclerAdapter);
    }
    @Override
    public void onClickLastCountry(String _id) {

    }

    private void dialogAbout(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_about);
        TextView textView =  dialog.findViewById(R.id.txt_link);
        textView.setOnClickListener(v->{
            openWeb();
        });


        dialog.show();
        Objects.requireNonNull(dialog.getWindow()).setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        View view = dialog.getWindow().getDecorView();
        view.setBackgroundResource(android.R.color.transparent);

    }

    private void openWeb(){
        Intent callIntent = new Intent(Intent.ACTION_VIEW);
        callIntent.setData(Uri.parse("http://pouriahemati.com"));
        startActivity(callIntent);
    }


}
