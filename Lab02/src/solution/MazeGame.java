package solution;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * A maze game.
 * 
 * @author Justin Pretlow
 * @version January 23, 2020
 *
 */
public class MazeGame
{
    /**
     * The size of each side of the game map.
     */
    private final static int HEIGHT = 19;
    private final static int WIDTH = 39;

    /**
     * The game map, as a 2D array of ints.
     */
    private boolean[][] blocked;
    private boolean[][] breadCrumb;
    /**
     * The current location of the player vertically.
     */
    private int userCol;
    
    /**
     * The current location of the player horizontally.
     */
    private int userRow;
    
    /**
     * The scanner from which each move is read.
     */
    private Scanner moveScanner;
        
    /**
     * The row and column of the goal.
     */
    private int goalRow;
    private int goalCol;
    /**
     * The row and column of the start.
     */
    private int startRow;
    private int startCol;
    
    /**
     * Constructor initializes the maze with the data in 'mazeFile'.
     * @param mazeFile the input file for the maze
     */
    public MazeGame(String mazeFile)
    {
        loadMaze(mazeFile);
        moveScanner = new Scanner(System.in);
    }

    /**
     * Constructor initializes the maze with the 'mazeFile' and the move 
     * scanner with 'moveScanner'.
     * @param mazeFile the input file for the maze
     * @param moveScanner the scanner object from which to read user moves
     */
    public MazeGame(String mazeFile, Scanner moveScanner)
    {
        loadMaze(mazeFile);
        this.moveScanner = moveScanner;
    }

