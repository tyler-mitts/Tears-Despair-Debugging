package hw4.player;

import hw4.maze.Cell;
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
	
	/**
	 * Sets the current cell position of the player.
	 * @param currentCell The new cell position
	 */
	public void setCurrentCell(Cell currentCell) {
	    this.currentCell = currentCell;
	}

	/**
	 * Sets the current row position of the player.
	 * @param currentRow The new row position
	 */
	public void setCurrentRow(Row currentRow) {
	    this.currentRow = currentRow;
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
