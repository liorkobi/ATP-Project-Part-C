package View;

import ViewModel.MyViewModel;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MyViewController  implements IView , Observer {
   MyViewModel MYVM;
   Stage stage;
    MazeDisplayer MD=new MazeDisplayer();

    public void setVM(MyViewModel MV){ MYVM=MV;}
    @FXML
    public TextField txtfld_rowsNum;
    @FXML
    public TextField txtfld_colsNum;

        @FXML
        void About(ActionEvent event) {
            try {
                Stage stage = new Stage();
                stage.setTitle("About");
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = fxmlLoader.load(getClass().getResource("About.fxml").openStream());
                Scene scene = new Scene(root, 748, 400);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
                stage.show();
            } catch (Exception e) {
            }
        }

        @FXML
        void Exit(ActionEvent event) {
            System.exit(0);

        }
        @FXML
        void loadfile(ActionEvent event) {
        }

        @FXML
        void newfile(ActionEvent event) throws IOException {
            newFile.setMV(MYVM);
            Stage stage = new Stage();
            stage.setTitle("Properties");
            FXMLLoader newFXML = new FXMLLoader(getClass().getResource("/View/newfile.fxml"));
            Parent root = newFXML.load();
            newFile newController = newFXML.getController();
            newController.setStage(stage);
            Scene scene = new Scene(root, 350, 350);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }

        @FXML
        void properties(ActionEvent event) {

        }

        @FXML
        void savefile(ActionEvent event) {

        }
        @FXML
        void Help(ActionEvent event) {
            try {
                Stage stage = new Stage();
                stage.setTitle("Help");
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = fxmlLoader.load(getClass().getResource("Help.fxml").openStream());
                Scene scene = new Scene(root, 748, 400);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
                stage.show();
            } catch (Exception e) {
            }
    }

    @Override
    public void update(Observable o, Object arg) {
           String a=(String)arg;
        System.out.println("C??????????");
        if (o == MYVM) {
            if (a.equals("generated")) {
//                Display(MYVM.getMaze());
                MD.drawm(MYVM.getMaze());
                   System.out.println("D??????????");


            }
        }
    }

//    public void Display(int [][] maze){
//        System.out.println("D??????????");
//
//        //Scene sc=new Scene(stage.getScene().getRoot(),700,700);
//       // this.stage.setScene(MD);
//
//
//    }

    public void setStage(Stage primaryStage) {
            stage=primaryStage;
    }

    public Stage getStage() {
        return stage;
    }

    //    public void Generate(ActionEvent event) {
//        newFile n= new newFile(MYVM);
//        n.generate();
//    }
}



