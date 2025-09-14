package com.example.dimadiary;

import java.io.File;
import java.net.URL;

import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.yandex.disk.rest.Credentials;
import com.yandex.disk.rest.ProgressListener;
import com.yandex.disk.rest.RestClient;
import com.yandex.disk.rest.json.Link;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import javafx.stage.Stage;

public class Disk {
    public void saveToDisk() {
        String token = "y0__xDFyZvRBxjq6Dkgz7y8mhTFsodHmQQ5LLn997ZlNZRGDF_V1A";
        String url = "disk:/дима";
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите папку");
        Window window = new Stage();
        File selectedDirectory = directoryChooser.showDialog(window);

        if (selectedDirectory != null && selectedDirectory.isDirectory()) {
            File[] files = selectedDirectory.listFiles();

            if (files != null) {
                for (File fileTest : files) {
                    ProgressListener progressListener = new ProgressListener() {
                        @Override
                        public void updateProgress(long l, long l1) {

                        }

                        @Override
                        public boolean hasCancelled() {
                            return false;
                        }
                    };

                    Credentials credentials = new Credentials("dima.kamensky.2012", token);
                    RestClient restClient = new RestClient(credentials);





                    if (fileTest != null) {
                        try {
                            String linkPath =  "https://cloud-api.yandex.net/v1/disk/resources/upload?path=" + url + "&overwrite=true";


                            restClient.uploadFile(new Link(), false, fileTest, progressListener);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Файл не существует или не является файлом: " + fileTest.getAbsolutePath());
                    }
                }
            } else {
                System.out.println("Не удалось получить список файлов в директории.");
            }
        } else {
            System.out.println("Выберите корректную папку.");
        }
    }
}
