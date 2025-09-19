package com.example.dimadiary;

import com.almasb.fxgl.net.ClientConfig;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.yandex.disk.rest.Credentials;
import com.yandex.disk.rest.ProgressListener;
import com.yandex.disk.rest.RestClient;
import com.yandex.disk.rest.exceptions.ServerException;
import com.yandex.disk.rest.json.Link;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


public class Disk {
    public void saveToDisk() {
        String directoryPath;

            String token = "y0__xDFyZvRBxjq6Dkgz7y8mhTFsodHmQQ5LLn997ZlNZRGDF_V1A";
            String url = "disk:/дима";

            boolean progressSave = true;

            try {
                String projectPath = Paths.get("").toAbsolutePath().toString();
                File selectedDirectory = new File(projectPath + "\\dataRecords");
                directoryPath = selectedDirectory.toString();
                File file = new File(directoryPath);

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
                                    String linkPath = "https://cloud-api.yandex.net/v1/disk/resources/upload?path=" + url + "&overwrite=true";
                                    restClient.uploadFile(restClient.getUploadLink("dima-diary/" + fileTest.getName(), true), false, fileTest, progressListener);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    progressSave = false;
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Сохранить файл: " + fileTest.getName() + " не удалось");
                                    alert.showAndWait();
                                }
                            } else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Файл отсутствует или поврежден");
                                alert.showAndWait();
                            }
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Получить файлы из папки");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Папка отствует или повреждена");
                    alert.showAndWait();
                }
                if (progressSave) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Сохронение прошло успешно!");
                    alert.showAndWait();
                }
            } catch (RuntimeException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Вы выбрали неправильный путь до папки или во время выбора произошла ошибка");
                alert.showAndWait();
            }
        }
    }
