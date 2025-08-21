package com.example.dimadiary;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import kotlin.coroutines.CoroutineContext;

import javax.swing.*;
import javax.swing.text.Element;
import javax.swing.text.View;


public class HelloController {
    String text1;
    String text2;
    String text3;
    String text4;

    private String date;
    private Note note = new Note();
    boolean save = false;
    boolean one1 = false;
    boolean menuAndBack = false;
    int i = 1;
    ArrayList<ArrayList> arrayLists;
    int d;
    @FXML
    private DatePicker calendar;

    @FXML
    private Label error;

    @FXML
    private Pane wordsMenu;

    @FXML
    private Pane menuPhoto;

    @FXML
    private Button next;

    @FXML
    private Button previous;

    @FXML
    private ImageView photo;

    @FXML
    private Label bags;


    @FXML
    private Button makeARecording1;

    @FXML
    private TextField search1;

    @FXML
    private Label instructions1;

    @FXML
    private Button next1;

    @FXML
    private Button previous1;

    @FXML
    private Pane recordingOnDateMenu;


    @FXML
    private Pane mainMenu;

    @FXML
    private TextArea text;

    @FXML
    private Button goToTheEntry;

    @FXML
    private TextField searchWords;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label instructions;

    @FXML
    private Label instructions2;

    @FXML
    private TextField search;

    @FXML
    private Button two;

    @FXML
    private Button three;

    @FXML
    private Button four;

    @FXML
    private Button one;

    @FXML
    private Pane searchSystem;

    @FXML
    private Button backToMenu;


    public void stopPrograms() {
        instructions.setText("");
    }


