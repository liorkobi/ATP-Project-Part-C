package View;

import ViewModel.MyViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WinController {
    Stage stage;
    Scene scene;
    MyViewModel MYVM;
    newFile generate;
    MyViewController MVC;
//    newFile newFile;


    @FXML
    public void PlayAgain() throws Exception {
//        stage.close();
        //stage.setScene(scene);
        newFile.setMV(MYVM);
        generate = new newFile();
//        MVC.setNF(generate);
        generate.generate();
        System.out.println("genert");
        stage.close();
//        stage.show();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

//    public void setNF(newFile newfile){
//        this.newFile = newfile;
//    }

    public void setScene(Scene scene){
        this.scene = scene;
    }

    public void setMYVM(MyViewModel myvm) {
        this.MYVM = myvm;
    }

    public void Quit(ActionEvent actionEvent) {
        stage.close();
        MYVM.stopServers();
        System.exit(0);
    }
}
