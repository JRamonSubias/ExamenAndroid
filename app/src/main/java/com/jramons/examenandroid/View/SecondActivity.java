package com.jramons.examenandroid.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.jramons.examenandroid.InstanceApp;
import com.jramons.examenandroid.Model.Employees;
import com.jramons.examenandroid.ParseJson;
import com.jramons.examenandroid.R;
import com.jramons.examenandroid.SharedPreferenceManager;
import com.jramons.examenandroid.ViewModel.FileViewModel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private FileViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        viewModel = new ViewModelProvider(this).get(FileViewModel.class);
        if(!SharedPreferenceManager.getSomeBooleanValue(InstanceApp.FILE_JSON)){
            viewModel.getColaboradoresFileJson();
          SharedPreferenceManager.setSomeBooleanValue(InstanceApp.FILE_JSON,true);
        }
        viewModel.respaldoFDB();
    }

/*
    private void dowloadFile(String fileUrl){
        file = new File(InstanceApp.getContext().getExternalFilesDir(null),"employees.zip");
        String ruta = file.getAbsolutePath();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileUrl))
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
        try {
            FileInputStream fis = null;
            fis = openFileInput("employees_data.json");

            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String json;
            StringBuilder stringBuilder = new StringBuilder();
            while ((json = bufferedReader.readLine()) != null ){
                stringBuilder.append(json).append("\n");
            }
            Log.i("JSON", String.valueOf(stringBuilder));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
*/

}