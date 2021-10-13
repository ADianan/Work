package edu.curtin.bustimetable;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;

/**
 * Entry point for the bus timetabling app. It can be invoked with the
 * command-line parameter --locale=[value].
 */
public class BusTimetableApp extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        var localeString = getParameters().getNamed().get("locale");
        ResourceBundle bunmdle = null;
        if (localeString != null) {
            String[] loc = localeString.split("-");
            Locale locale1 = new Locale(loc[0], loc[1]);
            bunmdle = ResourceBundle.getBundle("bundle", locale1);
            // FIXME: A locale was specified on the command-line. What are you going to do
            // about it? ;-)
        }

        else {
            System.out.println("Default");
            bunmdle = ResourceBundle.getBundle("bundle");
        }

        var entries = FXCollections.<TimetableEntry>observableArrayList();
        FileIO fileIO = new FileIO();
        LoadSaveUI loadSaveUI = new LoadSaveUI(stage, entries, fileIO);
        AddUI addUI = new AddUI(entries);
        new MainUI(stage, entries, loadSaveUI, addUI, bunmdle).display();
    }
}
