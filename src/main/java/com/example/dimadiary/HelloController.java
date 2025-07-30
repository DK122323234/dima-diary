package com.example.dimadiary;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class HelloController {

        ArrayList<String> list = returningEntries();
        ArrayList<String> dates = returningData();
        boolean save = false;
        private int index;
        boolean one = false;
        boolean menuAndBack = false;

        @FXML
        private Pane mainMenu;

        @FXML
        private TextArea text;

        @FXML
        private Button goToTheEntry;

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Label instructions;

        @FXML
        private TextField search;

        @FXML
        private Pane searchSystem;

        @FXML
        private Button backToMenu;



        @FXML
        void find (ActionEvent event){

        if (dates.contains(search.getText())) {
            instructions.setText("Запись найдена, что бы перейти найжмите на кнопку с низу");
            goToTheEntry.setVisible(true);
        } else {
            instructions.setText("        Такого текста не существует, попробуйте снова");
            goToTheEntry.setVisible(false);
        }
    }

        @FXML
        void goTo (ActionEvent event){
            index = dates.indexOf(search.getText());
            if (index != -1 && index < list.size()) {
                text.setText(list.get(index));
                menuAndBack = false;
            } else {
                instructions.setText("Запись не найдена.");
            }
            searchSystem.setVisible(false);
            save = true;
            search.setText("");
            goToTheEntry.setVisible(false);
        }

    @FXML
    void startFind(ActionEvent event) {
            mainMenu.setVisible(false);
            searchSystem.setVisible(true);
            menuAndBack = true;

    }

    @FXML
    void begin(ActionEvent event) {
        String a = LocalDate.now().toString();
        recordingDates(dates);
        recordingEntries(list);
        index = dates.indexOf(search.getText());
        if (dates.contains(a)) {

            text.setText(list.get(0));
            mainMenu.setVisible(false);
            one = true;
        }


        }


        @FXML
        void goBack (ActionEvent event){
            if (!menuAndBack) {
                searchSystem.setVisible(false);
                search.setText("");
                goToTheEntry.setVisible(false);
            }
        if (menuAndBack){
           searchSystem.setVisible(false);
           mainMenu.setVisible(true);
           menuAndBack = false;

        }

    }

        @FXML
        void findByRecord (ActionEvent event){
        searchSystem.setVisible(true);
    }

        @FXML
        void save (ActionEvent event){

            LocalDate now = LocalDate.now();
            String todayDate = String.valueOf(now);

            if (save) {
                if (index < list.size()) {
                    list.set(index, text.getText());
                } else {
                    System.out.println("Ошибка");
                }
                recordingDates(dates);
                recordingEntries(list);
            } else {
                if (dates.isEmpty() || !dates.get(dates.size() - 1).equals(todayDate)) {
                    list.add(text.getText());
                    dates.add(todayDate);
                    recordingDates(dates);
                    recordingEntries(list);
                } else {
                    list.add(text.getText());
                    recordingDates(dates);
                    recordingEntries(list);
                }
            }
            if (one){
                index = dates.indexOf( LocalDate.now().toString());
                list.set(index, text.getText());
                recordingDates(dates);
                recordingEntries(list);


            }
    }


        private void recordingDates (ArrayList < String > dates) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Data"));
            objectOutputStream.writeObject(dates);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        }
    private static ArrayList<String> returningData() {
        ArrayList<String> dates = new ArrayList<>();
        File file = new File("Data");
        if (!file.exists()) {
            System.err.println("Файл не найден");
            return dates;
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            dates = (ArrayList<String>) objectInputStream.readObject();
        } catch (EOFException e) {
            System.err.println("Ошибка если файл пуст: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при чтении данных: " + e.getMessage());
        }
        return dates;
    }
    private void recordingEntries (ArrayList < String > dates) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Entries"));
            objectOutputStream.writeObject(dates);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    private static ArrayList<String> returningEntries() {
        ArrayList<String> dates = new ArrayList<>();
        File file = new File("Entries");
        if (!file.exists()) {
            System.err.println("Файл не найден");
            return dates;
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            dates = (ArrayList<String>) objectInputStream.readObject();
        } catch (EOFException e) {
            System.err.println("Ошибка если файл пуст: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при чтении данных: " + e.getMessage());
        }
        return dates;
    }



    @FXML
        void initialize () {
        dates = returningData();
        assert instructions != null : "fx:id=\"instructions\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert searchSystem != null : "fx:id=\"searchSystem\" was not injected: check your FXML file 'hello-view.fxml'.";

    }



    }

