package com.example.dimadiary;

import javafx.scene.control.Alert;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.awt.SystemColor.text;

public class Note {
  private String text = "";



    public void savingToFile(String date, String entries) {
        File directory = new File("C:\\Users\\dkame\\OneDrive\\Изображения\\Desktop\\IdeaProjects\\Dima-diary\\dataRecords");
        if (directory.exists()) {
            File file = new File("C:\\Users\\dkame\\OneDrive\\Изображения\\Desktop\\IdeaProjects\\Dima-diary\\dataRecords\\" + date + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));) {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("recording", entries);
                writer.write(jsonObject.toString());
                writer.newLine();


            } catch (Throwable e) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "файл содержит текст в неправильном формате");
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Папка \"dataRecords\" не найдена");
            alert.showAndWait();
        }


    }

    public String loadFromFile(String name){
        File directory = new File("C:\\Users\\dkame\\OneDrive\\Изображения\\Desktop\\IdeaProjects\\Dima-diary\\dataRecords");
        if (directory.exists()){
            File nameFile = new File(directory, name + ".txt");
            if (nameFile.exists()){
                System.out.println();
                 String path = "C:\\Users\\dkame\\OneDrive\\Изображения\\Desktop\\IdeaProjects\\Dima-diary\\dataRecords\\" + name + ".txt";

                try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
                  String line = bufferedReader.readLine();
                  JSONObject jsonObject = new JSONObject(line);
                   text = jsonObject.get("recording").toString();

                }
                catch (Throwable e) {
                    text = "файл содержит текст в неправильном формате";
                    System.out.println("Ss");
                }

            }

            else {
                text = "Такой записи не существует";
            }
        }
        else {
            text = "Папка \"dataRecords\" не найдена";
        }
        return text;
    }
}

