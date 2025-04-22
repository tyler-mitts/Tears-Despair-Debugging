package hw4.simulation;

import java.util.Scanner;

import hw4.game.Game;
import hw4.maze.Cell;
import hw4.maze.CellComponents;
import hw4.maze.Grid;
import hw4.maze.Row;
import hw4.player.Movement;
import hw4.player.Player;

/**
 * Simulation class for the "Tears, Despair & Debugging" game.
 * Demonstrates game functionality with a text-based interface.
 */
public class Simulation {
    private Game game;
    private Player player;
    private boolean gameRunning;
    
    /**
     * Creates a new simulation with a randomly generated grid of specified size.
     * @param gridSize The size of the grid for the game (3-7)
     */
    public Simulation(int gridSize) {
        this.game = new Game(gridSize);
        this.gameRunning = true;
        
        Grid grid = game.getGrid();
        int lastRowIndex = grid.getRows().size() - 1;
        Row lastRow = grid.getRows().get(lastRowIndex);
        int lastCellIndex = lastRow.getCells().size() - 1;
        player = new Player(lastRow, lastRow.getCells().get(lastCellIndex));
        
        System.out.println("Welcome to Tears, Despair & Debugging!");
        System.out.println("Try to escape the maze by reaching the EXIT (E).");
        System.out.println("Controls: W (Up), S (Down), A (Left), D (Right), Q (Quit)");
        System.out.println();
    }
    
    /**
     * Runs the simulation, allowing the player to navigate the maze.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        
        while (gameRunning) {

            System.out.println("Current Maze:");
            game.printGrid(player);
            
            System.out.print("Enter your move (W/S/A/D) or Q to quit: ");
            String input = scanner.nextLine().toUpperCase();
            
            if (input.equals("Q")) {
                System.out.println("Thanks for playing!");
                gameRunning = false;
                continue;
            }
            
            boolean moveSuccessful = false;
            
            switch (input) {
                case "W":
                    moveSuccessful = game.play(Movement.UP, player);
                    break;
                case "S":
                    moveSuccessful = game.play(Movement.DOWN, player);
                    break;
                case "A":
                    moveSuccessful = game.play(Movement.LEFT, player);
                    Cell currentCell = player.getCurrentCell();
                    int cellIndex = player.getCurrentRow().getCells().indexOf(currentCell);
                    if (moveSuccessful && cellIndex == 0 && currentCell.getLeft() == CellComponents.EXIT) {
                        System.out.println("Congratulations! You've escaped the maze!");
                        gameRunning = false;
                    }
                    break;
                case "D":
                    moveSuccessful = game.play(Movement.RIGHT, player);
                    break;
                default:
                    System.out.println("Invalid input. Use W/S/A/D to move or Q to quit.");
                    continue;
            }
            
            if (!moveSuccessful && gameRunning) {
                System.out.println("You can't move in that direction. Try another way.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Main method to start the simulation.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Tears, Despair & Debugging!");
        System.out.print("Enter grid size (3-7): ");
        
        int gridSize = 5;
        try {
            gridSize = Integer.parseInt(scanner.nextLine());
            if (gridSize < 3 || gridSize > 7) {
                System.out.println("Invalid size. Using default size 5.");
                gridSize = 5;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Using default size 5.");
        }
        
        Simulation simulation = new Simulation(gridSize);
        simulation.run();
        
        scanner.close();
    }
}