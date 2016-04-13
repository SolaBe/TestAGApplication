package com.example.sola2be.testagapplication;

import java.util.List;

/**
 * Created by Sola2Be on 13.04.2016.
 */
public interface HeroesDataInterface {

    void setData(List<HeroModel> list);

    void addHero(HeroModel hero);

    List<HeroModel> getData();

    int getSize();

    HeroModel getHero(int position);

    int getHeroPositionById(int id);

    void setSelectedItemId(int id);

    int getSelectedItemId();
}
