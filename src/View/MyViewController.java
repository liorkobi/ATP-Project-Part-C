package View;

import ViewModel.MyViewModel;
import algorithms.search.Solution;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MyViewController  implements IView , Observer {
   MyViewModel MYVM;
    Scene scene;
    Stage stage;
    public StringProperty playerPosRow = new SimpleStringProperty();
    public StringProperty playerPosCol = new SimpleStringProperty();


    public void setScene(Scene scene) {
        this.scene = scene;
    }
   @FXML
    MazeDisplayer MazeDisplayer;
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
        void Exit(ActionEvent event) throws IOException {
//            System.exit(0);
                Stage stage = new Stage();
                stage.setTitle("Exit");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Exit.fxml"));
                Parent root = fxmlLoader.load();
                Exit ctrl=  fxmlLoader.getController();
                ctrl.setST(stage);
                Scene scene = new Scene(root, 350, 448);
                stage.setScene(scene);
//                ctrl.setScene(scene);

                stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
                stage.show();
        }



        @FXML
        void newfile(ActionEvent event) throws IOException {
//            newFile.setMV(MYVM);
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
        if (o == MYVM) {
            if (a.equals("generated")) {
                MazeDisplayer.setCol_player(MYVM.getScol());
                MazeDisplayer.setRow_player(MYVM.getSrow());
                MazeDisplayer.placeEr(MYVM.getErow());
                MazeDisplayer.placeEc(MYVM.getEcol());

                MazeDisplayer.drawm(MYVM.getMaze());
            }if(arg.equals("playerMove")){
                MazeDisplayer.setCol_player(MYVM.getPlayerPosColIdx());
                MazeDisplayer.setRow_player(MYVM.getPlayerPosRowIdx());
//                System.out.println(">>>");
                MazeDisplayer.draw();
            }
            if ((arg.equals("solved"))) {
                MazeDisplayer.setSolutionObj(MYVM.getSolution());
                MazeDisplayer.drawSolution();
            } if ((arg.equals("loaded"))){
                MazeDisplayer.placeEr(MYVM.getErow());
                MazeDisplayer.placeEc(MYVM.getEcol());
                MazeDisplayer.setCol_player(MYVM.getScol());
                MazeDisplayer.setRow_player(MYVM.getSrow());
                MazeDisplayer.drawm(MYVM.getMaze());
                //bindProperties(viewModel);
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
public void KeyPressed(KeyEvent keyEvent) {
//    MazeDisplayer.requestFocus();
    System.out.println("keypressd");
    MYVM.movePlayer(keyEvent.getCode());
    keyEvent.consume();
}

//    @Override
//    public void Load() throws IOException, ClassNotFoundException {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
//        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Maze Files", "*.maze"));
//        File loadFile = fileChooser.showOpenDialog(stage);
//        if (loadFile != null) {
//            MYVM.loadGame(loadFile);
////            MazeDisplayer.audioChooser(1);
//        } else {
//        }
//    }

    public void setStage(Stage primaryStage) {
            stage=primaryStage;
    }


    public void mouseClicked(MouseEvent mouseEvent) {
        MazeDisplayer.requestFocus();
    }

    public void solveMaze(ActionEvent event) {
            MYVM.solve();
    }

    @FXML
public void savefile(ActionEvent e) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Maze Files", "*.maze")
        );
        fileChooser.setInitialFileName("My Maze To Save");
        File saveFile = fileChooser.showSaveDialog(MazeDisplayer.getScene().getWindow());
        if (saveFile != null) {
            MYVM.saveM(saveFile);
        }
    }

    @FXML
    public void loadfile(ActionEvent E) throws IOException, ClassNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Maze Files", "*.maze"));
        File loadFile = fileChooser.showOpenDialog(stage);
        if (loadFile != null) {
            MYVM.loadGame(loadFile);
        }
//            mazeDisplayer.audioChooser(1);
//        } else {
//        }
    }



}




