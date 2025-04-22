package hw4.player;

import java.util.List;
import hw4.maze.Cell;
import hw4.maze.CellComponents;
import hw4.maze.Row;

/**
 * Represents a player in the maze game.
 * Maintains the current position of the player in terms of cell and row.
 */
public class Player {
	private Row currentRow;
	private Cell currentCell;
	
	/**
     * Creates a new player with specified position in the maze.
     * @param currentRow The row where the player starts
     * @param currentCell The cell where the player starts
     */
	public Player(Row currentRow, Cell currentCell) {
		this.currentRow = currentRow;
		this.currentCell = currentCell;
	}
	
	/**
     * Gets the row where the player is currently located.
     * @return The current row
     */
	public Row getCurrentRow() {
		return currentRow;
	}
	
	/**
     * Gets the cell where the player is currently located.
     * @return The current cell
     */
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
	
	/**
     * Returns a string representation of the player.
     * @return String representation of the player's position
     */
    @Override
    public String toString() {
        return "Player [currentCell=" + currentCell + ", currentRow=" + currentRow + "]";
    }
}
