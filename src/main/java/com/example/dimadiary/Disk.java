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
               String token = "y0__xDFyZvRBxjq6Dkgz7y8mhTFsodHmQQ5LLn997ZlNZRGDF_V1A";
               String url = "https://disk.yandex.ru/d/lLg0bv_mkLKIKQ/";
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
                       httpURLConnection.setRequestMethod("GET");
                       httpURLConnection.setRequestProperty("Authorization", "OAuth" + token);
                       httpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");

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
