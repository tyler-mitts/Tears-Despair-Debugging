package hw4.game;

import java.util.Random;
import java.util.ArrayList;

import hw4.maze.Cell;
import hw4.maze.CellComponents;
import hw4.maze.Grid;
import hw4.maze.Row;

/**
 * Represents the game with maze grid and player movement.
 * Handles grid creation and player movement in the maze.
 */
public class Game {
	private Grid grid;
	
	/**
     * Creates a new game with a grid of the specified size.
     * @param gridSize The size of the grid (3-7)
     */
	public Game(int gridSize) {
		if( (gridSize >= 3) || (gridSize <= 7) ) {
			this.setGrid(createRandomGrid(gridSize));
		}
		else {
			System.out.println("Invalid Grid Size, must be between 3 and 7.");
		}
	}
	
	/**
     * Creates a new game with a specified grid.
     * @param grid The grid to use for the game
     */
	public Game(Grid grid) {
		this.setGrid(grid);
	}
	
	/**
     * Creates a random grid of the specified size.
     * @param gridSize The size of the grid (3-7)
     * @return A randomly generated grid
     */
	public Grid createRandomGrid(int gridSize) {
		//Check to see if it fits in size criteria
		if( (gridSize < 3) || (gridSize > 7) ) {
			return null;
		}
		
		//Creates instance of random class for the use of randomly creating the game board
		Random random = new Random();
		
		//Basis for grid, creates an array of rows 
		ArrayList<Row> rows = new ArrayList<>(gridSize);
		
		// Initialize all rows and cells first
	    for (int i = 0; i < gridSize; i++) {
	        ArrayList<Cell> cells = new ArrayList<>(gridSize);
	        for (int j = 0; j < gridSize; j++) {
	            cells.add(new Cell());
	        }
	        rows.add(new Row(cells));
	    }
	    
	    for (int i = 0; i < gridSize; i++) {
	    	for (int j = 0; j < gridSize; j++) {
	    		Cell currentCell = rows.get(i).getCellAtIndex(j);
	    		
	    		// If not in the first row, share UP component with the cell above
	            if (i > 0) {
	                currentCell.setUp(rows.get(i-1).getCellAtIndex(j).getDown());
	            } else {
	                // For first row cells, randomly assign UP component
	                currentCell.setUp(random.nextInt(2) == 0 ? CellComponents.WALL : CellComponents.APERTURE);
	            }
	            
                // For all cells, randomly assign DOWN component
                currentCell.setDown(random.nextInt(2) == 0 ? CellComponents.WALL : CellComponents.APERTURE);
                
                // If not in the first column, share LEFT component with the cell to the left
                if (j > 0) {
                    currentCell.setLeft(rows.get(i).getCellAtIndex(j-1).getRight());
                } else {
                    // For first column cells (leftmost), randomly assign LEFT component
                    currentCell.setLeft(random.nextInt(2) == 0 ? CellComponents.WALL : CellComponents.APERTURE);
                }
                
                // For all cells, randomly assign RIGHT component
                currentCell.setRight(random.nextInt(2) == 0 ? CellComponents.WALL : CellComponents.APERTURE);

             // Ensure each cell has at least one APERTURE
                if (currentCell.getLeft() == CellComponents.WALL &&
                    currentCell.getRight() == CellComponents.WALL &&
                    currentCell.getUp() == CellComponents.WALL &&
                    currentCell.getDown() == CellComponents.WALL) {
                    
                    // Find components that can be changed to APERTURE without breaking consistency
                    ArrayList<Integer> changeable = new ArrayList<>();
                    
                    // Left side can be changed if it's the first column
                    if (j == 0) changeable.add(0);
                    // Right side can be changed if it's the last column
                    if (j == gridSize - 1) changeable.add(1);
                    // Up side can be changed if it's the first row
                    if (i == 0) changeable.add(2);
                    // Down side can be changed if it's the last row
                    if (i == gridSize - 1) changeable.add(3);
                    
                    if (!changeable.isEmpty()) {
                        // Pick a random side that can be changed
                        int side = changeable.get(random.nextInt(changeable.size()));
                        switch (side) {
                            case 0: currentCell.setLeft(CellComponents.APERTURE); break;
                            case 1: currentCell.setRight(CellComponents.APERTURE); break;
                            case 2: currentCell.setUp(CellComponents.APERTURE); break;
                            case 3: currentCell.setDown(CellComponents.APERTURE); break;
                        }
                    } else {
                        // If no sides can be changed without breaking consistency,
                        // we're in the middle of the grid. Let's change the right side
                        // but we'll also need to update the cell to the right
                        currentCell.setRight(CellComponents.APERTURE);
                        if (j < gridSize - 1) {
                            rows.get(i).getCellAtIndex(j+1).setLeft(CellComponents.APERTURE);
                        }
                    }
                }
	    	}
	    }
	    
	    // Set one EXIT on the leftmost side
	    int exitRow = random.nextInt(gridSize);
	    rows.get(exitRow).getCellAtIndex(0).setLeft(CellComponents.EXIT);
	    
		return new Grid(rows);
	}
	
	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}
}
