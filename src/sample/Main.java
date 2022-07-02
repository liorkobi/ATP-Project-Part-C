package sample;

import Model.IModel;
import Model.MyModel;
import View.MyViewController;
import View.PlayerController;
import View.Welcome;
import ViewModel.MyViewModel;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
        FXMLLoader playerFXML = new FXMLLoader(getClass().getResource("/View/PlayerController.fxml"));
        FXMLLoader gameFXML = new FXMLLoader(getClass().getResource("/View/MyView.fxml"));

        Parent welcome = welcomeFXML.load();
        Parent player = playerFXML.load();
        Parent game = gameFXML.load();

        Welcome welcomeController = welcomeFXML.getController();
        PlayerController playerController = playerFXML.getController();
        MyViewController gameController = gameFXML.getController();

        welcomeS = new Scene(welcome,700,700);
        playerS = new Scene(player,700,700);
        gameS = new Scene(game,700,700);

        primaryStage.setScene(welcomeS);
        welcomeController.setStage(primaryStage);
        welcomeController.setScene(playerS);
        playerController.setStage(welcomeController.getPrimaryStage());
        playerController.setScene(gameS);
        gameController.setStage(playerController.getPrimaryStage());

        IModel model = new MyModel();
        MyViewModel MYVM= new MyViewModel(model);
        model.ab(MYVM);
        playerController.setVM(MYVM);
        gameController.setResizeEvent(gameS);
        playerController.setVC(gameController);
//        welcomeController.setVM(MYVM);
        MYVM.ab(gameController);
        SetStageCloseEvent(primaryStage, gameController);

        //primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }

    private void SetStageCloseEvent(Stage primaryStage, MyViewController viewController) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent windowEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Quit Game");
                alert.setContentText("Are you sure you want to QUIT?");
//               DialogPane dialog =  alert.getDialogPane();
//               dialog.getStylesheets().add(getClass().getResource("View/Alert.css").toExternalForm());
//               dialog.getStyleClass().add("dialog");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    viewController.stopServers();
                    System.exit(0);
                } else { windowEvent.consume(); }
            }
        });
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
