package ViewModel;


import Model.IModel;
import Model.MyModel;
import View.IView;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

public class MyViewModel extends Observable implements Observer{
    IView V;
    IModel M;

    public void ab(Observer o){
        this.addObserver(o);
    }

    public MyViewModel(IModel m) {
        M = m;
        M.ab(this);
    }
    public int[][] getMaze() {
        System.out.println(Arrays.deepToString(M.getMazeGrid()));
        return M.getMazeGrid();
    }



    public boolean validateMazeGenerationParams(int rows, int columns) {
        return true;
    }

    public void newGame(int rows, int columns) {
       int[][] a=M.Ganerate(rows,columns);
    }

    @Override
    public void update(Observable o, Object arg) {
        String a=(String)arg;
        System.out.println("M??????????");
        if(o == M){
            if(a.equals("generated")){
                //UPDATE VIEW
                setChanged();
                notifyObservers("generated");

            }
        }

    }


}