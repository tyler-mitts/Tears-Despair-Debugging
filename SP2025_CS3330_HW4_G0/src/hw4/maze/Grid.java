package hw4.maze;

import java.util.ArrayList;

/**
 * Represents the grid of the maze containing rows of cells.
 */
public class Grid {
	private ArrayList<Row> rows;

	/**
     * Creates a new grid with specified rows.
     * @param rows The list of rows in the grid
     */
	public Grid(ArrayList<Row> rows) {
		this.rows = rows;
	}
	
	/**
     * Gets the list of rows in the grid.
     * @return The list of rows
     */
    public ArrayList<Row> getRows() {
        return rows;
    }
    
    /**
     * Sets the list of rows in the grid.
     * @param rows The new list of rows
     */
    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }
    
    /**
     * Returns a string representation of the grid.
     * @return String representation of the grid's rows
     */
    @Override
    public String toString() {
        return "Grid [rows=" + rows + "]";
    }
}
