package com.example.dimadiary;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class HelloController {
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();
    boolean save = false;
    private int index;


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
    void find(ActionEvent event) {

            if (dates.contains(search.getText())){
                instructions.setText("Запись найдена, что бы перейти найжмите на кнопку с низу");
                goToTheEntry.setVisible(true);
            }
            else {
                instructions.setText("        Такого текста не существует, попробуйте снова");
                goToTheEntry.setVisible(false);
            }
        }

    @FXML
    void goTo(ActionEvent event) {
         index = dates.indexOf(search.getText());
        text.setText(list.get(index));
        searchSystem.setVisible(false);
        save = true;
        search.setText("");
        goToTheEntry.setVisible(false);


    }



    @FXML
    void goBack(ActionEvent event) {
        list.add("прив"); //это для тестов если что
        dates.add("2025-12-12"); //и это тоже
        searchSystem.setVisible(false);
        search.setText("");
        goToTheEntry.setVisible(false);

    }

    @FXML
    void findByRecord(ActionEvent event) {
       searchSystem.setVisible(true);
    }

    @FXML
    void save(ActionEvent event) {

        LocalDate.now();
        String todayDate = String.valueOf(LocalDate.now());
        if (save){
            list.set(index, text.getText());

        }
       else {

            if (dates.isEmpty() || !dates.getLast().equals(todayDate)) {
                list.add(text.getText());
                dates.add(todayDate);
                System.out.println(list.getLast());
            } else {
                list.add(dates.size(), text.getText());
                System.out.println(list.getLast());
            }
        }




    }

    @FXML
    void initialize() {
        assert instructions != null : "fx:id=\"instructions\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert searchSystem != null : "fx:id=\"searchSystem\" was not injected: check your FXML file 'hello-view.fxml'.";

    }

}
