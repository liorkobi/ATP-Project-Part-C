package View;

import Model.IModel;
import Model.MyModel;
import ViewModel.MyViewModel;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
        IModel model = new MyModel();
        MyViewModel MYVM= new MyViewModel(model);
        model.ab(MYVM);

        primaryStage.setTitle("Welcome");
        FXMLLoader welcomeFXML = new FXMLLoader(getClass().getResource("/View/Welcome.fxml"));
        Parent welcome = welcomeFXML.load();
        welcomeS = new Scene(welcome,900,600);
        Welcome welcomeController = welcomeFXML.getController();

        FXMLLoader playerFXML = new FXMLLoader(getClass().getResource("/View/PlayerController.fxml"));
        Parent player = playerFXML.load();
        PlayerController playerController = playerFXML.getController();
        playerS = new Scene(player,900,600);


        FXMLLoader gameFXML = new FXMLLoader(getClass().getResource("/View/MyView.fxml"));
        Parent game = gameFXML.load();
        MyViewController gameController = gameFXML.getController();
        gameS = new Scene(game,900,600);

        welcomeController.setStage(primaryStage);
        welcomeController.setScene(playerS);

        playerController.setStage(welcomeController.getPrimaryStage());
        playerController.setScene(gameS);

        playerController.setVM(MYVM);
        gameController.setResizeEvent(gameS);
        playerController.setVC(gameController);

        MYVM.ab(gameController);

        SetStageCloseEvent(primaryStage, gameController);
        primaryStage.setScene(welcomeS);
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


    public static void main(String[] args) {
        launch(args);
    }
}
