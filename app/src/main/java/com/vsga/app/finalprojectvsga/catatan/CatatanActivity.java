package com.vsga.app.finalprojectvsga.catatan;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.vsga.app.finalprojectvsga.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CatatanActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_STORAGE = 100;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Aplikasi Catatan Harian");
        setContentView(R.layout.activity_catatan_main);

        listView = findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CatatanActivity.this, InsertAndViewActivity.class);
                Map<String, Object> data = (Map<String, Object>) parent.getAdapter().getItem(position);
                intent.putExtra("filename", data.get("name").toString());
                Toast.makeText(CatatanActivity.this, "You clicked" + data.get("name"), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> data = (Map<String, Object>) parent.getAdapter().getItem(position);
                tampilkanDialogKonfirmasiHapusCatatan(data.get("name").toString());
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 23) {
            if (periksaIzinPenyimpanan()) {
                mengambilListFilePadaFolder();
            }
        } else {
            mengambilListFilePadaFolder();
        }
    }

    public boolean periksaIzinPenyimpanan() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE);
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mengambilListFilePadaFolder();
            }
        }
    }

    void tampilkanDialogKonfirmasiHapusCatatan(final String filename) {
        new AlertDialog.Builder(this).setTitle("Hapus Catatan ini?")
                .setMessage("Apakah Anda yakin ingin menghapus catatan " + filename + "?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hapusFile(filename);
                    }
                }).setNegativeButton(android.R.string.no, null).show();
    }

    void hapusFile(String filename) {
        String path = getExternalFilesDir(null) + "/catatan";
        File file = new File(path, filename);
        if (file.exists()) {
            file.delete();
        }
        mengambilListFilePadaFolder();
    }

    void mengambilListFilePadaFolder() {
        String path = getExternalFilesDir(null) + "/catatan";
        File directory = new File(path);

        if (directory.exists()) {
            File[] files = directory.listFiles();
            String[] filenames = new String[files.length];
            String[] dateCreated = new String[files.length];
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM YYYY HH:mm:ss");
            ArrayList<Map<String, Object>> itemDataList = new ArrayList<>();

            for (int i = 0; i < files.length; i++) {
                filenames[i] = files[i].getName();
                Date lastModDate = new Date(files[i].lastModified());
                dateCreated[i] = simpleDateFormat.format(lastModDate);

                Map<String, Object> listItemMap = new HashMap<>();
                listItemMap.put("name", filenames[i]);
                listItemMap.put("date", dateCreated[i]);
                itemDataList.add(listItemMap);
            }

            SimpleAdapter simpleAdapter = new SimpleAdapter(this, itemDataList, android.R.layout.simple_list_item_2, new String[]{"name", "date"}, new int[]{android.R.id.text1, android.R.id.text2});
            listView.setAdapter(simpleAdapter);
            simpleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catatan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_tambah) {
            Intent i = new Intent(this, InsertAndViewActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
