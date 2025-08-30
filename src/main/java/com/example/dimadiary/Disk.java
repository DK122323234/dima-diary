package com.example.dimadiary;

import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.imageio.IIOException;
import java.io.*;
import java.lang.foreign.UnionLayout;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

       public class Disk {
           public void saveToDisk(){
               String token = "e79141dc86094a3994acea30c6c81f24";
               String url = "https://disk.yandex.ru/d/YSBhkTpnIl3hTw";
               String directoryPath;


               DirectoryChooser directoryChooser = new DirectoryChooser();
               directoryChooser.setTitle("Выберите папку");
               Window window = new Stage();
               File selectedDirectory = directoryChooser.showDialog(window);
               directoryPath = selectedDirectory.toString();

               File file = new File(directoryPath);
               if (file.isDirectory()) {
                   try {



                       URL url1 = new URL(url + "?path="+  file.getName() + "&overwrite=true");


                       System.out.println(url1.toString());


                       HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
                       httpURLConnection.setDoOutput(true);
                       httpURLConnection.setRequestMethod("PUT");
                       httpURLConnection.setRequestProperty("Authorization", "OAuth" + token);
                       httpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
                       System.out.println(httpURLConnection);


                       if (httpURLConnection.getResponseCode() == 201) {
                       }
                       else {
                           System.out.println("фигово");

                       }


                   } catch(IOException e){
                       throw new RuntimeException(e);
                   }


               }
           }
       }
