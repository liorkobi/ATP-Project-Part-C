package View;

import Model.MyModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class Exit  implements Initializable {
    public Scene sc;
    public Stage ST;
    @FXML
    private javafx.scene.control.Button closeButton;

    public void setST(Stage ST) {
        this.ST = ST;
    }

    @FXML
 //   private javafx.scene.control.Button closeButton1;




    public void setScene(Scene sc1) {
        this.sc = sc1;
    }


    @FXML
    private void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Window window =   ((Node)(event.getSource())).getScene().getWindow();
//        if (window instanceof Stage){
//            ((Stage) window).close();
//        }
//        ( (Node) event.getSource()).getScene().getWindow().hide();        // do what you have to do
        //((Stage)sc.getWindow()).close();
        stage.close();
    }

    @FXML
    public void QUIT(ActionEvent event) {
        System.exit(0);

    }

    public void onMouseClickedCancelBtn(MouseEvent e) {
//       // ((Stage)sc.getWindow()).close();
//        final Node source = (Node) e.getSource();
//        final Stage stage = (Stage) source.getScene().getWindow();
//        stage.close();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
