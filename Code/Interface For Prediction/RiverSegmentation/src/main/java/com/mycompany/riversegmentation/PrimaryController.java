package com.mycompany.riversegmentation;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class PrimaryController {
    
    
    @FXML
    private TextField pictureNameTextField;

    @FXML
    private Button browseButton;

    @FXML
    private Button runButton;

    @FXML
    void browseButtonFunction(ActionEvent event) {

        File file = openFile("Load Image");
        if (file != null)
        {

            pictureNameTextField.setText(file.getAbsolutePath());
            
        }
             
    }
        	
    public static File openFile(String titel)
    {
        //FileChooser fileChooser;
        //fileChooser = new FileChooser();
        FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.getExtensionFilters().addAll(new javafx.stage.FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        fileChooser.setTitle(titel);
        return fileChooser.showOpenDialog(new Stage());
    }

    @FXML
    void runButtonFunction(ActionEvent event) {

    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
