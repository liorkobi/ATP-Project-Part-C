package Model;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.util.Observable;
import java.util.Observer;

public class MyModel extends Observable implements IModel {
     AMazeGenerator generator=new MyMazeGenerator();
     Maze maze;

     @Override
     public int[][] Ganerate(int r, int c) {
          Maze maze = generator.generate(r, c);
          this.maze=maze;
          //maze.print();
          setChanged();//-->hasChanged() will return True
          /* Notify ViewModel */
          notifyObservers("generated");
//          System.out.println("MM1??????????");
          return maze.getMaze();
     }
    public int[][] getMazeGrid(){return maze.getMaze();}


     public void ab(Observer o){
          this.addObserver(o);
     }


}
