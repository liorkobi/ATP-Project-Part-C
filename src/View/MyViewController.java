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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
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
     newFile newfile;
    public boolean empty;
    public static boolean mute=false;

    @FXML
    public Pane pane;

    public StringProperty playerPosRow = new SimpleStringProperty();
    public StringProperty playerPosCol = new SimpleStringProperty();
    @FXML
    public javafx.scene.control.Label lbl_playerRow;
    @FXML
    public javafx.scene.control.Label lbl_playerColumn;


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
            stopServers();
            System.exit(0);

        }



        @FXML
        void newfile(ActionEvent event) throws IOException {
//            MazeDisplayer.mediaPlayer.stop();
//            newFile.setMV(MYVM);
            Stage stage = new Stage();
            stage.setTitle("NewFile");
            FXMLLoader newFXML = new FXMLLoader(getClass().getResource("/View/newfile.fxml"));
            Parent root = newFXML.load();
            newFile newController = newFXML.getController();
            newfile=newController;
            newController.setStage(stage);
            Scene scene = new Scene(root, 350, 350);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
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
                MazeDisplayer.setMYVM(MYVM);
                MazeDisplayer.setCol_player(MYVM.getScol());
                MazeDisplayer.setRow_player(MYVM.getSrow());
                MazeDisplayer.placeEr(MYVM.getErow());
                MazeDisplayer.placeEc(MYVM.getEcol());
                MazeDisplayer.drawm(MYVM.getMaze());
                bindProperties(MYVM);
                this.playerPosRow.set(MYVM.getPlayerPosRowIdx() + "");
                this.playerPosCol.set(MYVM.getPlayerPosColIdx() + "");

            }if(arg.equals("playerMove")){
                MazeDisplayer.setCol_player(MYVM.getPlayerPosColIdx());
                MazeDisplayer.setRow_player(MYVM.getPlayerPosRowIdx());
//                System.out.println(">>>");
                MazeDisplayer.draw();
                bindProperties(MYVM);

            }
            if ((arg.equals("solved"))) {
                MazeDisplayer.setSolutionObj(MYVM.getSolution());
                MazeDisplayer.drawSolution();
                bindProperties(MYVM);

            } if ((arg.equals("loaded"))){
                MazeDisplayer.placeEr(MYVM.getErow());
                MazeDisplayer.placeEc(MYVM.getEcol());
                MazeDisplayer.setCol_player(MYVM.getScol());
                MazeDisplayer.setRow_player(MYVM.getSrow());
                MazeDisplayer.drawm(MYVM.getMaze());
                //bindProperties(viewModel);
            }
            if (a.equals("empty")) {
                MazeDisplayer.setCol_player(MYVM.getScolE());
                MazeDisplayer.setRow_player(MYVM.getSrowE());
                MazeDisplayer.placeEr(MYVM.getErowE());
                MazeDisplayer.placeEc(MYVM.getEcolE());
                while (MazeDisplayer.getCol_player()==MazeDisplayer.getCol_exit() && MazeDisplayer.getRow_player()==MazeDisplayer.getRow_exit()){
                    MazeDisplayer.placeEr(MYVM.getErowE());
                }
                MazeDisplayer.drawm(MYVM.getMazeE());
                bindProperties(MYVM);
                this.playerPosRow.set(MYVM.getPlayerPosRowIdx() + "");
                this.playerPosCol.set(MYVM.getPlayerPosColIdx() + "");

            }

        }
    }

