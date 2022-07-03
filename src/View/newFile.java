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

import static java.lang.String.valueOf;


public class newFile implements Initializable {
    public Stage st;
    public TextField tfRow;
    public TextField tfCol;
    public static  MyViewModel MYVM;
    public MazeDisplayer md;
    public static boolean empty=false;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void generateForUpdate(){
        if (tfRow == null || tfCol == null) {
            MYVM.empty(10,10);
        }
        else {
            if (valueOf(tfRow.getText()) == "" || valueOf(tfCol.getText()) == "") {
                MYVM.empty(10, 10);
                showAlert("never heard of maze without dimensions,you survived this time");
            } else {
                MYVM.empty(Integer.parseInt(tfRow.getText()), Integer.parseInt(tfCol.getText()));
            }
        }
    }

    @FXML
    public void generate() {
        if (empty) {
            try {
                if (tfRow == null || tfCol == null) {
                    MYVM.empty(10, 10);
                    st.close();
                } else {
                    if (valueOf(tfRow.getText()) == "" || valueOf(tfCol.getText()) == "") {
                        MYVM.empty(10, 10);
                        showAlert("never heard of maze without dimensions,you survived this time");
                    } else {
                        MYVM.empty(Integer.parseInt(tfRow.getText()), Integer.parseInt(tfCol.getText()));
                    }
                }
                st.close();
            } catch (Exception e) {
                showAlert("you are trying to enter letters as maze dimensions");
            }

            }else{
            try{
                if (tfRow == null || tfCol == null) {
                    System.out.println("default");
                    MYVM.newGame(10, 10);
//                st.close();
                } else {
                    if (valueOf(tfRow.getText()) == "" || valueOf(tfCol.getText()) == "") {
                        MYVM.newGame(10, 10);
                        showAlert("never heard of maze without dimensions,you survived this time");
                        st.close();
                    } else {
                        int rows = Integer.parseInt(tfRow.getText());
                        int columns = Integer.parseInt(tfCol.getText());
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
            }catch (Exception e) {
                showAlert("you are trying to enter letters as maze dimensions");
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

