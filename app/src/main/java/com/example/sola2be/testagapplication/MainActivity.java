package com.example.sola2be.testagapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ListViewInterface, AdapterView.OnItemClickListener,TextWatcher {

    private ListView listView;
    private ProgressBar progressBar;
    private EditText editTextFilter;
    private ListPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new ListPresenter(this);
        listView = (ListView) findViewById(R.id.listView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editTextFilter = (EditText) findViewById(R.id.editTextFilter);
        listView.setOnItemClickListener(this);
        if (listView.getAdapter() == null)
            presenter.initLoader(getSupportLoaderManager());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editTextFilter.removeTextChangedListener(this);
        presenter.removeFilter();
    }

    @Override
    public void setList(List<HeroModel> list) {
        ListAdapter adapter = new ListAdapter(this,list);
        presenter.setListAdapter(adapter);
        listView.setAdapter(adapter);
        editTextFilter.addTextChangedListener(this);
        if (editTextFilter.getText().length() > 0 )
            presenter.filterList(editTextFilter.getText().toString());
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this,"Не удалось загрузить данные с сервера",Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.setSelectedPosition(position);
        Intent intent = new Intent(this,SwipeActivity.class);
        startActivity(intent);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (count > 0)
            presenter.filterList(s.toString());
        else
            presenter.removeFilter();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
