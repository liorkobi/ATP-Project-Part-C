package View;

import ViewModel.MyViewModel;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;

public interface IView {
     void setScene(Scene scene);
     void setVM(MyViewModel MV);
    void About(ActionEvent event);
    void Exit(ActionEvent event)throws IOException;
    void newfile(ActionEvent event) throws IOException;
    void Help(ActionEvent event);
     void update(Observable o, Object arg);
    void KeyPressed(KeyEvent keyEvent);
    void setStage(Stage primaryStage);
    void mouseClicked(MouseEvent mouseEvent);
    void solveMaze(ActionEvent event);
    void savefile(ActionEvent e) throws IOException;
    void loadfile(ActionEvent E) throws IOException, ClassNotFoundException;
    void Properties(ActionEvent event) throws Exception;
    void setResizeEvent(Scene scene);
    void setPlayerAcordingToUserChoise(String s) throws Exception;
    void stopServers();
    void mouseDragged(MouseEvent mouseEvent);
    void setOnScroll(ScrollEvent scroll);
}
