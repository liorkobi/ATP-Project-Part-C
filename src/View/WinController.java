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
    int r;
    int c;


    public void setd(int a,int b){
        r=a;c=b;
    }


    @FXML
    public void PlayAgain() throws Exception {
//        stage.close();
        //stage.setScene(scene);
        newFile.setMV(MYVM);
        generate = new newFile();
//        MVC.setNF(generate);
        MazeDisplayer.win=false;
        generate.generateForUpdate(r,c);
//        generate.generate();
        System.out.println("genert");
        stage.close();
//        stage.show();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
        MazeDisplayer.audioChooser(3);

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
