package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController {

    public void authBtnClicked(ActionEvent event) throws IOException {
        String idField = "test"; // TODO: Get user input (change this to integer)
        Voter voter = new Voter();
        if(voter.authenticate(idField) == true){

            //voter.setVoterID(idField);

            Parent candidateParent = FXMLLoader.load(getClass().getResource("candidateController.fxml"));
            Scene candidateScene = new Scene(candidateParent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(candidateScene);
            appStage.show();
        }
        else{
            //Return to start?
        }
    }

}
