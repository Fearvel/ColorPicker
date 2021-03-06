package de.Fearvel.ColorPicker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class of the color picker
 * @author Andreas Schreiner
 * @version 1.1
 * See {@linktourl https://github.com/Fearvel/ColorPicker}
 */
public class Main extends Application {

    /**
     * Definition of the JavaFX main window
     * @param primaryStage -
     * @throws Exception -
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ColorPicker.fxml"));
        primaryStage.setTitle("Color Picker");
        primaryStage.setScene(new Scene(root, 400, 130));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Main method
     * @param args Start Parameters(not used)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
