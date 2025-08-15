package com.example.dimadiary;


import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Photo {
    int i = 1;

    String projectPath = Paths.get("").toAbsolutePath().toString();
    public void savePhoto(String date, String pathPhoto) {

            File directory = new File(projectPath + "\\dataPhoto");

            if (directory.exists()) {
                System.out.println( "ssss");

                String s = projectPath + "\\dataPhoto\\" + date + "_" + i + ".png";
                try {
                    Path path =  Paths.get(pathPhoto);
                    Path path1 =Paths.get(s);
                    Files.copy(path, path1);
                }
               catch (IOException e){
                    i = i + 1;
                    savePhoto(date,pathPhoto);
                    System.out.println("ssewu8g8");

               }

            } else {
                File directory1 = new File(projectPath + "\\dataPhoto");
                directory1.mkdir();
                System.out.println("a");
            }

    }

           public boolean loadPhoto(String date, int t){
        File directory = new File(projectPath + "\\dataPhoto");
           if (directory.exists()){
               File file = new File(projectPath + "\\dataPhoto\\" + date + "_" + t + ".png");
               if (file.exists()){
                  return true;
               }
               else {
                   return false;
               }
           }
           else {
               File directory1 = new File(projectPath + "\\dataPhoto");
               directory1.mkdir();
               return  false;
           }
        }
}
