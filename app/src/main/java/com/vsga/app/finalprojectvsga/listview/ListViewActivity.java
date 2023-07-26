package com.vsga.app.finalprojectvsga.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vsga.app.finalprojectvsga.R;

public class ListViewActivity extends AppCompatActivity {

    ListView lv;
    String[] namanegara = new String[]{
            "Indonesia", "Malaysia", "Singapore",
            "Italia", "Inggris", "Belanda",
            "Argentina", "Chile",
            "Mesir", "Uganda"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("List View");
        setContentView(R.layout.activity_listview);

        lv = findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListViewActivity.this,
                android.R.layout.simple_list_item_1, android.R.id.text1, namanegara);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListViewActivity.this, "Memilih : " + namanegara[i], Toast.LENGTH_LONG).show();
            }
        });
    }
}
