package com.example.ales.sqdb;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ales on 3.7.2018.
 */

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";
    DatabaseHelper mDbHelper;
    private ListView mListView;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView = (ListView) findViewById(R.id.list);
        mDbHelper = new DatabaseHelper(this);

        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Dispaying data in ListView");

        Cursor data = mDbHelper.getData();
        Map<String, Float> listData = new HashMap();

        while(data.moveToNext()) {
            listData.put(data.getString(1), data.getFloat(2));
        }
        ListAdapter adapter = new MyAdapter(listData);
        mListView.setAdapter(adapter);

    }

}
