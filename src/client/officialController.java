package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class officialController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void authBtnClicked(ActionEvent event) throws IOException {
        String idField = "Pothering"; // TODO: Get user input
        Official official = new Official();
        if(official.authenticate(idField) == true){
            System.out.println("auth confirmed");

//            Parent candidateParent = FXMLLoader.load(getClass().getResource("candidateController.fxml"));
//            Scene candidateScene = new Scene(candidateParent);
//            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            appStage.setScene(candidateScene);
//            appStage.show();
        }
        else{
            //Return to start?
        }
    }

}
