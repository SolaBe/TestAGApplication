package com.example.sola2be.testagapplication;

/**
 * Created by Sola2Be on 13.04.2016.
 */
public class PagerPresenter {


    public PagerPresenter(ViewPagerInterface viewPagerInterface) {
        HeroesData data = HeroesData.init();
        viewPagerInterface.setList(data.getData());
        int id = data.getSelectedItemId();
        viewPagerInterface.setPage(data.getHeroPositionById(id));
    }

}
