package com.vsga.app.finalprojectvsga.sqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vsga.app.finalprojectvsga.R;
import com.vsga.app.finalprojectvsga.sqlite.adapter.Adapter;
import com.vsga.app.finalprojectvsga.sqlite.helper.DbHelper;
import com.vsga.app.finalprojectvsga.sqlite.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLiteActivity extends AppCompatActivity {

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_ADDRESS = "address";
    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> itemList = new ArrayList<Data>();
    Adapter adapter;
    DbHelper SQLite = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Aplikasi SQLite");
        setContentView(R.layout.activity_sqlite_main);

        SQLite = new DbHelper(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);

        listView = findViewById(R.id.list_view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SQLiteActivity.this, AddEdit.class);
                startActivity(intent);
            }
        });

        adapter = new Adapter(SQLiteActivity.this, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = itemList.get(position).getId();
                final String name = itemList.get(position).getName();
                final String address = itemList.get(position).getAddress();

                final CharSequence[] dialogItem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(SQLiteActivity.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intent = new Intent(SQLiteActivity.this, AddEdit.class);
                                intent.putExtra(TAG_ID, idx);
                                intent.putExtra(TAG_NAME, name);
                                intent.putExtra(TAG_ADDRESS, address);
                                startActivity(intent);
                                break;

                            case 1:
                                SQLite.delete(Integer.parseInt(idx));
                                itemList.clear();
                                getAllData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getAllData();
    }

    private void getAllData() {
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();

        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(TAG_ID);
            String poster = row.get(i).get(TAG_NAME);
            String title = row.get(i).get(TAG_ADDRESS);

            Data data = new Data();

            data.setId(id);
            data.setName(poster);
            data.setAddress(title);

            itemList.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        getAllData();
    }
}