    /**
     * getMaze returns a copy of the current maze for testing purposes.
     * 
     * @return the grid
     */
    public boolean[][] getMaze()
    {
        if (blocked == null)
        {
            return null;
        }
        
        boolean[][] copy = new boolean[HEIGHT][WIDTH];
        
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                copy[i][j] = blocked[i][j];
            }
        }
        return copy;
    }

    /**
     * setMaze sets the current map for testing purposes.
     * 
     * @param maze
     *            another maze.
     */
    public void setMaze(boolean[][] maze)
    {
        this.blocked =maze;
    }
    
    /**
     * Function loads the data from the maze file and creates the 'blocked' 
     * 2D array.
     *  
     * @param mazeFile the input maze file.
     */
    public void loadMaze(String mazeFile)
    {
        blocked = new boolean[HEIGHT][WIDTH];
        breadCrumb = new boolean[HEIGHT][WIDTH];
        Scanner reader = null;
        File fileIn = new File(mazeFile);
        try
        {
            reader = new Scanner(fileIn);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not find the file.");
            System.exit(0);
        }
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                breadCrumb[i][j] = false;
                String read = reader.next();
                if (read.equals("0"))
                {
                    blocked[i][j] = true;
                }
                else if (read.equals("1"))
                {
                    blocked[i][j] = false;
                }
                else if (read.equals("S"))
                {
                    setUserCol(i);
                    setUserRow(j);
                    setStartCol(i);
                    setStartRow(j);
                    blocked[i][j] = true;
                }
                else if (read.equals("G"))
                {
                    setGoalCol(i);
                    setGoalRow(j);
                    blocked[i][j] = true;
                }
            }
        }
        reader.close();
    }
    
    /**
     * Actually plays the game.
     */
    public void playGame()
    {

    }

    /**
     * Checks to see if the player has won the game.
     * @return true if the player has won.
     */
    public boolean playerAtGoal()
    {
        return userRow == goalRow && userCol == goalCol;
    }
    /**
     * Makes a move based on the String.
     * 
     * @param move
     *            the direction to make a move in.
     * @return whether the move was valid.
     */
    public boolean makeMove(String move)
    {
        // TODO
        return false;
    }

    /**
     * Prints the map of the maze.
     */
    public void printMaze()
    {
        System.out.print("*");
        for (int i = 0; i < WIDTH; i++)
        {
            System.out.print("-");
        }
        System.out.println("*");
        for (int i = 0; i < HEIGHT; i++)
        {
            System.out.print("|");
            for (int j = 0; j < WIDTH; j++)
            {
                if (i == userRow && j == userCol)
                {
                    System.out.print("@");
                }
                else if (i == startRow && j == startCol)
                {
                    System.out.print("S");
                }
                else if (i == goalRow && j == goalCol)
                {
                    System.out.print("G");
                }
                else if (breadCrumb[i][j] == true)
                {
                    System.out.print(".");
                }
                else if (blocked[i][j] == true)
                {
                    System.out.print(" ");
                }
                else if (blocked[i][j] == false)
                {
                    System.out.print("X");
                }
            }
            System.out.println("|");
        }
        System.out.print("*");
        for (int i = 0; i < WIDTH; i++)
        {
            System.out.print("-");
        }
        System.out.println("*");
    }

    /**
     * Creates a new game, using a command line argument file name, if one is
     * provided.
     * 
     * @param args the command line arguments
     */

    public static void main(String[] args)
    {
        String mapFile = "data/easy.txt";
        Scanner scan = new Scanner(System.in);
        MazeGame game = new MazeGame(mapFile, scan);
        game.playGame();
    }
    
    /**
     * This is the getter for the blocked array.
     * @return This returns the array.
     */
    public boolean[][] getBlocked()
    {
        if (blocked == null)
        {
            return null;
        }
        
        boolean[][] copy = new boolean[HEIGHT][WIDTH];
        
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                copy[i][j] = !blocked[i][j];
            }
        }
        return copy;
    }
    
    /**
     * This is the setter for the blocked array.
     * @param blocked This is the array.
     */
    public void setBlocked(boolean[][] blocked)
    {
        this.blocked = blocked;
    }
        
    /**
     * This is the getter for the user's column.
     * @return userCol This returns the user's column.
     */
    public int getUserCol()
    {
        return userCol;
    }
    
    /**
     * This is the setter for the user's column.
     * @param userCol This is the user's column.
     */
    public void setUserCol(int userCol)
    {
        this.userCol = userCol;
    }
    
    /**
     * This is the getter for the user's row.
     * @return userRow This is the user's row.
     */
    public int getUserRow()
    {
        return userRow;
    }
    
    /**
     * This is the setter of the user's row.
     * @param userRow This is the user's row.
     */
    public void setUserRow(int userRow)
    {
        this.userRow = userRow;
    }

    
    /**
     * This is the getter for moveScanner.
     * @return moveScanner This is the moveScanner.
     */
    public Scanner getMoveScanner()
    {
        return moveScanner;
    }
    
    /**
     * This is the setter for moveScanner.
     * @param moveScanner This is the moveScanner.
     */
    public void setMoveScanner(Scanner moveScanner)
    {
        this.moveScanner = moveScanner;
    }
    
    /**
     * This is the getter for goalRow.
     * @return goalRow This is the goal row from the mazeFile.
     */
    public int getGoalRow()
    {
        return goalRow;
    }
    
    /**
     * This is the setter for goalRow.
     * @param goalRow This is the goal row from the mazeFile.
     */
    public void setGoalRow(int goalRow)
    {
        this.goalRow = goalRow;
    }
    
    /**
     * This is the getter for goalCol.
     * @return goalRow This is the goal column from the mazeFile.
     */
    public int getGoalCol()
    {
        return goalCol;
    }
    
    /**
     * This is the setter for goalCol.
     * @param goalCol This is the goal column from the mazeFile.
     */
    public void setGoalCol(int goalCol)
    {
        this.goalCol = goalCol;
    }
    
    /**
     * This is the getter for startRow.
     * @return startRow This is what row you start on.
     */
    public int getStartRow()
    {
        return startRow;
    }
    
    /**
     * This is the setter for startRow.
     * @param startRow This is what row you start on.
     */
    public void setStartRow(int startRow)
    {
        this.startRow = startRow;
    }
    
    /**
     * This is the getter for startCol.
     * @return startCol This is what column you will start on.
     */
    public int getStartCol()
    {
        return startCol;
    }
    
    /**
     * This is the setter for startCol.
     * @param startCol This is what column you will start on.
     */
    public void setStartCol(int startCol)
    {
        this.startCol = startCol;
    }
}
