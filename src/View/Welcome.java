package View;

import ViewModel.MyViewModel;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Welcome {
    MyViewModel MYVM;
   public Scene scene;
   public Stage stage;


    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        MazeDisplayer.audioChooser(3);

    }


    public Stage getPrimaryStage() {
        return stage;
    }

    @FXML
    public void newGameClicked() throws Exception {
        stage.setScene(scene);

    }
}
