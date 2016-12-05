package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BallotController implements Initializable {

    @FXML
    RadioButton radioA, radioB, radioC, radioD;
    @FXML
    Label officeName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Context.getInstance().currentVoter().openBallot(); //When we get into the voting, we start using ballot not tally
        ArrayList <RadioButton> buttons = new ArrayList<>();
        buttons.add(radioA);
        buttons.add(radioB);
        buttons.add(radioC);
        buttons.add(radioD);
        for(int i = 0; i < Context.getInstance().currentBallot().getCandidates().size() && i < 4; i++){
            Candidate current = (Candidate) Context.getInstance().currentBallot().getCandidates().get(i);
            buttons.get(i).setText(current.getName());
        }

    }

    //TODO: (Tanner) button handler method. If selected, popup verification and then create ballot and submit to DB
    public void submitBtnClicked(ActionEvent event) throws IOException{

        Boolean nonEmpty = false;
        ArrayList <RadioButton> candidates  = new ArrayList<RadioButton>();
        candidates.add(radioA);
        candidates.add(radioB);
        candidates.add(radioC);
        candidates.add(radioD);
        Ballot testBallot = Context.getInstance().currentBallot(); //test

//
        for (RadioButton current: candidates) {
            if(current.isSelected() == true){
                Context.getInstance().currentBallot().addOfficeSelection(current.getText()); //TODO: Next test this
                //testBallot.addOfficeSelection(current.getText());
                nonEmpty = true;
            }
        }

        if(nonEmpty == true) {
            Context.getInstance().currentVoter().vote();
            Tally testTally = Context.getInstance().currentTally();
            //TODO: (Tanner) Thank user, return to beginning
            Context.getInstance().refresh();
            Parent parent = FXMLLoader.load(getClass().getResource("home.fxml"));
            Scene homeScene = new Scene(parent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(homeScene);
            //appStage.setFullScreen(true);
            appStage.show();
        }else{
            //TODO: (Tanner) tell user they need to pick someone
        }


    }

}
