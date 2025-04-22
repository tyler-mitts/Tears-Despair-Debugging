package hw4.player;

import java.util.List;
import hw4.maze.Cell;
import hw4.maze.CellComponents;
import hw4.maze.Row;

public class Player {
	private Row currentRow;
	private Cell currentCell;
	
	public Player(Row currentRow, Cell currentCell) {
		this.currentRow = currentRow;
		this.currentCell = currentCell;
	}
	
	public Row getCurrentRow() {
		return currentRow;
	}
	
	public Cell getCurrentCell() {
		return currentCell;
	}
	
	public boolean move(Movement direction, List<Row> grid) {
		int rowIndex = grid.indexOf(currentRow);
		int colIndex = currentRow.getCells().indexOf(currentCell);
		
		switch(direction) {
			case UP:
				if (currentCell.getUp() != CellComponents.WALL && rowIndex > 0) {
					currentRow =grid.get(rowIndex - 1);
					currentCell = currentRow.getCells().get(colIndex);
					return true;
				}
				break;
				
			case DOWN:
				if (currentCell.getDown() != CellComponents.WALL && rowIndex < grid.size()- 1) {
					currentRow =grid.get(rowIndex + 1);
					currentCell = currentRow.getCells().get(colIndex);
					return true;
				}
				break;
				
			case LEFT:
				if (currentCell.getLeft() != CellComponents.WALL && colIndex > 0) {
					currentCell = currentRow.getCells().get(colIndex - 1);
					return true;
				}
				break;
				
			case RIGHT:
				if (currentCell.getRight() != CellComponents.WALL && colIndex < currentRow.getCells().size() - 1) {
					currentCell = currentRow.getCells().get(colIndex + 1);
					return true;
				}
				break;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Player's current cell:" + currentCell + ", current row:" + currentRow;
	}
}
