/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashcard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tafhim
 */
public class PlayCardController implements Initializable {

    @FXML
    private AnchorPane scenePane;
    Stage stage;
    @FXML
    private Button exitButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button fileButton;
    @FXML
    private TextField textField;
    @FXML
    private Button nextButton;
    @FXML
    private Button showButton;

     private int totalLine = 0;
    private int questionNo = 0;
    
    String fileOpeningPath;
    int n_1 = 0;

    FileChooser obj1 = new FileChooser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void exitAction(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("You are about to exit!");
            alert.setContentText("Do you want to leave ?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                stage = (Stage) scenePane.getScene().getWindow();
                stage.close();
            }
    }

    @FXML
    private void homeAction(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FlashCard.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stageHome = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageHome.setScene(scene);
            stageHome.setTitle("Flash Card");
            stageHome.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void fileAction(ActionEvent event) {
          try {

            System.out.println("File Action working");
            obj1.setTitle("Open File Dialog");
            obj1.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt files", "*.txt"));
            File file = obj1.showOpenDialog(stage);
            if (file != null) {

                fileOpeningPath = file.getAbsolutePath();
                 fileOpenEssentials();

            }
        } catch (Exception e) {
            System.out.println("Open a valid file");
        }
    }

    @FXML
    private void textAction(ActionEvent event) {
    }

    @FXML
    private void nextAction(ActionEvent event) {
         try {
            // System.out.println("v");
            if (n_1 >= totalLine) {
                textField.setDisable(true);
            }
            String line = Files.readAllLines(Paths.get(fileOpeningPath)).get(n_1);
            System.out.println(n_1);
            textField.setText(line);

            n_1 += 2;

        } catch (Exception e) {
            System.out.println("No file found");
        }
    }

    @FXML
    private void showAction(ActionEvent event) {
          try {
            System.out.println("Show Action working");

            if (n_1 + 1 >= totalLine) {
                textField.setDisable(true);
            }

            String line = Files.readAllLines(Paths.get(fileOpeningPath)).get(n_1 - 1);
            System.out.println(line);
            textField.setText(line);

        } catch (Exception e) {
            System.out.println("No file found");
        }
    }

     public void fileOpenEssentials() throws FileNotFoundException, IOException {
        //System.out.println("Initializer working");

        try {
            textField.setDisable(false);
            totalLine = 0;
            n_1 = 0;
            textField.setText("");
            File file1 = new File(fileOpeningPath);
            if (file1.exists()) {
                FileReader fr = new FileReader(file1);
                LineNumberReader lr = new LineNumberReader(fr);
                while (lr.readLine() != null) {
                    totalLine++;
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error Occured");
        }
       

    }
  
    
}
