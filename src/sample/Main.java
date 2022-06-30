package sample;

import Model.IModel;
import Model.MyModel;
import View.MyViewController;
import View.Welcome;
import View.newFile;
import ViewModel.MyViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Observer;
import java.util.Optional;

public class Main extends Application {
    public  Scene welcomeS;
    public  Scene playerS;
    public  Scene gameS;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //mainstage;
        primaryStage.setTitle("Welcome");
        FXMLLoader welcomeFXML = new FXMLLoader(getClass().getResource("/View/Welcome.fxml"));
        FXMLLoader gameFXML = new FXMLLoader(getClass().getResource("/View/MyView.fxml"));
        Parent welcome = welcomeFXML.load();
        Parent game = gameFXML.load();

        Welcome welcomeController = welcomeFXML.getController();
        MyViewController gameController = gameFXML.getController();

        welcomeS = new Scene(welcome,700,700);
        gameS = new Scene(game,700,700);

        primaryStage.setScene(welcomeS);
        welcomeController.setStage(primaryStage);
        welcomeController.setScene(gameS);
        gameController.setStage(welcomeController.getPrimaryStage());



//        FXMLLoader playerFXML = new FXMLLoader(getClass().getResource("/View/Welcome.fxml"));
//        Parent player = playerFXML.load();
//        PlayerController playerController = playerFXML.getController();
//        playerS = new Scene(player,700,700);
//        playerController.setStage(primaryStage);
//        playerController.setScene(playerS);


        IModel model = new MyModel();
        MyViewModel MYVM= new MyViewModel(model);
        model.ab(MYVM);
        gameController.setVM(MYVM);
        welcomeController.setVM(MYVM);
        MYVM.ab(gameController);

        //primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }


//    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
//            a.setTitle("EXIT");
//            a.setContentText("are youU?");
//    Optional<ButtonType> res=a.showAndWait();
//            if(res.get()==ButtonType.OK){
//        System.exit(0);
//    }
//
//            else {a.close();}


    public static void main(String[] args) {
        launch(args);
    }
}
