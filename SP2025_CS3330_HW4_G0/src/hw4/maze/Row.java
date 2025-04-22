package hw4.maze;

import java.util.ArrayList;

/**
 * Represents a row in the maze grid containing cells.
 */
public class Row {
	private ArrayList<Cell> cells;
	
	/**
     * Creates a new row with empty cells of specified size.
     * @param size The number of cells in this row
     */
	public Row(int size) {
		this.cells = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.cells.add(new Cell());
        }
	}
	
    /**
     * Creates a new row with the specified cells.
     * @param cells The list of cells in this row
     */
    public Row(ArrayList<Cell> cells) {
        this.cells = cells;
    }
	
    /**
     * Gets the cell at a specific index in this row.
     * @return The cell at the specified index
     */
	public Cell getCellAtIndex(int index) {
		return cells.get(index);
	}

	/**
     * Gets the list of cells in this row.
     * @return The list of cells
     */
    public ArrayList<Cell> getCells() {
        return cells;
    }
    
    /**
     * Sets the list of cells in this row.
     * @param cells The new list of cells
     */
    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }
    
    /**
     * Returns a string representation of the row.
     * @return String representation of the row's cells
     */
    @Override
    public String toString() {
        return "Row [cells=" + cells + "]";
    }
}