public void KeyPressed(KeyEvent keyEvent) {
//    MazeDisplayer.requestFocus();
    System.out.println("keypressd");
    MYVM.movePlayer(keyEvent.getCode());
    keyEvent.consume();
}

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

    @FXML
    public void Properties(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        stage.setTitle("Properties");
        FXMLLoader propFXML = new FXMLLoader(getClass().getResource("/View/Properties.fxml"));
        Parent root = propFXML.load();
        Properties propController = propFXML.getController();
        propController.setStage(stage);
        propController.setNF(newfile);
        Scene scene = new Scene(root, 500, 450);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }


    public void setResizeEvent(Scene scene) {
        MazeDisplayer.widthProperty().bind(pane.widthProperty());
        MazeDisplayer.heightProperty().bind(pane.heightProperty());
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            MazeDisplayer.widthProperty().bind(pane.widthProperty());
        });
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            MazeDisplayer.heightProperty().bind(pane.heightProperty());
        });
    }


    public void setPlayerAcordingToUserChoise(String s) throws Exception {
        MazeDisplayer.getUserChoiceOfPlayer(s);

    }
    public void updateProp() throws Exception {
//        MazeDisplayer.setCol_player(MYVM.getScol());
//        MazeDisplayer.setRow_player(MYVM.getSrow());
//        MazeDisplayer.placeEr(MYVM.getErow());
//        MazeDisplayer.placeEc(MYVM.getEcol());
//        MazeDisplayer.drawm(MYVM.getMaze());
//        bindProperties(MYVM);
//        this.playerPosRow.set(MYVM.getPlayerPosRowIdx() + "");
//        this.playerPosCol.set(MYVM.getPlayerPosColIdx() + "");
    }
    public void stopServers(){
            MYVM.stopServers();
    }

    public void mouseDragged(MouseEvent mouseEvent) {
        if(MYVM.getMaze() != null) {
            //save the bigger num- rows or columns??
            int maximumSize = Math.max(MYVM.getMaze()[0].length, MYVM.getMaze().length);
            //calculate x parameter of mouse - left and write
            double mousePosX=helperMouseDragged(maximumSize,MazeDisplayer.getHeight(),
                    MYVM.getMaze().length,mouseEvent.getX(),MazeDisplayer.getWidth() / maximumSize);
            //calculate y parameter of mouse - up and down
            double mousePosY=helperMouseDragged(maximumSize,MazeDisplayer.getWidth(),
                    MYVM.getMaze()[0].length,mouseEvent.getY(),MazeDisplayer.getHeight() / maximumSize);
            //אם ערך ה-X של העכבר==העמודה של השחקן וה-Y < השורה של השחקן תעלה למעלה
            if ( mousePosX == MYVM.getPlayerPosColIdx() && mousePosY < MYVM.getPlayerPosRowIdx() )
                MYVM.movePlayer(KeyCode.NUMPAD8);
            //אם ערך ה-Y של העכבר==השורה של השחקן וגם ערך ה-X >מהעמודה של השחקן תזוז ימינה
            else if (mousePosY == MYVM.getPlayerPosRowIdx() && mousePosX > MYVM.getPlayerPosColIdx() )
                MYVM.movePlayer(KeyCode.NUMPAD6);
                //אם ערך ה-Y של העכבר==השורה של השחקן וגם ערך ה-X <מהעמודה של השחקן תזוז שמאלה
            else if ( mousePosY == MYVM.getPlayerPosRowIdx() && mousePosX < MYVM.getPlayerPosColIdx() )
                MYVM.movePlayer(KeyCode.NUMPAD4);
                //אם ערך ה-X של העכבר==העמודה של השחקן וגם ערך ה-Y > השורה של השחקן תרד למטה
            else if (mousePosX == MYVM.getPlayerPosColIdx() && mousePosY > MYVM.getPlayerPosRowIdx()  )
                MYVM.movePlayer(KeyCode.NUMPAD2);

        }
    }
    private  double helperMouseDragged(int maxsize, double canvasSize, int mazeSize,double mouseEvent,double temp){
        double cellSize=canvasSize/maxsize;
        double start = (canvasSize / 2 - (cellSize * mazeSize / 2)) / cellSize;
        double mouse = (int) ((mouseEvent) / (temp) - start);
        return mouse;
    }


    public void setOnScroll(ScrollEvent scroll) {
        if (scroll.isControlDown()) {
            double zoom_fac = 1.05;
            if (scroll.getDeltaY() < 0) {
                zoom_fac = 2.0 - zoom_fac;
            }
            Scale newScale = new Scale();
            newScale.setPivotX(scroll.getX());
            newScale.setPivotY(scroll.getY());
            newScale.setX(MazeDisplayer.getScaleX() * zoom_fac);
            newScale.setY(MazeDisplayer.getScaleY() * zoom_fac);
            MazeDisplayer.getTransforms().add(newScale);
            scroll.consume();
        }
    }

    private void bindProperties(MyViewModel viewModel) {
        lbl_playerRow.textProperty().bind(viewModel.spPlayerPosRow);
        lbl_playerColumn.textProperty().bind(viewModel.spPlayerPosCol);
    }
    public String getPlayerPosRow() {
        return playerPosRow.get();
    }
    public String getPlayerPosCol() {
        return playerPosCol.get();
    }

    public void setNF(newFile generate) {
            this.newfile=generate;
    }

    public  void mute(){
        if(mute){
            mute = false;
        }
        else{ mute = true;
        }
        View.MazeDisplayer.stopMusic();
    }

}




