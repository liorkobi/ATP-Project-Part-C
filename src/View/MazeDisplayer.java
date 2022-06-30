package View;
//
import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.MazeState;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MazeDisplayer extends Canvas {
    private int row_player =0;
    private int col_player =0;
    private int[][] maze;

    StringProperty imageFileNameWall = new SimpleStringProperty();
    StringProperty imageFileNamePlayer = new SimpleStringProperty();
    StringProperty imageFileNameExit = new SimpleStringProperty();
    StringProperty imageFileNameWIN = new SimpleStringProperty();
    StringProperty imageFileNameSolution = new SimpleStringProperty();

    private int row_exit;
    private int col_exit;
    ArrayList<int[]> Sol;

    public String getImageFileNameWIN() {
        return imageFileNameWIN.get();
    }

    public void setImageFileNameWIN(String imageFileNameWIN) {
        this.imageFileNameWIN.set(imageFileNameWIN);
    }


    public String getImageFileNameWall() {
        return imageFileNameWall.get();
    }
    public String getImageFileNameSolution() {
        return imageFileNameSolution.get();
    }

    public void setImageFileNameWall(String imageFileNameWall) {
        this.imageFileNameWall.set(imageFileNameWall);
    }
    public void setImageFileNameSolution(String imageFileNameSolution) {
        this.imageFileNameSolution.set(imageFileNameSolution);
    }

    public String getImageFileNamePlayer() {
        return imageFileNamePlayer.get();
    }

    public void setImageFileNamePlayer(String imageFileNameExit) {
        this.imageFileNamePlayer.set(imageFileNameExit);
    }
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
        Image playerImage = null;
        try {
            playerImage = new Image(new FileInputStream(getImageFileNamePlayer()));
        } catch (FileNotFoundException e) {
            System.out.println("There is no Image player....");
        }
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

    }
}

    private double getCol_player() {
        return col_player;
    }

    private double getRow_player() {
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
        try {
            Pane pane = new Pane();
            Stage newStage = new Stage();
            newStage.setTitle("winners");
            Image WIN=null;
            try {
                WIN = new Image(new FileInputStream(getImageFileNameWIN()));
            } catch (FileNotFoundException e) {
                System.out.println("There is no Image WIN....");
            }
            ImageView imageviewUserWonScene = new ImageView(WIN);
            /* add ImageView to Pane's children */
            pane.getChildren().add(imageviewUserWonScene);
            Scene scene = new Scene(pane);
            newStage.setScene(scene);
            /* show the UserWon scene */
            newStage.show();
            /* play the UserWon Audio */
//            audioChooser(2);
            /* if user presses the exit window button then stop the music from playing*/
//            newStage.setOnCloseRequest( event ->  mediaPlayer.stop() );//Sets the value of the property onCloseRequest
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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
            Image playerImage = null;
            try {
                playerImage = new Image(new FileInputStream(getImageFileNamePlayer()));
            } catch (FileNotFoundException e) {
                System.out.println("There is no Image player....");
            }
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



}



