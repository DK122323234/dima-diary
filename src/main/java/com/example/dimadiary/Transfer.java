package com.example.dimadiary;

import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Transfer {
    String projectPath = Paths.get("").toAbsolutePath().toString();
    File directory = new File(projectPath + "\\dataRecords");

    public void transfers() {
        System.out.println("ddd");
        JSONObject jsonFile = new JSONObject();

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите папку");
        Window window = new Stage();
        File selectedDirectory = directoryChooser.showDialog(window);


        if (selectedDirectory != null) {
            if (directory.exists()) {
                File[] files = directory.listFiles();
                int i = files.length - 1;
                System.out.println(i);
                int q = 0;
                try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(selectedDirectory + "\\Notes.zip"))) {
                    File directoryToZip = new File(directory.toString());
                    if (directoryToZip.exists() && directoryToZip.isDirectory()) {
                        for (File file : directoryToZip.listFiles()) {
                            if (file.isFile()) {
                                ZipEntry zipEntry = new ZipEntry(file.getName());
                                zipOutputStream.putNextEntry(zipEntry);
                                try (FileInputStream inputStream = new FileInputStream(file)) {
                                    byte[] buffer = new byte[1024];
                                    int length;
                                    while ((length = inputStream.read(buffer)) > 0) {
                                        zipOutputStream.write(buffer, 0, length);
                                    }
                                }
                                zipOutputStream.closeEntry();
                            }
                        }
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                for (File file : directory.listFiles()) {
                    file.delete();

                }


            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Произошла ошибка при выборе файла для переноса");
                alert.showAndWait();
            }


        }


    }

    public void receiving() {


        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите ZIP-файл");
        Window window = new Stage();

        FileChooser.ExtensionFilter zipFilter = new FileChooser.ExtensionFilter("ZIP Files (*.zip)", "*.zip");
        fileChooser.getExtensionFilters().add(zipFilter);
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            if (directory.exists()) {
                try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(selectedFile))) {
                    ZipEntry entry;
                    while ((entry = zipInputStream.getNextEntry()) != null) {
                        File file = new File(directory, entry.getName());
                        if (entry.isDirectory()) {
                            file.mkdirs();
                        } else {
                            new File(file.getParent()).mkdirs();
                            try (FileOutputStream fos = new FileOutputStream(file)) {
                                byte[] buffer = new byte[1024];
                                int length;
                                while ((length = zipInputStream.read(buffer)) > 0) {
                                    fos.write(buffer, 0, length);
                                }
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        zipInputStream.closeEntry();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else {
                directory.mkdirs();
                Transfer transfer = new Transfer();
                transfer.receiving();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Неправильный путь");
            alert.showAndWait();
        }

    }
}



