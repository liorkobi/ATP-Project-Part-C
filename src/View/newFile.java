package View;

import Model.IModel;
import Model.MyModel;
import ViewModel.MyViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

//import java.awt.*;
import java.net.URL;
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
        int rows = Integer.valueOf(tfRow.getText());
        int columns = Integer.valueOf(tfCol.getText());
        if (MYVM.validateMazeGenerationParams(rows, columns)) {
//                    btn_generateMaze.setDisable(false);
                    MYVM.newGame(rows, columns);
                    st.close();
                    //mazeDisplayer.audioChooser(1);
                } else {
            System.out.println("you suck!");
                    //showAlert("Aw, man! are you trying to create a negative size maze? we're putting you on Megaseeds... ");
                }
//            } catch (NumberFormatException e) {
//                showAlert("Aw, man! are you trying to create a negative size maze? we're putting you on Megaseeds... ");
//            }
    }

    public void setStage(Stage stage) {
        this.st=stage;
    }

    public static void setMV(MyViewModel myvm) {
        MYVM=myvm;
    }


//

        }

