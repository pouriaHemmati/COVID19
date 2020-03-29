package com.unco.covid_19.app.show_all.adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.unco.covid_19.R;
import com.unco.covid_19.base.BaseActivity;
import com.unco.covid_19.model.JShowAll;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class Country_RecyclerAdapter extends RecyclerView.Adapter<Country_RecyclerAdapter.ViewHolder> {
    private ModelItem_Country requestItems;
    private Context context;
    private OnItemClickListenerCountry onItemClickListenerCountry;

    public Country_RecyclerAdapter(Context context, ModelItem_Country requestItems,
                                   OnItemClickListenerCountry onItemClickListenerCountry) {
        this.context = context;
        this.requestItems = requestItems;
        this.onItemClickListenerCountry = onItemClickListenerCountry;

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public Picasso picasso;
        public TextView txt_recycler_country;
        public TextView txt_recycler_count;
        public ImageView flag;
        public ImageView expand_btn;
        public LinearLayout lay_expand_btn;
        public LinearLayout expandable_view;
        public TextView txt_recycler_show_cases;
        public TextView txt_recycler_show_deaths;
        public TextView txt_recycler_show_todaycases;
        public TextView txt_recycler_show_todaydeaths;
        public TextView txt_recycler_show_recovered;
        public TextView txt_recycler_show_casesPerOneMillion;
        public TextView txt_recycler_show_deathsPerOneMillion;






        public ViewHolder(View itemView) {
            super(itemView);
            picasso = Picasso.with(BaseActivity.getContext());
            txt_recycler_country = (TextView) itemView.findViewById(R.id.txt_recycler_country);
            txt_recycler_count = (TextView) itemView.findViewById(R.id.txt_recycler_count);
            flag = (ImageView) itemView.findViewById(R.id.flag);
            expand_btn = (ImageView) itemView.findViewById(R.id.expand_btn);
            lay_expand_btn = (LinearLayout) itemView.findViewById(R.id.lay_expand_btn);
            expandable_view = (LinearLayout) itemView.findViewById(R.id.expandable_view);
            txt_recycler_show_cases = (TextView) itemView.findViewById(R.id.txt_recycler_show_cases);
            txt_recycler_show_deaths = (TextView) itemView.findViewById(R.id.txt_recycler_show_deaths);
            txt_recycler_show_todaycases = (TextView) itemView.findViewById(R.id.txt_recycler_show_todaycases);
            txt_recycler_show_todaydeaths = (TextView) itemView.findViewById(R.id.txt_recycler_show_todaydeaths);
            txt_recycler_show_recovered = (TextView) itemView.findViewById(R.id.txt_recycler_show_recovered);
            txt_recycler_show_casesPerOneMillion = (TextView) itemView.findViewById(R.id.txt_recycler_show_casesPerOneMillion);
            txt_recycler_show_deathsPerOneMillion = (TextView) itemView.findViewById(R.id.txt_recycler_show_deathsPerOneMillion);





        }

        @Override
        public void onClick(View v) {

        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_country, parent, false);
        return new ViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArrayList<JShowAll> jShowAllArrayList = requestItems.jShowAlls;

        holder.txt_recycler_count.setText( " : " +String.valueOf(position+1) );

        if (!jShowAllArrayList.get(position).flag.equals("")){
            holder.picasso
                    .load(jShowAllArrayList.get(position).flag)
                    .into(holder.flag);
        }
        if (!jShowAllArrayList.get(position).country.equals("")) {
            holder.txt_recycler_country.setText(jShowAllArrayList.get(position).country);
        }
        if (jShowAllArrayList.get(position).cases > -1) {
            holder.txt_recycler_show_cases.setText(counter(String.valueOf(jShowAllArrayList.get(position).cases)));
        }

        if (jShowAllArrayList.get(position).deaths > -1) {
            holder.txt_recycler_show_deaths.setText(counter(String.valueOf(jShowAllArrayList.get(position).deaths)));
        }
        if (jShowAllArrayList.get(position).todayCases > -1) {
            holder.txt_recycler_show_todaycases.setText(counter(String.valueOf(jShowAllArrayList.get(position).todayCases)));
        }
        if (jShowAllArrayList.get(position).todayDeaths > -1) {
            holder.txt_recycler_show_todaydeaths.setText(counter(String.valueOf(jShowAllArrayList.get(position).todayDeaths)));
        }
        if (jShowAllArrayList.get(position).recovered > -1) {
            holder.txt_recycler_show_recovered.setText(counter(String.valueOf(jShowAllArrayList.get(position).recovered)));
        }
        if (jShowAllArrayList.get(position).casesPerOneMillion > -1) {
            holder.txt_recycler_show_casesPerOneMillion.setText(counter(String.valueOf(jShowAllArrayList.get(position).casesPerOneMillion)));
        }
        if (jShowAllArrayList.get(position).deathsPerOneMillion > -1) {
            holder.txt_recycler_show_deathsPerOneMillion.setText(counter(String.valueOf(jShowAllArrayList.get(position).deathsPerOneMillion)));
        }



//        holder.lay_see_courses_btn.setOnClickListener(v -> {
//            onItemClickListenerCountry.onClickLastCountry(String.valueOf(jShowAllArrayList.get(position).id));
//        });

        holder.lay_expand_btn.setOnClickListener(v -> {
            if (holder.expandable_view.getVisibility() == View.GONE) {
                expand(holder.expandable_view);
                holder.expand_btn.setRotation(0);
            } else {

                collapse(holder.expandable_view);
                holder.expand_btn.setRotation(180);

            }
        });

    }


    @Override
    public int getItemCount() {
        return requestItems.jShowAlls.size();
    }

    private String counter(String number){
        String s = number;
        String format = "0";
        s = s.replace(",", "");
        if (s.length() > 0) {
            DecimalFormat sdd = new DecimalFormat("#,###");
            Double doubleNumber = Double.parseDouble(s);
            format = sdd.format(doubleNumber);

        }
        return format;
    }

    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }


    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

}