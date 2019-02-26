package de.Fearvel.ColorPicker;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller Class for the JavaFX Window
 * Contains functions and methods to generate and display colors
 * @author Andreas Schreiner
 * @version 1.1
 * See {@linktourl https://github.com/Fearvel/ColorPicker}
 */
public class ColorPickerController implements Initializable {

    /**
     * Definition of the Color RED Slider
     */
    @FXML
    public Slider SliderRed;

    /**
     * Definition of the Color GREEN Slider
     */
    @FXML
    public Slider SliderGreen;

    /**
     * Definition of the Color BLUE Slider
     */
    @FXML
    public Slider SliderBlue;

    /**
     * Definition of the Color RED TextField used to display a value between 0 and 255
     */
    @FXML
    public TextField RgbTextFieldRed;

    /**
     * Definition of the Color GREEN TextField used to display a value between 0 and 255
     */
    @FXML
    public TextField RgbTextFieldGreen;

    /**
     * Definition of the Color BLUE TextField used to display a value between 0 and 255
     */
    @FXML
    public TextField RgbTextFieldBlue;

    /**
     * Definition of the Pane, which displays the mixed Color
     */
    @FXML
    public Pane ColorPane;

    /**
     * Definition of the TextField, which displays the HEX value of the mixed Color
     */
    @FXML
    public TextField TextFieldColorHex;

    /**
     * Contains the mixed color, start value is black
     */
    public String Color = "#000000";

    /**
     * Slider Event onMouseDragged
     * Updates the TextFields which contain the value of the specific color.
     * It also calls generateColor.
     */
    @FXML
    public void RgbSliderRefresh() {

        RgbTextFieldRed.setText(FixHexValue(String.valueOf((int) SliderRed.getValue())));
        RgbTextFieldGreen.setText(FixHexValue(String.valueOf((int) SliderGreen.getValue())));
        RgbTextFieldBlue.setText(FixHexValue(String.valueOf((int) SliderBlue.getValue())));
        GenerateColor();
    }

    /**
     * TextField Event onKeyReleased (for manually entering a value)
     * Updates the Sliders whit the set colors.
     * It also calls generateColor.
     */
    @FXML
    public void RgbTextFieldAction() {
        SliderRed.setValue((double) getColorFromString(RgbTextFieldRed.getText()));
        SliderGreen.setValue((double) getColorFromString(RgbTextFieldGreen.getText()));
        SliderBlue.setValue((double) getColorFromString(RgbTextFieldBlue.getText()));
        GenerateColor();
    }

    /**
     * Generates the Color out of the 3 Base Color values and displays them on the Pane, and on The TextField as hex
     */
    public void GenerateColor() {
        String color = "#";
        color += FixHexValue(Integer.toHexString((int) SliderRed.getValue()));
        color = AdjustLength(color, 3);
        color += FixHexValue(Integer.toHexString((int) SliderGreen.getValue()));
        color = AdjustLength(color, 5);
        color += FixHexValue(Integer.toHexString((int) SliderBlue.getValue()));
        color = AdjustLength(color, 7);
        ColorPane.setBackground(
                new Background(
                        new BackgroundFill(
                                Paint.valueOf(color),
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );
        Color = color;
        TextFieldColorHex.setText(color);
    }

    /**
     * Adds a 0 before a 1 char hex string to resolve an error where a wrong color got displayed
     * @param s hex value
     * @return hex value
     */
    public String FixHexValue(String s) {
        if (s.length() == 1)
            s = "0" + s;
        return s;
    }

    /**
     * Appends "0" to a string until the specified length is met
     * @param s String to be filled up
     * @param length Expected Length
     * @return adjusted String
     */
    public String AdjustLength(String s, int length) {
        while (s.length() < length) {
            s += "0";
        }
        return s;
    }

    /**
     * Tries to parse an integer from a string
     * @param s String, which should contain a integer
     * @return int value
     */
    public int getColorFromString(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Initialize the Window
     * The Pane ColorPane which contains the mixed color will be initialized with the standard color #FFFFFF
     * The TextField TextFieldColorHex wich contains the hex value of the ColorPane will be initialized with the
     * standard color hex of #FFFFFF
     *
     * @param location  -
     * @param resources -
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ColorPane.setBackground(
                new Background(
                        new BackgroundFill(
                                Paint.valueOf(Color),
                                CornerRadii.EMPTY,
                                Insets.EMPTY)
                )
        );
        TextFieldColorHex.setText(Color);
        RgbTextFieldRed.setText("0");
        RgbTextFieldGreen.setText("0");
        RgbTextFieldBlue.setText("0");
    }
}