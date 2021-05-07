package com.jramons.examenandroid.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.jramons.examenandroid.InstanceApp;
import com.jramons.examenandroid.R;
import com.jramons.examenandroid.ViewModel.FileViewModel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SecondActivity extends AppCompatActivity {
    private FileViewModel viewModel;
    private long dowloadid;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        viewModel = new ViewModelProvider(this).get(FileViewModel.class);
        viewModel.getFile().observe(this, url->{
                dowloadFile(url);
                unZip();
                readJson();
        });

        registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }



    private BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1);
            if(dowloadid == id){
                Toast.makeText(context, "Dowload Completed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void dowloadFile(String fileUrl){
        file = new File(InstanceApp.getContext().getExternalFilesDir(null),"employees.zip");
        String ruta = file.getAbsolutePath();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileUrl))
                .setTitle("Employees")
                .setDescription("Dowloading")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setDestinationUri(Uri.fromFile(file))
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true);

        DownloadManager downloadManager = (DownloadManager)InstanceApp.getContext().getSystemService(Context.DOWNLOAD_SERVICE);
        dowloadid = downloadManager.enqueue(request);
    }

    private void readJson() {
        file = new File(InstanceApp.getContext().getExternalFilesDir(null),"employees_data.json");
        Boolean is = file.canRead();
         file.getName();

    }

    public void unZip() {
        file = new File(InstanceApp.getContext().getExternalFilesDir(null),"employees.zip");
        String ruta = file.getPath();

        InputStream is;
        ZipInputStream  zis;
        try {
            String filename;
            is = new FileInputStream(file);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;
            byte[] buffer = new byte[1024];
            int count;
            ze = zis.getNextEntry();
            while(ze != null){
                    filename = ze.getName();
                    if(ze.isDirectory()){
                        file.mkdirs();
                        continue;
                    }
                FileOutputStream fout = new FileOutputStream(file);
                    while((count = zis.read(buffer)) != -1){
                        fout.write(buffer,0,count);
                    }
                    fout.close();
                    zis.closeEntry();
            }
            zis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(onDownloadComplete);
    }
}