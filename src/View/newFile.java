package View;

import Model.IModel;
import Model.MyModel;
import ViewModel.MyViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;


public class newFile implements Initializable {
    public Stage st;
    public TextField tfRow;
    public TextField tfCol;
    public static  MyViewModel MYVM;
    public MazeDisplayer md;
    public static boolean empty=false;

    public static void SETempty(boolean b) {
        empty=b;
        MyViewModel.empty=b;
        MYVM.emptyM=null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void generateForUpdate(int a,int b){
        MazeDisplayer.audioChooser(1);
        if (empty){ MYVM.empty(a,b);}
        else {MYVM.newGame(a,b);}
    }

    @FXML
    public void generate() {

        MazeDisplayer.audioChooser(1);
        MazeDisplayer.win=false;
        if (empty) {
            if (tfRow == null || tfCol == null) {
                MYVM.empty(10, 10);
                if(st!=null)st.close();
            } else {
                try {
                    if (valueOf(tfRow.getText()) == "" || valueOf(tfCol.getText()) == "") {
                        MYVM.empty(10, 10);
                        showAlert("never heard of maze without dimensions,you survived this time");
                    } else {
                        MYVM.empty(Integer.parseInt(tfRow.getText()), Integer.parseInt(tfCol.getText()));
                    }
                    if(st!=null)st.close();
                } catch (Exception e) {
                    showAlert("you are trying to enter not numbers value as maze dimensions");
                }
            }
        } else {
            if (tfRow == null || tfCol == null) {
                System.out.println("default");
                MYVM.newGame(10, 10);

//                st.close();
            } else {
                try {
                    if (valueOf(tfRow.getText()) == "" || valueOf(tfCol.getText()) == "") {
                        MYVM.newGame(10, 10);
                        showAlert("never heard of maze without dimensions,you survived this time");
                        if(st!=null)st.close();
                    } else {
                        int rows = Integer.parseInt(tfRow.getText());
                        int columns = Integer.parseInt(tfCol.getText());
                        if (MYVM.validateMazeGenerationParams(rows, columns)) {
//                    btn_generateMaze.setDisable(false);
                            MYVM.newGame(rows, columns);
                            if(st!=null)st.close();
                            MazeDisplayer.audioChooser(1);
                        } else {
                            MYVM.newGame(10, 10);
                            showAlert("Maze is too small you survived this time.confirm and BEWARE");
                            if(st!=null)st.close();
                            MazeDisplayer.audioChooser(1);

                        }

                    }

                } catch (Exception e) {
                    showAlert("you are trying to enter not numbers value as maze dimensions");
                }
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

