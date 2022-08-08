/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashcard;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tafhim
 */
public class FlashCardController implements Initializable {

    @FXML
    private AnchorPane scenePane;
    @FXML
    private Button exitButton;
    @FXML
    private Label titleLabel;
    @FXML
    private Button createCardButton;
    @FXML
    private Button playCardbutton;

    Stage stage;
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
    private void createCardAction(ActionEvent event) {
         try {
            System.out.println("Create Card Action");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("createCard.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            CreateCardController createCard = loader.getController();
            
          
            Stage stageCreate = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageCreate.setScene(scene);
            stageCreate.setTitle("Create Flash Card");
            stageCreate.show();

        } catch (IOException e) {
        }
    }

    @FXML
    private void playCardAction(ActionEvent event) {
         System.out.println("Play card Action");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("playCard.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            PlayCardController welcome = loader.getController();

            Stage stagePlay = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stagePlay.setScene(scene);
            stagePlay.setTitle("Play Flash Card");
            stagePlay.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
