package com.example.sola2be.testagapplication;

/**
 * Created by Sola2Be on 13.04.2016.
 */
public interface ListPresenterInterface {

    void setSelectedPosition(int position);

    void filterList(String str);

    void removeFilter();

    void setListAdapter(ListAdapter adapter);

    boolean isDataLoaded();
}
