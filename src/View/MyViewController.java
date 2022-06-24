package View;

import ViewModel.MyViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MyViewController implements IView {
private MyViewModel MYVM;
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

        }

        @FXML
        void loadfile(ActionEvent event) {

        }

        @FXML
        void newfile(ActionEvent event) {
       //     mazeDisplayer.mediaPlayer.stop();
//            try {
                int rows = Integer.valueOf(txtfld_rowsNum.getText());
                int columns = Integer.valueOf(txtfld_colsNum.getText());
//                if (MYVM.validateMazeGenerationParams(rows, columns)) {
//                    btn_generateMaze.setDisable(false);
//                    viewModel.newGame(rows, columns);
//                    mazeDisplayer.audioChooser(1);
//                } else {
//                    showAlert("Aw, man! are you trying to create a negative size maze? we're putting you on Megaseeds... ");
//                }
//            } catch (NumberFormatException e) {
//                showAlert("Aw, man! are you trying to create a negative size maze? we're putting you on Megaseeds... ");
//            }
        }

//        }

        @FXML
        void properties(ActionEvent event) {

        }

        @FXML
        void savefile(ActionEvent event) {

        }
        @FXML
        void Help(ActionEvent event) {

    }

    }