    @FXML
    void find(ActionEvent event) {

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
        } else {
            instructions.setText("       Вы ввели неверный формат даты, попробуйте снова");
        }
    }

    @FXML
    void transfer(ActionEvent event) {
        Transfer transfer = new Transfer();
        transfer.transfers();
    }


    @FXML
    void transfer1(ActionEvent event) {
        Transfer transfer = new Transfer();
        transfer.receiving();
    }

    @FXML
    void goMenu(ActionEvent event) {
           mainMenu.setVisible(true);
    }

    @FXML
    void one (ActionEvent event1) {
        text.setText(text1);
        date = one.getText();
        wordsMenu.setVisible(false);
        searchWords.setText("");
        one.setVisible(false);
        two.setVisible(false);
        three.setVisible(false);
        four.setVisible(false);
        mainMenu.setVisible(false);
        menuAndBack = false;
    }

    @FXML
    void two(ActionEvent event) {
        text.setText(text2);
        date = two.getText();
        wordsMenu.setVisible(false);
        searchWords.setText("");
        one.setVisible(false);
        two.setVisible(false);
        three.setVisible(false);
        four.setVisible(false);
        mainMenu.setVisible(false);
        menuAndBack = false;
    }

    @FXML
    void three(ActionEvent event) {
        text.setText(text3);
        date = three.getText();
        wordsMenu.setVisible(false);

        one.setVisible(false);
        two.setVisible(false);
        three.setVisible(false);
        four.setVisible(false);
        mainMenu.setVisible(false);
        searchWords.setText("");
        menuAndBack = false;
    }

    @FXML
    void four(ActionEvent event) {
        text.setText(text4);
        date = four.getText();
        wordsMenu.setVisible(false);

        one.setVisible(false);
        two.setVisible(false);
        three.setVisible(false);
        four.setVisible(false);
        searchWords.setText("");
        menuAndBack = false;
        mainMenu.setVisible(false);
    }




    @FXML
    void next(ActionEvent event) {
        i = i + 1;
        String projectPath = Paths.get("").toAbsolutePath().toString();
        Photo photo1 = new Photo();

        if (photo1.loadPhoto(date, i)) {
            previous.setVisible(true);
            photo.setVisible(true);
            String imagePath = "file:///" + projectPath.replace("\\", "/") + "/dataPhoto/" + date + "_" + i;
            Image image = new Image(imagePath);
            photo.setImage(image);
            error.setText("");
            previous.setVisible(true);
        }
        else {
            error.setText("                   Фото не найдено");
            photo.setVisible(false);
            next.setVisible(false);

        }
    }

    @FXML
    void previous(ActionEvent event) {
        i = i - 1;
        String projectPath = Paths.get("").toAbsolutePath().toString();
        Photo photo1 = new Photo();
        if (photo1.loadPhoto(date, i)) {
            next.setVisible(true);
            photo.setVisible(true);
            String imagePath = "file:///" + projectPath.replace("\\", "/") + "/dataPhoto/" + date + "_" + i;
            Image image = new Image(imagePath);
            System.out.println(image);
            photo.setImage(image);
            error.setText("");


        }
        else {
            error.setText("                   Фото не найдено");
            photo.setVisible(false);
            previous.setVisible(false);
        }

    }

    @FXML
    void ViewPhoto(ActionEvent event) {
        next.setVisible(true);
        previous.setVisible(true);
        String projectPath = Paths.get("").toAbsolutePath().toString();
        Photo photo1 = new Photo();
        if (photo1.loadPhoto(date, i)) {
            String imagePath = "file:///" + projectPath.replace("\\", "/") + "/dataPhoto/" + date + "_" + i ;
            Image image = new Image(imagePath);
            System.out.println(image);
            photo.setImage(image);
        }
        else {
            photo.setVisible(false);
            error.setText("                   Фото не найдено");
            next.setVisible(false);
            previous.setVisible(false);
        }
    }
    @FXML
    void next1(ActionEvent event) {
        System.out.println(i);
        one.setVisible(false);
        two.setVisible(false);
        three.setVisible(false);
        four.setVisible(false);
        i = i - 4;


        if (i >= 4) {

            one.setVisible(true);
            two.setVisible(true);
            three.setVisible(true);
            four.setVisible(true);

            text1 = arrayLists.get(1).get(i - 1).toString();
            text2 = arrayLists.get(1).get(i - 2).toString();
            text3 = arrayLists.get(1).get(i - 3).toString();
            text4 = arrayLists.get(1).get(i - 4).toString();

            one.setText(arrayLists.get(0).get(0).toString());
            two.setText(arrayLists.get(0).get(1).toString());
            three.setText(arrayLists.get(0).get(2).toString());
            four.setText(arrayLists.get(0).get(3).toString());


        }
        switch (i) {
            case 1:
                one.setVisible(true);
                one.setText(arrayLists.get(0).get(i - 1).toString());
                text1 = arrayLists.get(1).get(0).toString();
                next1.setVisible(false);

                break;

            case 2:

                one.setVisible(true);
                two.setVisible(true);

                one.setText(arrayLists.get(0).get(i - 1).toString());
                two.setText(arrayLists.get(0).get(i - 2).toString());

                text1 = arrayLists.get(1).get(i - 1).toString();
                text2 = arrayLists.get(1).get(i - 2).toString();
                next1.setVisible(false);

                break;

            case 3:

                one.setVisible(true);
                two.setVisible(true);
                three.setVisible(true);

                one.setText(arrayLists.get(0).get(i - 1).toString());
                two.setText(arrayLists.get(0).get(i - 2).toString());
                three.setText(arrayLists.get(0).get(i - 3).toString());

                text1 = arrayLists.get(1).get(i - 1).toString();
                text2 = arrayLists.get(1).get(i - 2).toString();
                text3 = arrayLists.get(1).get(i - 3).toString();
                next1.setVisible(false);

                break;
            case 4 :
                one.setVisible(true);
                two.setVisible(true);
                three.setVisible(true);
                four.setVisible(true);

                text1 = arrayLists.get(1).get(i - 1).toString();
                text2 = arrayLists.get(1).get(i - 2).toString();
                text3 = arrayLists.get(1).get(i - 3).toString();
                text4 = arrayLists.get(1).get(i - 4).toString();

                one.setText(arrayLists.get(0).get(i - 1).toString());
                two.setText(arrayLists.get(0).get(i - 2).toString());
                three.setText(arrayLists.get(0).get(i - 3).toString());
                four.setText(arrayLists.get(0).get(i - 4).toString());
                break;
        }
        previous1.setVisible(true);
        System.out.println(i);
    }


    @FXML
    void previous1(ActionEvent event) {
        i = i + 4;

        one.setVisible(true);
        two.setVisible(true);
        three.setVisible(true);
        four.setVisible(true);

        text1 = arrayLists.get(1).get(i - 1).toString();
        text2 = arrayLists.get(1).get(i - 2).toString();
        text3 = arrayLists.get(1).get(i - 3).toString();
        text4 = arrayLists.get(1).get(i - 4).toString();

        one.setText(arrayLists.get(0).get(i - 1).toString());
        two.setText(arrayLists.get(0).get(i - 2).toString());
        three.setText(arrayLists.get(0).get(i - 3).toString());
        four.setText(arrayLists.get(0).get(i - 4).toString());

        next1.setVisible(true);
        System.out.println(i);
        if (i == d){
            previous1.setVisible(false);
        }

    }
    @FXML
    void attachPhoto(ActionEvent event) {

        JFileChooser jFileChooser = new JFileChooser();

        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String projectPath = Paths.get("").toAbsolutePath().toString();
            Photo photo1 = new Photo();
            photo1.savePhoto(date, jFileChooser.getSelectedFile().toString());
            photo.setVisible(true);

            String imagePath = "file:///" + projectPath.replace("\\", "/") + "/dataPhoto/" + date + "_" + i;
            Image image = new Image(imagePath);
            System.out.println(date);
            photo.setImage(image);
            error.setText("");
            next.setVisible(true);
            previous.setVisible(true);

        } else {
            photo.setImage(null);
            error.setText("                   Ошибка");
        }
    }

    @FXML
    void goTo(ActionEvent event) {

        text.setText(note.loadFromFile(search.getText()));
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
        calendar.setEditable(false);

    }

    @FXML
    void writeDownTheDate1(ActionEvent event) {
        recordingOnDateMenu.setVisible(true);
        menuAndBack = true;
        calendar.setEditable(false);


    }

    @FXML
    void words(ActionEvent event) {
        wordsMenu.setVisible(true);
        menuAndBack = true;
        searchSystem.setVisible(false);
    }


    @FXML
    void attach(ActionEvent event) {
        menuPhoto.setVisible(true);
    }


    @FXML
    void makeARecording1(ActionEvent event) {

    }

    @FXML
    void makeARecording(ActionEvent event) {
        mainMenu.setVisible(false);
        date = calendar.getValue().toString();
        recordingOnDateMenu.setVisible(false);

    }


    @FXML
    void startFind(ActionEvent event) {
        mainMenu.setVisible(false);
        searchSystem.setVisible(true);
        menuAndBack = true;

    }

    @FXML
    void startSearching(ActionEvent event) {
        Search search1 = new Search();
        arrayLists = search1.wordCheck(searchWords.getText());


        if (arrayLists.size() == 0) {
            instructions2.setText("                               ВЫ ОСТАВИЛИ ПОЛЕ ДЛЯ ВВОДА ПУСТЫМ ИЛИ ОНО ЗАПОЛНЕНО ТОЛЬКО ПРОБЕЛАМИ");
        } else {
            i = arrayLists.get(0).size();
            d = arrayLists.get(0).size();
            instructions2.setText("                                                      ВВЕДИТЕ СЛОВО(А) ДЛЯ ПРОИСКА ЗАМЕТОК С НИМ(И)");
            switch (i) {

                case 0:
                    instructions2.setText("                                                                                ЗАПИСЬ НЕ НАЙДЕНА");
                    one.setVisible(false);
                    two.setVisible(false);
                    three.setVisible(false);
                    four.setVisible(false);
                    next1.setVisible(false);
                    previous1.setVisible(false);
                    break;
                case 1:
                    if (arrayLists.get(0).get(0).equals("ноль")) {
                        instructions2.setText("                                                       Ваш запрос пустой или содержит только пробелы");
                    } else {
                        one.setVisible(true);
                        one.setText(arrayLists.get(0).get(i - 1).toString());
                        System.out.println(i);
                        text1 = arrayLists.get(1).get(i - 1).toString();
                    }
                    break;


                case 2:
                    one.setVisible(true);
                    two.setVisible(true);

                    one.setText(arrayLists.get(0).get(i - 1).toString());
                    two.setText(arrayLists.get(0).get(i - 2).toString());

                    text1 = arrayLists.get(1).get(i - 1).toString();
                    text2 = arrayLists.get(1).get(i - 2).toString();

                    break;

                case 3:
                    one.setVisible(true);
                    two.setVisible(true);
                    three.setVisible(true);

                    one.setText(arrayLists.get(0).get(i - 1).toString());
                    two.setText(arrayLists.get(0).get(i - 2).toString());
                    three.setText(arrayLists.get(0).get(i - 3).toString());

                    text1 = arrayLists.get(1).get(i - 1).toString();
                    text2 = arrayLists.get(1).get(i - 2).toString();
                    text3 = arrayLists.get(1).get(i - 3).toString();
                    break;

            }
            if (i == 4) {
                one.setVisible(true);
                two.setVisible(true);
                three.setVisible(true);
                four.setVisible(true);

                text1 = arrayLists.get(1).get(i - 1).toString();
                text2 = arrayLists.get(1).get(i - 2).toString();
                text3 = arrayLists.get(1).get(i - 3).toString();
                text4 = arrayLists.get(1).get(i - 4).toString();

                one.setText(arrayLists.get(0).get(i - 1).toString());
                two.setText(arrayLists.get(0).get(i - 2).toString());
                three.setText(arrayLists.get(0).get(i - 3).toString());
                four.setText(arrayLists.get(0).get(i - 4).toString());
            }


            System.out.println(d);
            if (i > 4) {
                next1.setVisible(true);

                one.setVisible(true);
                two.setVisible(true);
                three.setVisible(true);
                four.setVisible(true);

                text1 = arrayLists.get(1).get(i - 1).toString();
                text2 = arrayLists.get(1).get(i - 2).toString();
                text3 = arrayLists.get(1).get(i - 3).toString();
                text4 = arrayLists.get(1).get(i - 4).toString();

                one.setText(arrayLists.get(0).get(i - 1).toString());
                two.setText(arrayLists.get(0).get(i - 2).toString());
                three.setText(arrayLists.get(0).get(i - 3).toString());
                four.setText(arrayLists.get(0).get(i - 4).toString());
            }
        }
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
        one1 = true;


    }


    @FXML
    void goBack (ActionEvent event){
        if (!menuAndBack) {
            searchSystem.setVisible(false);
            search.setText("");
            goToTheEntry.setVisible(false);
            recordingOnDateMenu.setVisible(false);
            mainMenu.setVisible(false);
            menuPhoto.setVisible(false);
            next.setVisible(false);
            previous.setVisible(false);
            photo.setImage(null);
            System.out.println("S");
            wordsMenu.setVisible(false);

            one.setVisible(false);
            two.setVisible(false);
            three.setVisible(false);
            four.setVisible(false);
            searchWords.setText("");


        }
        if (menuAndBack){
            System.out.println("ss");
            searchSystem.setVisible(false);
            recordingOnDateMenu.setVisible(false);
            menuPhoto.setVisible(false);
            mainMenu.setVisible(true);
            menuAndBack = false;
            next.setVisible(false);
            previous.setVisible(false);
            photo.setImage(null);
            wordsMenu.setVisible(false);
            searchWords.setText("");
            one.setVisible(false);
            two.setVisible(false);
            three.setVisible(false);
            four.setVisible(false);

        }

    }

    @FXML
    void findByRecord (ActionEvent event){
        searchSystem.setVisible(true);
        menuAndBack = false;
    }

    @FXML
    void save (ActionEvent event) {
        if (one1) {
            note.savingToFile(LocalDate.now().toString(), text.getText());
            one1 = false;
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

