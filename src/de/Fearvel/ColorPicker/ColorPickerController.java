package de.Fearvel.ColorPicker;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

public class ColorPickerController implements Initializable {
    @FXML
    public Slider SliderRed;
    @FXML
    public Slider SliderGreen;
    @FXML
    public Slider SliderBlue;
    @FXML
    public TextField rgbTextFieldRed;
    @FXML
    public TextField rgbTextFieldGreen;
    @FXML
    public TextField rgbTextFieldBlue;
    @FXML
    public Pane ColorPane;
    @FXML
    public TextField TextFieldColorHex;

    public String Color = "#ffffff";


    @FXML
    public void rgbSliderRefresh() {

        rgbTextFieldRed.setText(String.valueOf((int) SliderRed.getValue()));
        rgbTextFieldGreen.setText(String.valueOf((int) SliderGreen.getValue()));
        rgbTextFieldBlue.setText(String.valueOf((int) SliderBlue.getValue()));
        generateColor();
    }

    public void generateColor(){
        String color = "#";
        color += Integer.toHexString((int) SliderRed.getValue());
        color = adjustLenght(color, 3);
        color += Integer.toHexString((int) SliderGreen.getValue());
        color = adjustLenght(color, 5);
        color += Integer.toHexString((int) SliderBlue.getValue());
        color = adjustLenght(color, 7);
        ColorPane.setBackground(new Background(new BackgroundFill(Paint.valueOf(color), CornerRadii.EMPTY, Insets.EMPTY)));
        Color=color;
        TextFieldColorHex.setText(color);
    }
    public void rgbTextFieldAction() {

        int red = getColorFromString(rgbTextFieldRed.getText());
        int green = getColorFromString(rgbTextFieldGreen.getText());
        int blue = getColorFromString(rgbTextFieldBlue.getText());
        SliderRed.setValue((double) red);
        SliderGreen.setValue((double) green);
        SliderBlue.setValue((double) blue);
        generateColor();

    }

    public String adjustLenght(String s, int lenght) {
        while (s.length() < lenght) {
            s += "0";
        }
        return s;
    }
    public int getColorFromString(String s){
        try {
            return Integer.parseInt(s);
        } catch (Exception e){
            return 0;
        }
    }
    public void copyToClipboard(){
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(Color);
        clipboard.setContent(content);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ColorPane.setBackground(new Background(new BackgroundFill(Paint.valueOf(Color), CornerRadii.EMPTY, Insets.EMPTY)));
        TextFieldColorHex.setText(Color);

    }
}
