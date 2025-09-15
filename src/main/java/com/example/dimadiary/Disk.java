package com.example.dimadiary;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;


public class Disk {
    public void saveToDisk(){
        String directoryPath;


        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите папку");
        Window window = new Stage();
        File selectedDirectory = directoryChooser.showDialog(window);
        directoryPath = selectedDirectory.toString();

        File file = new File(directoryPath);
        if (file.isDirectory()) {




        }
    }
}

