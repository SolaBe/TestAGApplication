package com.example.sola2be.testagapplication;

import android.content.Context;

import java.util.List;

/**
 * Created by Sola2Be on 13.04.2016.
 */
public interface ListViewInterface {

    void setList(List<HeroModel> list);

    void showProgressBar();

    void hideProgressBar();

    void showErrorMessage();

    Context getViewContext();
}
