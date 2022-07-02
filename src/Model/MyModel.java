package Model;

import Client.Client;
import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import Server.Configurations;
import Server.Server;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.*;
import javafx.scene.input.KeyCode;


import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import Client.IClientStrategy;
import Server.*;

public class MyModel extends Observable implements IModel {

    AMazeGenerator generator = new MyMazeGenerator();
    SearchableMaze Smaze;
    Maze maze;
    Solution solution;

    int playerRowIdx = 1;
    int playerColIdx = 1;

    private Server generatorServer;
    private Server solverServer;
    private boolean serversOn = false;

    public MyModel() {
        generatorServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        solverServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());
        startServers();
    }


    public int getPlayerRowIdx() {
        return playerRowIdx;
    }

    public void setPlayerRowIdx(int playerRowIdx) {
        this.playerRowIdx = playerRowIdx;
    }

    public int getPlayerColIdx() {
        return playerColIdx;
    }

    public void setPlayerColIdx(int playerColIdx) {
        this.playerColIdx = playerColIdx;
    }
    /**
     * calls Server.start() for both servers */
    public void startServers() {
        serversOn = true;
        generatorServer.start();
        solverServer.start();
    }
    /**
     * calls Server.stop() for both servers */
    public void stopServers() {
        if(serversOn){
            generatorServer.stop();
            solverServer.stop();
        }
    }
    /**
     * generates a Maze instance with given dimensions using communication with Server (ServerStrategyGenerateMaze)
     * Updates this.maze with the freshly created Maze (doesn't notify ViewModel)
     * @param numOfRows - int indicating rows dimensions
     * @param numOfCols - int indicating cols dimensions*/
    private void generateMazeThroughGeneratorServer(int numOfRows, int numOfCols) {
        try {
            /* Code from part-B test: "RunCommunicateWithServers" written by Aviad */
            Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        int[] mazeDimensions = new int[]{numOfRows, numOfCols};
                        /* write the desired Maze dimensions to the OutStream */
                        toServer.writeObject(mazeDimensions);
                        toServer.flush();
                        /* get compressed Maze from the InStream */
                        byte[] compressedMaze = (byte[])fromServer.readObject();
                        /*### Decompress the compressed-maze read from server ###*/
                        InputStream decompressorIS = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                        byte[] decompressedMaze = new byte[1000000];//todo consider these dims: byte[numOfRows * numOfCols + 12] - logically no reason it won't be the size
                        /*Fill decompressedMaze with bytes*/
                        decompressorIS.read(decompressedMaze);
                        /*create new Maze */
                        Maze newMaze = new Maze(decompressedMaze);
                        /* update maze data member */
                        maze=newMaze;
                        playerRowIdx =getSrow();
                        playerColIdx =getScol();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });//end of concrete Client ctor
            /* invoking the anonymous "clientStrategy" implemented above */
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void solveMazeThroughSolverServer() {
        try {
            /* Code from part-B test: "RunCommunicateWithServers" written by Aviad */
            Client client = new Client(InetAddress.getLocalHost(), 5401, new IClientStrategy() {
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        toServer.writeObject(maze);
                        toServer.flush();
                        Solution mazeSolution = (Solution) fromServer.readObject();//do something with the solution
                        /*update solution so that mazeDisplayer can use getter to take it*/
                        solution = mazeSolution;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            /* invoking the anonymous "clientStrategy" implemented above */
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void solveMaze() {
//        if(isMazeExist){
        solveMazeThroughSolverServer();
        setChanged();
        notifyObservers("solved");
//    }
    }

    @Override
    public ArrayList<int[]> getSolution() {
        ArrayList<AState> sol = solution.getSolutionPath();
        ArrayList<int[]> intSol = new ArrayList<>();
        for (int i = 0; i < sol.size(); i++) {
            if (sol.get(i) instanceof MazeState) {
                MazeState M = (MazeState) sol.get(i);
                Position P;
                P = M.getPos();
                int[] temp = new int[2];
                temp[0] = P.getRowIndex();
                temp[1] = P.getColumnIndex();
                intSol.add(temp);
            }
        }
        return intSol;
    }




    @Override
     public int[][] Ganerate(int r, int c) {
        generateMazeThroughGeneratorServer(r,c);
          setChanged();//-->hasChanged() will return True
          /* Notify ViewModel */
          notifyObservers("generated");
        System.out.println("ggggggg");
          return maze.getMaze();
     }
    public int[][] getMazeGrid(){return maze.getMaze();}
    public int getSrow(){return maze.getStartPosition().getRowIndex();}
    public int getScol(){return maze.getStartPosition().getColumnIndex();}
    public int getErow(){return maze.getGoalPosition().getRowIndex();}
    public int getEcol(){return maze.getGoalPosition().getColumnIndex();}

     public void ab(Observer o){
          this.addObserver(o);
     }

    @Override
    /** moves the player onKey pressed
     * valid keys are: all NUMPAD except 5, arrows   */
    public void movePlayerModelLogic(KeyCode direction) {
        switch (direction) {
            /*----------------------90 degrees moves---------------------*/
            case UP:
            case NUMPAD8:
                System.out.println("08");
                if(playerRowIdx >= 1 && maze.getval(playerRowIdx -1,playerColIdx)!=1)
                    playerRowIdx--;
                break;
            case DOWN:
            case NUMPAD2:
                System.out.println("02");
                if(playerRowIdx +1 < maze.getRow() && maze.getval(playerRowIdx +1,playerColIdx)!=1)
                    playerRowIdx++;
                break;
            case RIGHT:
            case NUMPAD6:
                if(playerColIdx + 1 < maze.getCol() && maze.getval(playerRowIdx,playerColIdx +1) != 1)
                    playerColIdx++;
                break;
            case LEFT:
            case NUMPAD4:
                if(playerColIdx >= 1 && maze.getval(playerRowIdx,playerColIdx -1)!=1)
                    playerColIdx--;
                break;
            /*----------------------diagonal moves---------------------*/
            case NUMPAD1:
                if(playerColIdx >= 1 && playerRowIdx + 1 < maze.getRow() && maze.getval(playerRowIdx + 1,playerColIdx -1) != 1) {
                    if ((maze.getval(playerRowIdx,playerColIdx - 1) != 1) || ( maze.getval(playerRowIdx +1,playerColIdx)!=1)) {
                        playerColIdx--;
                        playerRowIdx++;
                    }
                }
                break;
            case NUMPAD3:
                if(playerColIdx + 1 < maze.getCol() && playerRowIdx +1 < maze.getRow() && maze.getval(playerRowIdx + 1,playerColIdx +1)!=1) {
                    if ((playerColIdx +1 < maze.getCol() && maze.getval(playerRowIdx,playerColIdx +1)!=1) || (playerRowIdx +1 < maze.getRow() && maze.getval(playerRowIdx +1,playerColIdx)!=1)){
                        playerColIdx++;
                        playerRowIdx++;
                    }
                }
                break;
            case NUMPAD9:
                if(playerColIdx +1 < maze.getCol() && playerRowIdx >= 1 && maze.getval(playerRowIdx - 1,playerColIdx +1)!=1) {
                    if ((playerColIdx +1 < maze.getCol() && maze.getval(playerRowIdx,playerColIdx +1)!=1) || (playerRowIdx >= 1 && maze.getval(playerRowIdx -1,playerColIdx)!=1)) {
                        playerColIdx++;
                        playerRowIdx--;
                    }
                }
                break;
            case NUMPAD7:
                if(playerColIdx >=1 && playerRowIdx >= 1 && maze.getval(playerRowIdx - 1,playerColIdx -1)!=1) {
                    if ((playerColIdx >= 1 && maze.getval(playerRowIdx,playerColIdx -1)!=1) || (playerRowIdx >= 1 && maze.getval(playerRowIdx -1,playerColIdx)!=1)){
                        playerColIdx--;
                        playerRowIdx--;
                    }
                }
                break;
        }

        setChanged();
        notifyObservers("playerMove");

    }

//    public boolean isPlayerAtGoalPosition(){
//        if(playerRowIdx == maze.getGoalPosition().getRowIndex() && playerColIdx==maze.getGoalPosition().getColumnIndex()){
//            return true;
//        }
//        return false;
//    }
//
//    public void loadMaze(File file){
//        int goalRowIdx = 0, goalColIdx = 0 , playerRowIdx = 0, playerColIdx= 0, mazeNumOfRows = 0, mazeNumOfCols = 0;
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            /*read 6 lines from file -- the saved parameters of a maze game */
//            for( int i = 0 ; i < 6 ; i++){
//                String line = br.readLine();
//                if (line != null) {
//                    if(i == 0)
//                        playerRowIdx = Integer.parseInt(line);
//                    if(i == 1)
//                        playerColIdx = Integer.parseInt(line);
//                    if(i == 2)
//                        goalRowIdx = Integer.parseInt(line);
//                    if(i == 3)
//                        goalColIdx = Integer.parseInt(line);
//                    if(i == 4)
//                        mazeNumOfRows = Integer.parseInt(line);
//                    if(i == 5)
//                        mazeNumOfCols = Integer.parseInt(line);
//                }
//            }
//            int[][] grid = new int[mazeNumOfRows][mazeNumOfCols];
//            String line = "";
//            int row = 0;
//            while ((line = br.readLine()) != null) {
//                String[] cols = line.split(",");
//                int col = 0;
//                for (String c : cols) {
//                    grid[row][col] = Integer.parseInt(c);
//                    col++;
//                }
//                row++;
//            }
//            br.close();
//            Position start = new Position(playerRowIdx, playerColIdx);
//            Position end  = new Position(goalRowIdx, goalColIdx);
//            this.grid = grid;
//            this.maze = new Maze(grid, start, end);
//            this.playerColIdx = playerColIdx;
//            this.playerRowIdx = playerRowIdx;
//            //this.mazeGoalColIdx = playerPosColIdx;
//            //this.mazeGoalRowIdx = playerPosRowIdx;
//            //isMazeExist=true;
//            setChanged();
//            notifyObservers("loaded");
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//
//
//    }

    public void save(File chosen)
    {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(chosen));
//            FileOutputStream file = new FileOutputStream(chosen);
//            OutputStream os = new MyCompressorOutputStream(file);
//            maze.setStart(new Position(characterPositionRow, characterPositionColumn));
            os.writeObject(maze.toByteArray());
//            os.write(maze.toByteArray());
            os.flush();
            os.close();
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }
//    ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(file));
//            maze.setStartPosition(playerRow,playerCol);
//            objectOutput.writeObject(maze.toByteArray());

    @Override
    public void loadGame(File loadfile) throws IOException, ClassNotFoundException {
//        ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(loadfile));

//        FileInputStream file = new FileInputStream(loadfile);
//        InputStream in = new MyDecompressorInputStream(file);
     //   byte savedMazeBytes[] = new byte[0];
//        byte[] loadedMaze = (byte[])objectIn.readObject();
//        maze = new Maze(loadedMaze);
        ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(loadfile));
        byte[] loadedMaze = (byte[]) objectIn.readObject();
        objectIn.close();
        maze = new Maze(loadedMaze);
//        // savedMazeBytes = new byte[maze.toByteArray().length+15];
//        in.read(savedMazeBytes);
//        in.close();
//        Maze loadedMaze = new Maze(savedMazeBytes);
//        maze=loadedMaze;
        setChanged();
        notifyObservers("loaded");
    }



//
    public void exit(){
        stopServers();
//        threadPool.shutdown();
    }
}



