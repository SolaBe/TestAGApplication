package com.example.sola2be.testagapplication;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sola2Be on 12.04.2016.
 */
public class ContentDownloader extends AsyncTaskLoader<List<HeroModel>> {

    private final static String urlString = "http://others.php-cd.attractgroup.com/test.json";

    public ContentDownloader(Context context) {
        super(context);
    }

    @Override
    public List<HeroModel> loadInBackground() {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int status = urlConnection.getResponseCode();
            switch (status) {
                case 200 :
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder json = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        json.append(line);
                    }
                    urlConnection.disconnect();
                    JSONArray jsonArray = new JSONArray(json.toString());
                    List<HeroModel> heroesList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        HeroModel hero = new HeroModel();
                        JSONObject jsObject = (JSONObject) jsonArray.get(i);
                        hero.setItemId(jsObject.getInt("itemId"));
                        hero.setName(jsObject.getString("name"));
                        hero.setImgUrl(jsObject.getString("image"));
                        hero.setDescription(jsObject.getString("description"));
                        hero.setTime(jsObject.getLong("time"));
                        heroesList.add(hero);
                    }
                    return heroesList;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("Error ContentDownloader","MalformedURLException");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Error ContentDownloader","IOException");
        } catch (JSONException e) {
            Log.e("Error ContentDownloader","JSONException");
            e.printStackTrace();
        }

        return null;
    }
}
