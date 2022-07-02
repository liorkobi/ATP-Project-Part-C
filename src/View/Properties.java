package View;

import Server.Configurations;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import Server.Configurations;


public class Properties implements Initializable {
    Stage stage;
    public ChoiceBox<String> searchingAlgorithm;
    public ChoiceBox<String> generator;


    public void setStage(Stage stage) {
        this.stage=stage;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){
        generator.getItems().addAll("EmptyMazeGenerator","SimpleMazeGenerator","MyMazeGenerator");
        searchingAlgorithm.getItems().addAll("Breadth","DFS", "BestFirstSearch");
        try{
            Configurations properties = Configurations.getInstance();
//            properties.getval(new FileInputStream("resources/config.properties"));

            String a1= properties.getval("mazeSearchingAlgorithm");
            String a2= properties.getval("mazeGeneratingAlgorithm");
            if(a1.equals("BestFirstSearch")){
                searchingAlgorithm.setValue("BestFirstSearch");}
            else if(a1.equals("DepthFirstSearch")){
                searchingAlgorithm.setValue("DepthFirstSearch");}
            else if(a1.equals("BreadthFirstSearch")){
                searchingAlgorithm.setValue("BreadthFirstSearch");}
            if(a2.equals("MyMazeGenerator")){
                generator.setValue("MyMazeGenerator");}
            else if(a2.equals("SimpleMazeGenerator")){
                generator.setValue("SimpleMazeGenerator");}
            else if(a2.equals("EmptyMazeGenerator")){
                generator.setValue("EmptyMazeGenerator");}


        }
        catch (Exception e){}
    }



    public void UpdateClicked(){
        Configurations properties = Configurations.getInstance();
        properties.setProperty("mazeGeneratingAlgorithm",generator.getValue());
        properties.setProperty("mazeSearchingAlgorithm",searchingAlgorithm.getValue());
        System.out.println("updated");
        stage.close();
    }
}
