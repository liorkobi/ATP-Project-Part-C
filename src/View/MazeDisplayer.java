package View;
//
import ViewModel.MyViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MazeDisplayer extends Canvas {

    private int row_player =0;
    private int col_player =0;
    private int[][] maze;
    public MyViewModel MYVM;
    public static boolean win=false;
    public static MediaPlayer mediaPlayer;

    StringProperty imageFileNameWall = new SimpleStringProperty();
//    StringProperty imageFileNamePlayer = new SimpleStringProperty();
    StringProperty imageFileNameExit = new SimpleStringProperty();
    StringProperty imageFileNameWIN = new SimpleStringProperty();
    StringProperty imageFileNameSolution = new SimpleStringProperty();

    public static void audioChooser(int i) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.pause();
        }
        String path = "";
        if (i == 3) {
            path = "resources/mp3/welcom.mp3";
        }
        else if (i == 1) {
            path = "resources/mp3/maze.mp3";
//            winnerCounter = 0;
        }
        else if (i == 2) {
            path = "resources/mp3/win.mp3";
        }
        //Media player = new Media(Paths.get(path).toUri().toString());
        Media player = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(player);
        mediaPlayer.play();
        mediaPlayer.setMute(MyViewController.mute);
    }


    public String getTsoliderIMG() {
        return TsoliderIMG.get();
    }

    public String getSsoliderIMG() {
        return SsoliderIMG.get();
    }

    public String getCsoliderIMG() {
        return CsoliderIMG.get();
    }


    public String getBlackIMG() {
        return blackIMG.get();
    }



    public String getDollIMG() {
        return dollIMG.get();
    }

    public void setTsoliderIMG(String tsoliderIMG) {
        this.TsoliderIMG.set(tsoliderIMG);
    }

    public void setSsoliderIMG(String ssoliderIMG) {
        this.SsoliderIMG.set(ssoliderIMG);
    }

    public void setCsoliderIMG(String csoliderIMG) {
        this.CsoliderIMG.set(csoliderIMG);
    }

    public void setBlackIMG(String blackIMG) {
        this.blackIMG.set(blackIMG);
    }

    public void setDollIMG(String dollIMG) {
        this.dollIMG.set(dollIMG);
    }

    StringProperty TsoliderIMG = new SimpleStringProperty();
    StringProperty SsoliderIMG = new SimpleStringProperty();
    StringProperty CsoliderIMG = new SimpleStringProperty();
    StringProperty blackIMG = new SimpleStringProperty();
    StringProperty dollIMG = new SimpleStringProperty();
    Image playerImage;

    private int row_exit;
    private int col_exit;
    ArrayList<int[]> Sol;

    public MazeDisplayer() {
    widthProperty().addListener(e -> draw());
    heightProperty().addListener(e -> draw());
    }

    //WIN IMAGE
    public String getImageFileNameWIN() {
        return imageFileNameWIN.get();
    }
    public void setImageFileNameWIN(String imageFileNameWIN) { this.imageFileNameWIN.set(imageFileNameWIN);}

    //WALL IMAGE
    public String getImageFileNameWall() { return imageFileNameWall.get(); }
    public void setImageFileNameWall(String imageFileNameWall) {
        this.imageFileNameWall.set(imageFileNameWall);
    }


    //SOLUTION IMAGE
    public String getImageFileNameSolution() { return imageFileNameSolution.get(); }
    public void setImageFileNameSolution(String imageFileNameSolution) { this.imageFileNameSolution.set(imageFileNameSolution); }

//    public String getImageFileNamePlayer() {
//        return imageFileNamePlayer.get();
//    }
//
//    public void setImageFileNamePlayer(String imageFileNameExit) {
//        this.imageFileNamePlayer.set(imageFileNameExit);
//    }
    public String getImageFileNameExit() {
        return imageFileNameExit.get();
    }

    public void setImageFileNameExit(String imageFileNameExit) {
        this.imageFileNameExit.set(imageFileNameExit);
    }


    public void drawm(int[][] maze) {
        this.maze = maze;
        draw();
    }

public void draw()
{
    if( maze!=null)
    {
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();
        int row = maze.length;
        int col = maze[0].length;
        double cellHeight = canvasHeight/row;
        double cellWidth = canvasWidth/col;
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.clearRect(0,0,canvasWidth,canvasHeight);
     //   graphicsContext.setFill(Color.RED);
        double w,h;
        //Draw Maze
        Image wallImage = null;
        try {
            wallImage = new Image(new FileInputStream(getImageFileNameWall()));
        } catch (FileNotFoundException e) {
            System.out.println("There is no file....");
        }
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                if(maze[i][j] == 1) // Wall
                {
                    h = i * cellHeight;
                    w = j * cellWidth;
                    if (wallImage == null){
                        graphicsContext.fillRect(w,h,cellWidth,cellHeight);
                    }else{
                        graphicsContext.drawImage(wallImage,w,h,cellWidth,cellHeight);
                    }
                }

            }
        }
