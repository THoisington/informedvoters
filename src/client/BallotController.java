package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.Alert;
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

    int index = 0;
    ArrayList <RadioButton> buttons = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Context.getInstance().currentVoter().openBallot(); //When we get into the voting, we start using ballot not tally
        buttons.add(radioA);
        buttons.add(radioB);
        buttons.add(radioC);
        buttons.add(radioD);
        for(int i = 0; i < Context.getInstance().currentBallot().getCandidates().size() && i < 4; i++){
            index++;
            Candidate current = (Candidate) Context.getInstance().currentBallot().getCandidates().get(i);
            buttons.get(i).setText(current.getName());
        }

    }

    public void leftBtnClicked(ActionEvent event) throws IOException{
        if(index>=8) {

            index= index - 5;

            for (RadioButton x : buttons) {
                x.setText("");
                //show X
            }

            //index is currently 4, arraylist spot 3 gotten
            for (int i = 3; i >= 0; i--) {
                Candidate current = (Candidate) Context.getInstance().currentBallot().getCandidates().get(index);
                buttons.get(i % 4).setText(current.getName());
                index--;
            }

            for (RadioButton x : buttons) {
                if (x.getText().equals("")) {
                    //hide X
                }
            }
        }
    }

    public void rightBtnClicked(ActionEvent event) throws IOException{

        if(index<Context.getInstance().currentBallot().getCandidates().size()) {

            for (RadioButton x : buttons) {
                x.setText("");
                //show X
            }

            //Test
            ArrayList <Candidate> test = Context.getInstance().currentBallot().getCandidates();

            //index is currently 4, arraylist spot 3 gotten
            for (int i = 0; i + index <= Context.getInstance().currentBallot().getCandidates().size() && i < 4; i++) {
                Candidate current = (Candidate) Context.getInstance().currentBallot().getCandidates().get(index);
                buttons.get(i % 4).setText(current.getName()); //% not needed?
                index++;
            }

            for (RadioButton x : buttons) {
                if (x.getText().equals("")) {
                    //hide X
                }
            }
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
                nonEmpty = true;
            }
        }

        if(nonEmpty == true) {
            Context.getInstance().currentVoter().vote();
            Tally testTally = Context.getInstance().currentTally();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Voting Complete");
            alert.setContentText("Thank you for your democracy!");
            alert.show();

            //TODO: (Aaron) set voter to having voted in DB

            Context.getInstance().currentBallot().print();

            Context.getInstance().refresh();
            Parent parent = FXMLLoader.load(getClass().getResource("home.fxml"));
            Scene homeScene = new Scene(parent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(homeScene);
            //appStage.setFullScreen(true);
            appStage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Invalid ballot");
            alert.setContentText("You must select a candidate!");
            alert.show();
        }


    }

}
