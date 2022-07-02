package View;

import Model.IModel;
import Model.MyModel;
import ViewModel.MyViewModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

//import java.awt.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class newFile implements Initializable {
    public Stage st;
    public TextField tfRow;
    public TextField tfCol;
    public static  MyViewModel MYVM;
    public MazeDisplayer md;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        IModel model = new MyModel();
//        MYVM= new MyViewModel(model);
    }
    @FXML
    public void generate() {
        if (tfRow == null || tfCol == null) {
            System.out.println("default");

            MYVM.newGame(10, 10);
//            st.close();
        } else {
            int rows = Integer.valueOf(tfRow.getText());
            int columns = Integer.valueOf(tfCol.getText());
            if (MYVM.validateMazeGenerationParams(rows, columns)) {
//                    btn_generateMaze.setDisable(false);
                MYVM.newGame(rows, columns);
                st.close();
                //mazeDisplayer.audioChooser(1);
            } else {
                MYVM.newGame(10, 10);
                showAlert("Maze is too small you survived this time.confirm and BEWARE");
                st.close();
            }
            }
        }


    public void setStage(Stage stage) {
        this.st=stage;
    }

    public static void setMV(MyViewModel myvm) {
        MYVM=myvm;
    }


    private void showAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(alertMessage);
        alert.show();
    }


//

        }

