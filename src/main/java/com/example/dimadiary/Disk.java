package com.example.dimadiary;

import com.yandex.disk.rest.Credentials;
import com.yandex.disk.rest.ProgressListener;
import com.yandex.disk.rest.RestClient;
import com.yandex.disk.rest.exceptions.ServerException;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;

public class Disk {
    public void saveToDisk(){
        String token = "y0__xDFyZvRBxjq6Dkgz7y8mhTFsodHmQQ5LLn997ZlNZRGDF_V1A";
        String url = "disk:/дима";
        String directoryPath;


        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите папку");
        Window window = new Stage();
        File selectedDirectory = directoryChooser.showDialog(window);
        directoryPath = selectedDirectory.toString();

        Credentials credentials = new Credentials("dima.kamensky.2012", token);
        File file = new File(directoryPath);
        file = new File(directoryPath, file.getName());

        RestClient restClient = new RestClient(credentials);
        ProgressListener progressListener = new ProgressListener() {
            @Override
            public void updateProgress(long loaded, long total) {
                System.out.println("e2f4g8g");
            }

            @Override
            public boolean hasCancelled() {
                return false;
            }
        };
        try {
            restClient.downloadFile("disk:/дима",file, progressListener);
      System.out.println("УРАААААААААААААААА НАКОНЕЦ ТО И ГОДА НЕ ПРОШЛО");
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("Плохо");
        }


        if (file.isDirectory()) {




        }
    }
}

