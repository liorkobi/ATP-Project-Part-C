package ViewModel;


import Model.IModel;
import Model.MyModel;
import View.IView;
import algorithms.search.Solution;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

public class MyViewModel extends Observable implements Observer{
    IView V;
    IModel M;
    int playerPosRowIdx ;
    int playerPosColIdx;
    public StringProperty spPlayerPosRow = new SimpleStringProperty();
    public StringProperty spPlayerPosCol = new SimpleStringProperty();

    public void ab(Observer o){
        this.addObserver(o);
    }

    public MyViewModel(IModel m) {
        M = m;
        M.ab(this);
    }
    public int[][] getMaze() {
        return M.getMazeGrid();
    }



    public boolean validateMazeGenerationParams(int rows, int columns) {
        //what about letters??!?!?!

        if(rows<=2 || columns <= 2){
            return false;
        }
        return true;
    }

    public void newGame(int rows, int columns) {
       int[][] a=M.Ganerate(rows,columns);
    }

    @Override
    public void update(Observable o, Object arg) {
        String a = (String) arg;
        if (o == M) {
            if (a.equals("generated")) {
                //UPDATE VIEW
                setChanged();
                notifyObservers("generated");

            }
            if (arg.equals("playerMove")) {
                updateRowsAndCols();
                setChanged();
                notifyObservers("playerMove");//call view.update()
            }
            if ((arg.equals("solved"))) {
                setChanged();
                notifyObservers("solved");//call view.update()
            }
            if ((arg.equals("loaded"))) {
                setChanged();
                notifyObservers("loaded");
            }

        }
    }
    public int getSrow() {
        return M.getSrow();
    }
    public int getErow() {
        return M.getErow();
    }
    public int getEcol() {
        return M.getEcol();
    }
    public int getScol() {
        return M.getScol();
    }
    public void movePlayer(KeyCode direction){
        System.out.println("movep()myvm");M.movePlayerModelLogic(direction);
    }


    private void updateRowsAndCols(){
         playerPosRowIdx = M.getPlayerRowIdx();
        playerPosColIdx = M.getPlayerColIdx();
        spPlayerPosRow.set(playerPosRowIdx + "");
        spPlayerPosCol.set(playerPosColIdx + "");
    }

    public int getPlayerPosRowIdx() {
        return playerPosRowIdx;
    }

    public int getPlayerPosColIdx() {
        return playerPosColIdx;
    }
    public ArrayList<int[]> getSolution(){ return M.getSolution() ; }

    public void solve() {
        M.solveMaze();
    }


//    public void loadGame(File file) throws IOException, ClassNotFoundException {
//        M.loadMaze(file);
//    }

    public void saveM(File file ){
        M.save(file);
    }

    public void loadGame(File loadFile) throws IOException, ClassNotFoundException {
        M.loadGame(loadFile);
    }
}