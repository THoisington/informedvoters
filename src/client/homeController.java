package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController {

    public void authBtnClicked(ActionEvent event) throws IOException {
        String idField = "test"; // TODO: (Tanner) Get user input (change this to integer)
        if(Context.getInstance().currentVoter().authenticate(idField) == true && Context.getInstance().currentTally().isPollOver() == false){
        //TODO: (Tanner) null pointer on second run ^
            //Context.getInstance().currentVoter().setVoterID(idField);

            Parent candidateParent = FXMLLoader.load(getClass().getResource("candidateController.fxml"));
            Scene candidateScene = new Scene(candidateParent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(candidateScene);
            //appStage.setFullScreen(true);
            appStage.show();
        }
        else{
            //Return to start?
        }
    }

}
