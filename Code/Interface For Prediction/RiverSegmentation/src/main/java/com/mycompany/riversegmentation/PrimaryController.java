package com.mycompany.riversegmentation;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ImageView selectedImage;
    
    @FXML
    private TextArea resultTextArea;
    
    String str;
    String imgDir;

    @FXML
    void browseButtonFunction(ActionEvent event) {

        File file = openFile("Load Image");
        if (file != null)
        {
            str = file.getAbsolutePath();
            str = str.replace("\\", "//");
            //System.out.println(str);
            imgDir = str;
            pictureNameTextField.setText(str);
            
        }
        
        str = file.toURI().toString();
        //System.out.println(str);
        Image image = new Image(str);
         // simple displays ImageView the image as is
        selectedImage.setImage(image);
        //selectedImage.setFitHeight(450);
        //selectedImage.setFitWidth(250);
        selectedImage.setPreserveRatio(true);
        //System.out.println(str);
        

             
    }
        	
    public static File openFile(String title)
    {
        //FileChooser fileChooser;
        //fileChooser = new FileChooser();
        FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.getExtensionFilters().addAll(new javafx.stage.FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        fileChooser.setTitle(title);
        return fileChooser.showOpenDialog(new Stage());
    }
    
@FXML
    void runButtonFunction(ActionEvent event) {
       
        try
        {    
            //String imgDir = str;//pictureNameTextField.getText();
            //System.out.println(imgDir);


            ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\Moad\\anaconda3\\python.exe","C:\\Users\\Moad\\Documents\\MSc Project\\Code\\Interface For Prediction\\RiverSegmentation\\src\\main\\java\\com\\mycompany\\riversegmentation\\Prediction.py", imgDir);
            processBuilder.directory(new File("C:\\Users\\Moad\\Documents\\MSc Project\\Code\\Interface For Prediction\\RiverSegmentation\\src\\main\\java\\com\\mycompany\\riversegmentation\\"));

            Process process = processBuilder.start();

            String line = null;
            BufferedReader  in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader  er = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            
            while ((line = in.readLine()) != null) {
                 System.out.println(line);
            }
            
            while ((line = er.readLine()) != null) {
                 System.out.println(line);
            }           
            
            int exitCode = process.waitFor();
 
            System.out.println("\nExited with error code : " + exitCode);
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    
    
    }    
    
    
    
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
