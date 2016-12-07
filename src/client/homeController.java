package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController {

    @FXML
    TextField voterF;

    //TODO: eventlistener for shortcut to get to admin tool

    public void authBtnClicked(ActionEvent event) throws IOException {
        int idField = Integer.parseInt(voterF.getText());
        if(Context.getInstance().currentVoter().authenticate(idField) == true && Context.getInstance().currentTally().isPollOver() == false){
            Context.getInstance().currentVoter().setVoterID(idField);
            //TODO: (Aaron) remove User identification from DB
            Parent candidateParent = FXMLLoader.load(getClass().getResource("candidateController.fxml"));
            Scene candidateScene = new Scene(candidateParent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(candidateScene);
            //appStage.setFullScreen(true);
            appStage.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Invalid Voter ID");
            alert.setContentText("Either you entered your ID incorrectly or you have already voted");
            alert.show();
        }
    }

}
