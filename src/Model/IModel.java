package Model;

import java.util.Observer;

public interface IModel {
    int[][] Ganerate(int r, int c);

    int[][] getMazeGrid();

    public void ab(Observer o);


}
