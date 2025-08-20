package com.example.dimadiary;

import javafx.scene.control.Alert;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.awt.SystemColor.text;

public class Note {
  private String text = "";
    String projectPath = Paths.get("").toAbsolutePath().toString();



    public void savingToFile(String date, String entries) {
        File directory = new File(projectPath);
        if (directory.exists()) {
            File file = new File( projectPath + "\\dataRecords\\" + date + ".json");
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
           String directoryPath = projectPath + "\\dataRecords";
            File directory1 = new File(directoryPath);
                 directory1.mkdir();
                 Note note = new Note();
                 note.savingToFile(date, entries);

        }



    }

    public String loadFromFile(String name){
        File directory = new File(projectPath + "\\dataRecords");
        if (directory.exists()){
            File nameFile = new File(directory, name + ".json");
            if (nameFile.exists()){
                System.out.println();
                 String path = projectPath + "\\dataRecords\\" + name + ".json";

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
            File directory1 = new File(projectPath + "\\dataRecords");
            directory1.mkdir();
            Note note = new Note();
            text = note.loadFromFile(name);

        }
        return text;
    }
}

