package hw4.game;

import java.util.Random;

import maze.Cell;
import maze.CellComponents;
import maze.Grid;
import maze.Row;

public class Game {
	private Grid grid;
	
	public Game(int gridSize) {
		if( (gridSize >= 3) || (gridSize <= 7) ) {
			this.setGrid(createRandomGrid(gridSize));
		}
		else {
			System.out.println("Invalid Grid Size, must be between 3 and 7.");
		}
	}
	
	public Game(Grid grid) {
		this.setGrid(grid);
	}
	
	public Grid createRandomGrid(int gridSize) {
		//Check to see if it fits in size criteria
		if( (gridSize < 3) || (gridSize > 7) ) {
			return null;
		}
		
		//Creates instance of random class for the use of randomly creating the game board
		Random random = new Random();
		
		//Basis for grid, creates an array of rows 
		Row[] columns = new Row[gridSize];
		
		//Creates a new row for every column in grid (same amount of each)
		for(int i = 0;i<gridSize;i++) {
			columns[i] = new Row(gridSize);
			
			//Creates an array of cells for each row
			for(int j = 0;j<gridSize;j++) {
				columns[i].setRow(new Cell[gridSize]);
				
				//Sets each cell in grid to randomly have WALL or APERTURE for left,right,up,down values
				columns[i].getCellAtIndex(j).setLeft(
						random.nextInt(2) == 0 ? CellComponents.WALL : CellComponents.APERTURE);
				columns[i].getCellAtIndex(j).setRight(
						random.nextInt(2) == 0 ? CellComponents.WALL : CellComponents.APERTURE);
				columns[i].getCellAtIndex(j).setUp(
						random.nextInt(2) == 0 ? CellComponents.WALL : CellComponents.APERTURE);
				columns[i].getCellAtIndex(j).setDown(
						random.nextInt(2) == 0 ? CellComponents.WALL : CellComponents.APERTURE);
				
			}
			
			//Sets one of the leftmost cells' left value to EXIT
			columns[0].getCellAtIndex(random.nextInt(gridSize)).setLeft(CellComponents.EXIT);
		}
		
		//Returns the grid
		Grid grid = new Grid(columns);
		return grid;
	}
	
	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}
}
