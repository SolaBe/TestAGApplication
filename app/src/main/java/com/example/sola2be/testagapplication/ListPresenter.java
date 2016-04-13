package com.example.sola2be.testagapplication;


import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.List;

/**
 * Created by Sola2Be on 13.04.2016.
 */
public class ListPresenter implements LoaderManager.LoaderCallbacks<List<HeroModel>>,ListPresenterInterface {

    private final static int JSON_LOADER = 1;
    private ListViewInterface listViewInterface;
    private HeroesData heroesData;
    private ListAdapter adapter;
    private static boolean dataLoaded = false;
    public ListPresenter(ListViewInterface listViewInterface) {
        this.listViewInterface = listViewInterface;
        heroesData = HeroesData.init();
    }

    public void initLoader(android.support.v4.app.LoaderManager loaderManager){
        loaderManager.restartLoader(JSON_LOADER,null,this);
        listViewInterface.showProgressBar();
    }

    @Override
    public Loader<List<HeroModel>> onCreateLoader(int id, Bundle args) {
        Loader<List<HeroModel>> loader = new ContentDownloader(listViewInterface.getViewContext());
        loader.forceLoad();
        return loader;
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<HeroModel>> loader, List<HeroModel> data) {
        if (data == null) {
            dataLoaded = false;
            listViewInterface.showErrorMessage();
        }
        else {
            dataLoaded = true;
            heroesData.setData(data);
            listViewInterface.setList(data);
        }
        listViewInterface.hideProgressBar();
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<HeroModel>> loader) {

    }


    @Override
    public void setSelectedPosition( int position) {
        int id = adapter.getItem(position).getItemId();
        heroesData.setSelectedItemId(id);
    }

    @Override
    public void filterList(String str) {
        if (adapter != null)
            adapter.getFilter().filter(str);
    }

    @Override
    public void removeFilter() {
        if (adapter != null)
            adapter.removeFilter(heroesData.getData());
    }

    @Override
    public void setListAdapter(ListAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public boolean isDataLoaded() {
        return dataLoaded;
    }
}
