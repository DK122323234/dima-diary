package com.example.dimadiary;

import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import netscape.javascript.JSObject;

public class HelloController {
        private String date;
        private Note note = new Note();
        boolean save = false;
        boolean one = false;
        boolean menuAndBack = false;

        @FXML
        private TextField search1;

        @FXML
        private Label instructions1;

        @FXML
        private Pane recordingOnDateMenu;

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


    public void stopPrograms(){
        instructions.setText("");
    }



        @FXML
        void find (ActionEvent event) {

            String dateRegex = "^(?!0)\\d{4}-\\d{2}-\\d{2}$";

            Pattern pattern = Pattern.compile(dateRegex);
            Matcher matcher = pattern.matcher(search.getText());
            if (matcher.matches()) {
                date = search.getText();
                String a = note.loadFromFile(date);

                switch (a) {
                    case "Такой записи не существует" -> {
                        instructions.setText("Такой записи не существует или файл с ней был потерян");
                        System.out.println("dd");
                    }
                    case "Папка \"dataRecords\" не найдена" ->
                            instructions.setText("                      Папка \"dataRecords\" не найдена");
                    case "файл содержит текст в неправильном формате" ->
                            instructions.setText("файл " + date + " содержит текст в неправильном формате");
                    default -> {
                        text.setText(note.loadFromFile(date));
                        goToTheEntry.setVisible(true);
                        System.out.println("DYDY");
                    }
                }
            }
            else {
                instructions.setText("       Вы ввели неверный формат даты, попробуйте снова");
            }
        }

        @FXML
        void goTo (ActionEvent event){

        text.setText( note.loadFromFile(search.getText()));
      date = search.getText();
            searchSystem.setVisible(false);
            save = true;
            search.setText("");
            menuAndBack = false;
            searchSystem.setVisible(false);
            goToTheEntry.setVisible(false);
        }

    @FXML
    void writeDownTheDate(ActionEvent event) {
        recordingOnDateMenu.setVisible(true);
        menuAndBack = false;

    }
    @FXML
    void writeDownTheDate1(ActionEvent event) {
        recordingOnDateMenu.setVisible(true);
        menuAndBack = true;

    }
    @FXML
    void makeARecording(ActionEvent event) {
        String dateRegex = "^(?!0)\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(dateRegex);
        Matcher matcher = pattern.matcher(search1.getText());
        if(matcher.matches()){
            ComplianceCheck complianceCheck = new ComplianceCheck();
            if (complianceCheck.check(search1.getText())){

                recordingOnDateMenu.setVisible(false);
                mainMenu.setVisible(false);
                date = search1.getText();
              menuAndBack = false;
            }
           else {
               instructions1.setText("       Вы ввели несуществующую дату, попробуйте снова");
            }

        }
        else {
            instructions1.setText("       Вы ввели неверный формат даты, попробуйте снова");
        }
    }

    @FXML
    void startFind(ActionEvent event) {
            mainMenu.setVisible(false);
            searchSystem.setVisible(true);
            menuAndBack = true;

    }

    @FXML
    void begin(ActionEvent event) {
        menuAndBack = false;
        date = LocalDate.now().toString();
        String a = LocalDate.now().toString();

         if (note.loadFromFile(LocalDate.now().toString()).equals("Такой записи не существует")){
            text.setText("");

         }
        else {
             note.loadFromFile(a);
             text.setText(note.loadFromFile(LocalDate.now().toString()));
         }

         mainMenu.setVisible(false);
         one = true;


        }


        @FXML
        void goBack (ActionEvent event){
            if (!menuAndBack) {
                searchSystem.setVisible(false);
                search.setText("");
                goToTheEntry.setVisible(false);
                recordingOnDateMenu.setVisible(false);
                mainMenu.setVisible(false);
                System.out.println("S");
            }
        if (menuAndBack){
            System.out.println("ss");
           searchSystem.setVisible(false);
           recordingOnDateMenu.setVisible(false);
           mainMenu.setVisible(true);
           menuAndBack = false;

        }

    }

        @FXML
        void findByRecord (ActionEvent event){
        searchSystem.setVisible(true);
        menuAndBack = false;
    }

        @FXML
        void save (ActionEvent event) {
            if (one) {
                note.savingToFile(LocalDate.now().toString(), text.getText());
                one = false;
                date = LocalDate.now().toString();

            }
              else {
                note.savingToFile(date, text.getText());

            }
        }


    @FXML
        void initialize () {
        assert instructions != null : "fx:id=\"instructions\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert searchSystem != null : "fx:id=\"searchSystem\" was not injected: check your FXML file 'hello-view.fxml'.";

    }



    }

