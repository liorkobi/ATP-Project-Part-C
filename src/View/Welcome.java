package View;

import ViewModel.MyViewModel;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Welcome {
    MyViewModel MYVM;
   public Scene scene;
   public Stage stage;
   public newFile generate;


    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getPrimaryStage() {
        return stage;
    }
    public void setVM(MyViewModel MV){ MYVM=MV;}
    @FXML
    public void newGameClicked() throws Exception {
        stage.setScene(scene);
        newFile.setMV(MYVM);
        generate = new newFile();
        generate.generate();
//      stage.show();
    }
}
