package com.example.dimadiary;

import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class Disk {
   public void saveToDisk(){

       String token = "c83c3b6dff804d6ea38c3c7ba23327c1";
       String url = "https://cloud-api.yandex.net/v1/disk/resources/upload";
       String pathDisk;
       String pathDirectory;

       try {
           DirectoryChooser directoryChooser = new DirectoryChooser();
           directoryChooser.setTitle("Выберите папку");
           Window window = new Stage();
           File selectedDirectory = directoryChooser.showDialog(window);
           pathDirectory = selectedDirectory.toString();

           File file = new File(pathDirectory);
           pathDisk = "disk/" + file.getName();
           System.out.println(pathDisk);
           CloseableHttpClient client = HttpClients.createDefault();
           HttpGet get = new HttpGet(url + "?path=" + pathDisk + "&overwrite=true");
           get.setHeader("Authorization", "OAuth " + token);


           HttpResponse response = client.execute(get);
           String jsonResponse = EntityUtils.toString(response.getEntity());
           JSONObject jsonObject = new JSONObject(jsonResponse);
           System.out.println(jsonObject);



       } catch (Exception e) {
           Alert alert = new Alert(Alert.AlertType.INFORMATION, "Произошла ошибка при выборе папки");
           alert.showAndWait();
        e.printStackTrace();
       }


   }
}
