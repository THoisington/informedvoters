package client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    Scene begin;
    Label voterID,filler;
    TextField voterTextField;
    Button authBtn;

    @Override
    public void start(Stage primaryStage) throws Exception{

        /*************** Initial Scene/Layout Start *************/
        /********************************************************/

        StackPane authLayout = new StackPane();
        voterID = new Label("Voter ID:");
        voterTextField = new TextField();
        authBtn = new Button();
        authBtn.setText("Submit ID");
        authBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String idField = "test"; // TODO: Get user input
                Voter voter = new Voter();
                if(voter.authenticate(idField) == true){
                    primaryStage.setScene(begin);
                }
                else{
                    //Return to start?
                }
            }
        });

        authLayout.getChildren().addAll(voterID,voterTextField,authBtn);
        Scene start = new Scene(authLayout,300,250);

        /********************************************************/
        /*************** Initial Scene/Layout End ***************/

        /*************** Second Scene/Layout Start *************/
        /********************************************************/

        StackPane layout2 = new StackPane();
        filler = new Label("You made it");

        layout2.getChildren().add(filler);
        begin = new Scene(layout2,300,250);

        /********************************************************/
        /*************** Second Scene/Layout End ***************/

        primaryStage.setTitle("Informed Voters System");
        primaryStage.setScene(start);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
