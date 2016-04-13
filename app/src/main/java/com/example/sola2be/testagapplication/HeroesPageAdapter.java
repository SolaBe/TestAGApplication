package com.example.sola2be.testagapplication;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HeroesPageAdapter extends PagerAdapter {

    private Context context;
    private List<HeroModel> heroes;
    private ImageCache imageCache;
    private LayoutInflater inflater;
    private ViewHolder holder;

    public HeroesPageAdapter(Context context, List<HeroModel> list) {
        this.context = context;
        this.heroes = list;
        imageCache = ImageCache.init();
        inflater = LayoutInflater.from(context);
    }

    class ViewHolder {
        ImageView imageView;
        TextView textViewName;
        TextView textViewDate;
        TextView textViewDescrition;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.page,null);
        holder = new ViewHolder();
        HeroModel hero = heroes.get(position);
        holder.imageView = (ImageView) view.findViewById(R.id.bigImage);
        holder.textViewName = (TextView) view.findViewById(R.id.textViewName);
        holder.textViewDate = (TextView) view.findViewById(R.id.textViewDate);
        holder.textViewDescrition = (TextView) view.findViewById(R.id.textViewDescription);
        imageCache.loadImage(holder.imageView,hero.getImgUrl());
        String name = context.getString(R.string.name_label);
        String date = context.getString(R.string.date_label);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
        String dateString = formatter.format(new Date(hero.getTime()));
        holder.textViewName.setText(String.format(name,hero.getName()));
        holder.textViewDate.setText(String.format(date,dateString));
        holder.textViewDescrition.setText(hero.getDescription());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public int getCount() {
        return heroes.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }


}