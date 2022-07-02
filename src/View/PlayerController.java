package View;

import ViewModel.MyViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlayerController {
    Stage stage;
    Scene scene;
    public MyViewModel MYVM;
    public newFile generate;
    public MyViewController MVC;


    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;

    }

    public void setVM(MyViewModel MV) {
        MYVM = MV;
    }


    public void setScene(Scene gameS) {
        this.scene = gameS;
    }

    public Stage getPrimaryStage() {
        return stage;
    }

    @FXML
    public void PlayerSelected() throws Exception {
        stage.setScene(scene);
        newFile.setMV(MYVM);
        generate = new newFile();
        MVC.setNF(generate);
        generate.generate();
        System.out.println("genert");
        //stage.show();
    }

    public void setVC(MyViewController gameController) {
        this.MVC = gameController;
        MVC.setVM(MYVM);
    }


    public void Tsolider() throws Exception {
        MVC.setPlayerAcordingToUserChoise("Tsolider");
        PlayerSelected();
    }

    public void Ssolider() throws Exception {
        MVC.setPlayerAcordingToUserChoise("Ssolider");
        PlayerSelected();
    }

    public void Csolider() throws Exception {
        MVC.setPlayerAcordingToUserChoise("Csolider");
        PlayerSelected();
    }

    public void black() throws Exception {
        MVC.setPlayerAcordingToUserChoise("black");
        PlayerSelected();
    }

    public void doll() throws Exception {
        PlayerSelected();
        MVC.setPlayerAcordingToUserChoise("doll");
    }
}

