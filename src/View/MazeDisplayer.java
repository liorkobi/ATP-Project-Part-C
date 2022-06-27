package View;
//
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
//
//public class MazeDisplayer extends Canvas {
//    int[][] maze;
//
//    public void drawm(int[][] m){
//        this.maze=m;
//        draw();
//    }
//    public void draw() {
//        if (maze != null) {
//            double h = getHeight();
//            System.out.println(h);
//            double w = getWidth();
//            System.out.println(w);
//
//            double cellH = 100 / maze.length;
//
//            double cellW = 200 / maze[0].length;
//
//            GraphicsContext graphicsContext = getGraphicsContext2D();
//            graphicsContext.clearRect(0, 0, 100, 200);
//            graphicsContext.setFill(Color.RED);
//
//            graphicsContext.fill();
//            graphicsContext.fillRect(1,1,1,1);
//
//            for (int i = 0; i < maze.length; i++) {
//                for (int j = 0; j < maze[0].length; j++) {
//                    if (maze[i][j] == 1) {
//                        System.out.println("1");
//                        graphicsContext.fillRect(1,1,1,1);
//                        //graphicsContext.fillRect(i*cellH,j*cellW,cellW,cellH);}}
//                    }
//
//
//                }
//            }
//        }
//    }}
public class MazeDisplayer extends Canvas {
    private int[][] maze;


    public void drawm(int[][] maze) {
        this.maze = maze;
        draw();
    }

    private void draw() {
        if(maze != null){
            double canvasHeight = getHeight();
            double canvasWidth = getWidth();
            int rows = maze.length;
            int cols = maze[0].length;

            double cellHeight = canvasHeight / rows;
            double cellWidth = canvasWidth / cols;

            GraphicsContext graphicsContext = getGraphicsContext2D();
            //clear the canvas:
            graphicsContext.clearRect(0, 0, canvasWidth, canvasHeight);
            graphicsContext.setFill(Color.RED);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if(maze[i][j] == 1){
                        //if it is a wall:
                        double x = j * cellWidth;
                        double y = i * cellHeight;
                        graphicsContext.fillRect(x, y, cellWidth, cellHeight);
                    }
                }
            }
        }
    }
}