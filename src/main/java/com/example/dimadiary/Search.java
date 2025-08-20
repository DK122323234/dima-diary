package com.example.dimadiary;

import javafx.scene.control.Alert;
import javafx.scene.shape.Path;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Search {
    public ArrayList<ArrayList> wordCheck(String words){
        ArrayList<String> arrayList5 = new ArrayList<String>();
        String bags = "Файлы:";
        if (words != null && !words.trim().isEmpty()) {


            String projectPath = Paths.get("").toAbsolutePath().toString();
            String directoryPath = projectPath + "\\dataRecords";
            File directory = new File(directoryPath);
            HashSet<String> hashSet = new HashSet<String>();
            HashSet<String> hashSet1 = new HashSet<String>();
            int q = 0;
            if (directory.exists()) {
                File[] files = directory.listFiles();
                int i = files.length - 1;
                System.out.println(i);
                while (i >= q) {

                    File file = new File(files[q].toString());
                    System.out.println(file);
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

                        String line = bufferedReader.readLine().toString();
                        JSONObject jsonObject = new JSONObject(line);
                        String text = jsonObject.get("recording").toString();

                        String s = words + " ";
                        System.out.println("text:" + text);

                        String[] d = s.split(" ");



                        for (int o = 0; o <= d.length - 1; o++) {
                            d[o].replaceAll(" ", "");
                            if (d[o].equals("") || d[o].equals(" ")){
                                System.out.println("hh");
                            }
                            else {

                                if (text.toLowerCase().contains(d[o].toLowerCase())) {
                                    System.out.println("87");
                                    hashSet.add(file.getName().replace(".json", ""));
                                    hashSet1.add(text);
                                }
                            }
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                        bags = bags + " " + file.getName();
                        System.out.println(bags);

                    }
                    q = q + 1;
                }
            } else {
                directory.mkdir();

            }
            ArrayList<String> arrayList1 = new ArrayList<String>(hashSet);
            ArrayList<String> arrayList2 = new ArrayList<String>(hashSet1);
            ArrayList<ArrayList> arrayLists = new ArrayList<ArrayList>();
            arrayLists.add(arrayList1);
            arrayLists.add(arrayList2);
            arrayLists.add(arrayList5);
            return arrayLists;

        }
        else {
            return new ArrayList<>();
        }
    }
}