//place the player
        double h_player = getRow_player() * cellHeight;
        double w_player = getCol_player() * cellWidth;

        graphicsContext.drawImage(playerImage,w_player,h_player,cellWidth,cellHeight);
        if (getRow_player() == getRow_exit() && getCol_player() == getCol_exit() && win==false) {
            win=true;
            showStageForUserWinningTheGame("You Are The Winner");

        }
 //place the exit p

        double h_exit = getRow_exit() * cellHeight;
        double w_exit = getCol_exit() * cellWidth;
        Image exitImage = null;
        try {
            exitImage = new Image(new FileInputStream(getImageFileNameExit()));
        } catch (FileNotFoundException e) {
            System.out.println("There is no Image player....");
        }
        graphicsContext.drawImage(exitImage,w_exit,h_exit,cellWidth,cellHeight);

    }
}

    double getCol_player() {
        return col_player;
    }

    double getRow_player() {
        return row_player;
    }

    public void setRow_player(int row_player) {
        this.row_player = row_player;
    }

    public void setCol_player(int col_player) {
        this.col_player = col_player;
    }

    public void placeEr(int row_exit){
        this.row_exit = row_exit;

    }
    public void placeEc(int col_exit){
        this.col_exit = col_exit;

    }

    public int getRow_exit() {
        return row_exit;
    }

    public int getCol_exit() {
        return col_exit;
    }

    private void showStageForUserWinningTheGame(String alertMessage) {
        try{
        Stage stage = new Stage();
        stage.setTitle("Winner");
        FXMLLoader winFXML = new FXMLLoader(getClass().getResource("/View/Win.fxml"));
        Parent root = winFXML.load();
        WinController winController = winFXML.getController();

        winController.setd(maze.length,maze[0].length);

        winController.setMYVM(MYVM);

        winController.setStage(stage);
        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        audioChooser(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
////
//        try {
//            Pane pane = new Pane();
//            Stage newStage = new Stage();
//            newStage.setTitle("winners");
//            Image WIN = null;
//            try {
//                WIN = new Image(new FileInputStream(getImageFileNameWIN()));
//            } catch (FileNotFoundException e) {
//                System.out.println("There is no Image WIN....");
//            }
//            ImageView imageviewUserWonScene = new ImageView(WIN);
//            /* add ImageView to Pane's children */
//            pane.getChildren().add(imageviewUserWonScene);
//            Scene scene = new Scene(pane);
//            newStage.setScene(scene);
//            /* show the UserWon scene */
//            newStage.show();
//            /* play the UserWon Audio */
////            audioChooser(2);
//            /* if user presses the exit window button then stop the music from playing*/
////            newStage.setOnCloseRequest( event ->  mediaPlayer.stop() );//Sets the value of the property onCloseRequest
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }



    public void setSolutionObj(ArrayList<int[]> solution) {
        Sol = solution;

    }

    public void drawSolution() {
        try {
            /*get Maze Canvas dimensions */
            double width = getWidth();
            double height = getHeight();
            /*get single cell dimesions */
            double cellWidth = width / maze[0].length;
            double cellHeight = height / maze.length;
            /* create Image instance of the Solution-step Image */
            Image solutionPathImage = null;
            try {
                solutionPathImage = new Image(new FileInputStream(getImageFileNameSolution()));
            } catch (FileNotFoundException e) {
                System.out.println("There is no Image WIN....");
            }
            /* create Image instance of the Wall-Brick Image */
            Image wallImage = null;
            try {
                wallImage = new Image(new FileInputStream(getImageFileNameWall()));
            } catch (FileNotFoundException e) {
                System.out.println("There is no file....");
            }
            GraphicsContext graphicsContext = getGraphicsContext2D();
            /* reset the Maze canvas */
            graphicsContext.clearRect(0, 0, getWidth(), getHeight());
            /*Draw walls and goal point*/
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[i].length; j++) {
                    if (maze[i][j] == 1) {
                        graphicsContext.drawImage(wallImage, j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                    }
                    /*if this cell is part of the path draw the solution path image */
                    int[] temp = new int[2];
                    temp[0]=i;
                    temp[1]=j;
                    for (int k=0;k<Sol.size();k++){
                    if(Sol.get(k)[0]==i &&Sol.get(k)[1]==j){
                        graphicsContext.drawImage(solutionPathImage, j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                    }}



                    }
                }
            //place the player
            double h_player = getRow_player() * cellHeight;
            double w_player = getCol_player() * cellWidth;
//            Image playerImage = null;
//            try {
//                playerImage = new Image(new FileInputStream(getImageFileNamePlayer()));
//            } catch (FileNotFoundException e) {
//                System.out.println("There is no Image player....");
//            }
            graphicsContext.drawImage(playerImage,w_player,h_player,cellWidth,cellHeight);
            if (getRow_player() == getRow_exit() && getCol_player() == getCol_exit()) {
                showStageForUserWinningTheGame("You Are The Winner");
            }
            //place the exit p

            double h_exit = getRow_exit() * cellHeight;
            double w_exit = getCol_exit() * cellWidth;
            Image exitImage = null;
            try {
                exitImage = new Image(new FileInputStream(getImageFileNameExit()));
            } catch (FileNotFoundException e) {
                System.out.println("There is no Image player....");
            }
            graphicsContext.drawImage(exitImage,w_exit,h_exit,cellWidth,cellHeight);

        } catch (Exception e) {
            e.printStackTrace();
        }
        /*reset the mazeSolution */
        Sol = null;
    }

//    public ResizableCanvas() {
//        // Redraw canvas when size changes.
//        widthProperty().addListener(evt -> draw());
//        heightProperty().addListener(evt -> draw());
//    }
    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }

    public void getUserChoiceOfPlayer(String s) throws Exception {
//        playerImage=null;
        //brown background
            if (s.equals("Tsolider"))
                playerImage = new Image(new FileInputStream(TsoliderIMG.get()));
            if (s.equals("Ssolider"))
                playerImage = new Image(new FileInputStream(SsoliderIMG.get()));
            if (s.equals("Csolider"))
                playerImage = new Image(new FileInputStream(CsoliderIMG.get()));
            if (s.equals("black"))
                playerImage = new Image(new FileInputStream(blackIMG.get()));
            if (s.equals("doll"))
                playerImage = new Image(new FileInputStream(dollIMG.get()));
        }

    public void setMYVM(MyViewModel myvm) {
        this.MYVM = myvm;
    }
    public static void stopMusic(){
        mediaPlayer.setMute(MyViewController.mute);
    }

}




