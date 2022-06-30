package Model;

import javafx.scene.input.KeyCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;

public interface IModel {
    int[][] Ganerate(int r, int c);

    int[][] getMazeGrid();

    void ab(Observer o);

    int getSrow();
    int getScol();
    int getErow();
    int getEcol();
    void movePlayerModelLogic(KeyCode direction);
    public int getPlayerRowIdx();

    public void setPlayerRowIdx(int playerRowIdx);

    public int getPlayerColIdx();

    public void setPlayerColIdx(int playerColIdx);

//    public boolean isPlayerAtGoalPosition();
public void solveMaze();

    ArrayList<int[]> getSolution();

    void save(File file);

    void loadGame(File loadfile) throws IOException, ClassNotFoundException;
//    public void loadMaze(File file);
}
