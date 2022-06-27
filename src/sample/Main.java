package sample;

import Model.IModel;
import Model.MyModel;
import View.MyViewController;
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
import java.util.Optional;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        //mainstage;
        FXMLLoader newFXML = new FXMLLoader(getClass().getResource("/View/MyView.fxml"));
        Parent root = newFXML.load();
        MyViewController newController = newFXML.getController();


        IModel model = new MyModel();
        MyViewModel MYVM= new MyViewModel(model);
        newController.setVM(MYVM);
        MYVM.ab(newController);

        newController.setStage(primaryStage);
        primaryStage.setTitle("Menu");
        Scene scene = new Scene(root, 700, 700);
        primaryStage.setScene(scene);
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
