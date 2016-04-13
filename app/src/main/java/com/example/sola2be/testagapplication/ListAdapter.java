package com.example.sola2be.testagapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sola2Be on 13.04.2016.
 */
public class ListAdapter extends BaseAdapter  implements Filterable{

    private List<HeroModel> heroes;
    private LayoutInflater inflater;
    private Context context;
    private ViewHolder holder;
    private ImageCache imageCache;
    private ListFilter filter;

    public ListAdapter(Context context, List<HeroModel> heroes){
        this.heroes = heroes;
        this.context = context;
        imageCache = ImageCache.init();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Filter getFilter() {
        if (filter == null)
            filter = new ListFilter();
        return filter;
    }

    public void removeFilter(List<HeroModel> list){
        heroes = list;
        notifyDataSetChanged();
    }

    class ViewHolder {
        ImageView imageView;
        TextView textViewName;
        TextView textViewDate;
    }


    @Override
    public int getCount() {
        return heroes.size();
    }

    @Override
    public HeroModel getItem(int position) {
        return heroes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return heroes.get(position).getItemId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        HeroModel hero = (HeroModel) getItem(position);
        if (v == null) {
            v = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) v.findViewById(R.id.image);
            holder.textViewName = (TextView) v.findViewById(R.id.textViewName);
            holder.textViewDate = (TextView) v.findViewById(R.id.textViewDate);
            v.setTag(holder);
        }
        else
            holder = (ViewHolder) v.getTag();
        imageCache.loadImage(holder.imageView,hero.getImgUrl());
        holder.textViewName.setText(String.format(context.getString(R.string.name_label),hero.getName()));
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
        String dateString = formatter.format(new Date(hero.getTime()));
        holder.textViewDate.setText(String.format(context.getString(R.string.date_label),dateString));
        return v;
    }

    class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            ArrayList<HeroModel> filteredHeroes = new ArrayList<>();

            constraint = constraint.toString().toLowerCase();
            for (int i = 0; i < heroes.size(); i++) {
                String name = heroes.get(i).getName();
                if (name.toLowerCase().contains(constraint.toString()))  {
                    filteredHeroes.add(heroes.get(i));
                }
            }

            results.count = filteredHeroes.size();
            results.values = filteredHeroes;
            Log.e("TAG", "Values "+ results.values.toString());
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            heroes = (List<HeroModel>) results.values;
            notifyDataSetChanged();
        }
    }

}
