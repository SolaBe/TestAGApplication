package com.example.sola2be.testagapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sola2Be on 13.04.2016.
 */
public class HeroesData implements HeroesDataInterface {

    private List<HeroModel> heroesList;
    private int selectedItemId;
    private static HeroesData data;

    private HeroesData(){
        heroesList = new ArrayList<>();
    }

    public static HeroesData init(){
        if (data == null) {
            data = new HeroesData();
        }
        return data;
    }


    @Override
    public void setData(List<HeroModel> list) {
        heroesList = list;
    }

    @Override
    public void addHero(HeroModel hero) {
        if (heroesList != null) {
            heroesList.add(hero);
        }
        else {
            ArrayList<HeroModel> list = new ArrayList<>();
            list.add(hero);
            setData(list);
        }
    }

    @Override
    public List<HeroModel> getData() {
        return heroesList;
    }

    @Override
    public int getSize() {
        if (heroesList != null) {
            return heroesList.size();
        }
        else {
            List<HeroModel> list = new ArrayList<>();
            setData(list);
            return list.size();
        }
    }

    @Override
    public HeroModel getHero(int position) {
        if (heroesList != null) {
            if (position >= 0 || position < heroesList.size()) {
                return heroesList.get(position);
            }
        }
        return null;
    }

    @Override
    public int getHeroPositionById(int id) {
        for (int i = 0; i < heroesList.size(); i++) {
            if (heroesList.get(i).getItemId() == id) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void setSelectedItemId(int id) {
        selectedItemId = id;
    }

    @Override
    public int getSelectedItemId() {
        return selectedItemId;
    }


}
