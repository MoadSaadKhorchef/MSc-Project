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
    
    
    
    
    
    
    /*
    @FXML
    void runButtonFunction(ActionEvent event) {
    
    try
    {
    //resultImage.setImage(mat2Image(imgSource));
    Thread outStreamReader = new Thread(new Runnable() {
    public void run() {
    
    try {
    
    //ExecutorService executor = Executors.newSingleThreadExecutor();
    ProcessBuilder processBuilder = new ProcessBuilder();
    
    processBuilder = processBuilder.redirectErrorStream(true);
    
    String imgDir = pictureNameTextField.getText();
    System.out.println(imgDir);
    
    String[] command = {"cmd.exe", "/c", "python.exe" ,"C:\\Users\\Moad\\Documents\\MSc Project\\Code\\Interface For Prediction\\RiverSegmentation\\src\\main\\java\\com\\mycompany\\riversegmentation\\Prediction.py",imgDir};
    
    processBuilder.directory(new File("C:\\Users\\Moad\\Documents\\MSc Project\\Code\\Interface For Prediction\\RiverSegmentation\\src\\main\\java\\com\\mycompany\\riversegmentation\\"));
    processBuilder.command(command);
    
    Process process = processBuilder.start();
    
    List<String> alist=new ArrayList<String>();
    List<String> flist=new ArrayList<String>();
    
    String line = null;
    BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
    
    while ((line = in.readLine()) != null) {
    in = new BufferedReader(new InputStreamReader(process.getInputStream()));
    
    alist.add(line);
    //flist = alist.stream().filter(l -> l.startsWith("Text")).collect(Collectors.toList());
    //flist = in.lines().filter(l -> l.startsWith("confidence")).collect(Collectors.toList());
    
    if(!alist.isEmpty()){
    //alist.forEach(System.out::println);
    //flist.forEach(cLine -> detectionResultTextArea.setText(cLine));
    final String s = alist.get(0);
    //int size = alist.size();
    //System.out.println(size);
    Platform.setImplicitExit(false);
    resultfeedback(s);
    //Platform.runLater(  () -> detectionResultTextArea.setText(s) )  ;
    //Thread.sleep(1000);
    line = null;
    }
    
    //TimeUnit.SECONDS.sleep(30);
    }
    
    int exitCode = process.waitFor();
    alist.clear();
    flist.clear();
    System.out.println("\nExited with error code : " + exitCode);
    
    } catch (Exception e) {
    e.printStackTrace();
    }
    //System.out.println("Exit reading process output");
    }
    });
    outStreamReader.start();
    
    // webCamCapture = new WebCamCapture(this.cameraFrame);
    
    //  String line;
    //  int exitCode = process.waitFor();
    //  System.out.println("\nExited with error code : " + exitCode);
    }catch (NullPointerException e){
    
    }finally{
    // resultImage.setImage(mat2Image(imResult));
    // resultImage.setImage(mat2Image(imgSource));
    }
    
    }
    
    
    public void resultfeedback (final String s){
    
    Platform.runLater(  () -> resultTextArea.setText(s) );
    
    }*/
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
