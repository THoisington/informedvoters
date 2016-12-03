package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        primaryStage.setScene(new Scene(root, 600, 550));
        primaryStage.show();

        if(Context.getInstance().getFirstStart()==true){
            Stage officialLogin = new Stage();
            Parent off = FXMLLoader.load(getClass().getResource("officialLogin.fxml"));
            officialLogin.initModality(Modality.WINDOW_MODAL);
            officialLogin.initOwner(primaryStage.getScene().getWindow());
            officialLogin.setScene(new Scene(off, 600, 550));
            officialLogin.show();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
