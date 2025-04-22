package hw4.game;

import java.util.Random;
import java.util.ArrayList;

import hw4.maze.Cell;
import hw4.maze.CellComponents;
import hw4.maze.Grid;
import hw4.maze.Row;
import hw4.player.Player;
import hw4.player.Movement;

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
	    		
	    		// Set top edge to WALL
	            if (i == 0) {
	                currentCell.setUp(CellComponents.WALL);
	            } else {
	                // Get component from the cell above
	                currentCell.setUp(rows.get(i-1).getCellAtIndex(j).getDown());
	            }
	            
	            // Set bottom edge to WALL
	            if (i == gridSize - 1) {
	                currentCell.setDown(CellComponents.WALL);
	            } else {
	                // Randomly set DOWN (will be shared with cell below)
	                currentCell.setDown(random.nextInt(2) == 0 ? CellComponents.WALL : CellComponents.APERTURE);
	            }
	            
	            // Set left edge to WALL (except for the EXIT)
	            if (j == 0) {
	                // Initially all left edges are WALL (we'll set one to EXIT later)
	                currentCell.setLeft(CellComponents.WALL);
	            } else {
	                // Get component from the cell to the left
	                currentCell.setLeft(rows.get(i).getCellAtIndex(j-1).getRight());
	            }
                
	            // Set right edge to WALL
	            if (j == gridSize - 1) {
	                currentCell.setRight(CellComponents.WALL);
	            } else {
	                // Randomly set RIGHT (will be shared with cell to the right)
	                currentCell.setRight(random.nextInt(2) == 0 ? CellComponents.WALL : CellComponents.APERTURE);
	            }
	    	}
	    }
	    
	    // Ensure all cells have at least one aperture
	    
	    // Top-left corner
	    Cell topLeft = rows.get(0).getCellAtIndex(0);
	    if (topLeft.getRight() == CellComponents.WALL && topLeft.getDown() == CellComponents.WALL) {
	        // Randomly choose between right and down for aperture
	        if (random.nextBoolean()) {
	            topLeft.setRight(CellComponents.APERTURE);
	            rows.get(0).getCellAtIndex(1).setLeft(CellComponents.APERTURE);
	        } else {
	            topLeft.setDown(CellComponents.APERTURE);
	            rows.get(1).getCellAtIndex(0).setUp(CellComponents.APERTURE);
	        }
	    }
	    
	    // Top-right corner
	    Cell topRight = rows.get(0).getCellAtIndex(gridSize - 1);
	    if (topRight.getLeft() == CellComponents.WALL && topRight.getDown() == CellComponents.WALL) {
	        // Randomly choose between left and down for aperture
	        if (random.nextBoolean()) {
	            topRight.setLeft(CellComponents.APERTURE);
	            rows.get(0).getCellAtIndex(gridSize - 2).setRight(CellComponents.APERTURE);
	        } else {
	            topRight.setDown(CellComponents.APERTURE);
	            rows.get(1).getCellAtIndex(gridSize - 1).setUp(CellComponents.APERTURE);
	        }
	    }
	    
	    // Bottom-left corner
	    Cell bottomLeft = rows.get(gridSize - 1).getCellAtIndex(0);
	    if (bottomLeft.getRight() == CellComponents.WALL && bottomLeft.getUp() == CellComponents.WALL) {
	        // Randomly choose between right and up for aperture
	        if (random.nextBoolean()) {
	            bottomLeft.setRight(CellComponents.APERTURE);
	            rows.get(gridSize - 1).getCellAtIndex(1).setLeft(CellComponents.APERTURE);
	        } else {
	            bottomLeft.setUp(CellComponents.APERTURE);
	            rows.get(gridSize - 2).getCellAtIndex(0).setDown(CellComponents.APERTURE);
	        }
	    }
	    
	    // Bottom-right corner
	    Cell bottomRight = rows.get(gridSize - 1).getCellAtIndex(gridSize - 1);
	    if (bottomRight.getLeft() == CellComponents.WALL && bottomRight.getUp() == CellComponents.WALL) {
	        // Randomly choose between left and up for aperture
	        if (random.nextBoolean()) {
	            bottomRight.setLeft(CellComponents.APERTURE);
	            rows.get(gridSize - 1).getCellAtIndex(gridSize - 2).setRight(CellComponents.APERTURE);
	        } else {
	            bottomRight.setUp(CellComponents.APERTURE);
	            rows.get(gridSize - 2).getCellAtIndex(gridSize - 1).setDown(CellComponents.APERTURE);
	        }
	    }
	    
	    // Top edge (not corners)
	    for (int j = 1; j < gridSize - 1; j++) {
	        Cell cell = rows.get(0).getCellAtIndex(j);
	        if (cell.getLeft() == CellComponents.WALL && cell.getRight() == CellComponents.WALL && cell.getDown() == CellComponents.WALL) {
	            // Create an aperture downward
	            cell.setDown(CellComponents.APERTURE);
	            // Update the cell below for consistency
	            rows.get(1).getCellAtIndex(j).setUp(CellComponents.APERTURE);
	        }
	    }
	    
	    // Bottom edge (not corners)
	    for (int j = 1; j < gridSize - 1; j++) {
	        Cell cell = rows.get(gridSize - 1).getCellAtIndex(j);
	        if (cell.getLeft() == CellComponents.WALL && cell.getRight() == CellComponents.WALL && cell.getUp() == CellComponents.WALL) {
	            // Create an aperture upward
	            cell.setUp(CellComponents.APERTURE);
	            // Update the cell above for consistency
	            rows.get(gridSize - 2).getCellAtIndex(j).setDown(CellComponents.APERTURE);
	        }
	    }
	    
	    // Left edge (not corners and not EXIT)
	    for (int i = 1; i < gridSize - 1; i++) {
	        Cell cell = rows.get(i).getCellAtIndex(0);
	        if (cell.getUp() == CellComponents.WALL && cell.getDown() == CellComponents.WALL && cell.getRight() == CellComponents.WALL) {
	            // Create an aperture to the right
	            cell.setRight(CellComponents.APERTURE);
	            // Update the cell to the right for consistency
	            rows.get(i).getCellAtIndex(1).setLeft(CellComponents.APERTURE);
	        }
	    }
	    
	    // Right edge (not corners)
	    for (int i = 1; i < gridSize - 1; i++) {
	        Cell cell = rows.get(i).getCellAtIndex(gridSize - 1);
	        if (cell.getUp() == CellComponents.WALL && cell.getDown() == CellComponents.WALL && cell.getLeft() == CellComponents.WALL) {
	            // Create an aperture to the left
	            cell.setLeft(CellComponents.APERTURE);
	            // Update the cell to the left for consistency
	            rows.get(i).getCellAtIndex(gridSize - 2).setRight(CellComponents.APERTURE);
	        }
	    }
	    
	    // Ensure all interior cells have at least one aperture
	    for (int i = 1; i < gridSize - 1; i++) {
	        for (int j = 1; j < gridSize - 1; j++) {
	            Cell cell = rows.get(i).getCellAtIndex(j);
	            // Count apertures
	            int apertureCount = 0;
	            if (cell.getUp() == CellComponents.APERTURE) apertureCount++;
	            if (cell.getDown() == CellComponents.APERTURE) apertureCount++;
	            if (cell.getLeft() == CellComponents.APERTURE) apertureCount++;
	            if (cell.getRight() == CellComponents.APERTURE) apertureCount++;
	            
	            if (apertureCount == 0) {
	                // Add an aperture in a random direction
	                int direction = random.nextInt(4);
	                switch (direction) {
	                    case 0:
	                        cell.setUp(CellComponents.APERTURE);
	                        rows.get(i-1).getCellAtIndex(j).setDown(CellComponents.APERTURE);
	                        break;
	                    case 1:
	                        cell.setDown(CellComponents.APERTURE);
	                        rows.get(i+1).getCellAtIndex(j).setUp(CellComponents.APERTURE);
	                        break;
	                    case 2:
	                        cell.setLeft(CellComponents.APERTURE);
	                        rows.get(i).getCellAtIndex(j-1).setRight(CellComponents.APERTURE);
	                        break;
	                    case 3:
	                        cell.setRight(CellComponents.APERTURE);
	                        rows.get(i).getCellAtIndex(j+1).setLeft(CellComponents.APERTURE);
	                        break;
	                }
	            }
	        }
	    }
	    
	    // Set one EXIT on the leftmost side
	    int exitRow = random.nextInt(gridSize);
	    rows.get(exitRow).getCellAtIndex(0).setLeft(CellComponents.EXIT);
	    
		return new Grid(rows);
	}
	
	/**
	 * Handles player movement in the maze.
	 * @param movement The direction of movement
	 * @param player The player to move
	 * @return True if the movement is valid and successful, false otherwise
	 */
	public boolean play(Movement movement, Player player) {
		if (movement == null || player == null) {
			return false;
		}
		
		Cell currentCell = player.getCurrentCell();
		Row currentRow = player.getCurrentRow();
		
		int rowIndex = grid.getRows().indexOf(currentRow);
		if (rowIndex == -1) {
			return false;
		}
		
		int cellIndex = currentRow.getCells().indexOf(currentCell);
		if (cellIndex == -1) {
			return false;
		}
		
		switch (movement) {
			case UP:
	            // Check if we can move up - aperture in current cell
	            if (currentCell.getUp() == CellComponents.APERTURE) {
	                Row upRow = grid.getRows().get(rowIndex - 1);
	                Cell upCell = upRow.getCells().get(cellIndex);
	                player.setCurrentRow(upRow);
	                player.setCurrentCell(upCell);
	                return true;
	            }
	            break;
	            
	        case DOWN:
	            // Check if we can move down - aperture in current cell
	            if (currentCell.getDown() == CellComponents.APERTURE) {
	                Row downRow = grid.getRows().get(rowIndex + 1);
	                Cell downCell = downRow.getCells().get(cellIndex);
	                player.setCurrentRow(downRow);
	                player.setCurrentCell(downCell);
	                return true;
	            }
	            break;
	            
	        case LEFT:
	            // Check if we can move left - either aperture or exit in current cell
	            if (currentCell.getLeft() == CellComponents.APERTURE) {
	                Cell leftCell = currentRow.getCells().get(cellIndex - 1);
	                player.setCurrentCell(leftCell);
	                return true;
	            } else if (currentCell.getLeft() == CellComponents.EXIT) {
	                // Player has reached the exit
	                System.out.println("Player has escaped the maze!");
	                return true;
	            }
	            break;
	            
	        case RIGHT:
	            // Check if we can move right - aperture in current cell
	            if (currentCell.getRight() == CellComponents.APERTURE) {
	                Cell rightCell = currentRow.getCells().get(cellIndex + 1);
	                player.setCurrentCell(rightCell);
	                return true;
	            }
	            break;
		}
		
		return false;
	}
	
	/**
     * Gets the grid of the game.
     * @return The grid
     */
	public Grid getGrid() {
		return grid;
	}

	/**
     * Sets the grid of the game.
     * @param grid The new grid
     */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	/**
     * Returns a string representation of the game.
     * @return String representation of the game's grid
     */
    @Override
    public String toString() {
        return "Game [grid=" + grid + "]";
    }
    
    /**
     * Prints the current state of the grid with the player's position.
     * @param player The player to display on the grid
     */
    public void printGrid(Player player) {
    	int gridSize = grid.getRows().size();
    	
    	char[][] displayGrid = new char[gridSize][gridSize];
    	
    	for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                displayGrid[i][j] = 'S';
            }
        }
    	
    	for (int i = 0; i < gridSize; i++) {
            if (grid.getRows().get(i).getCells().get(0).getLeft() == CellComponents.EXIT) {
                displayGrid[i][0] = 'E';
            }
        }
    	
    	int playerRowIndex = grid.getRows().indexOf(player.getCurrentRow());
        int playerCellIndex = player.getCurrentRow().getCells().indexOf(player.getCurrentCell());
        displayGrid[playerRowIndex][playerCellIndex] = 'A';
        
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(displayGrid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
