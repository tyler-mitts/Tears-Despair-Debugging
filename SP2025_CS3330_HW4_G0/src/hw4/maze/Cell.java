package hw4.maze;

/**
 * Represents a cell in the maze grid.
 * Each cell has four components (up, down, left, right) that can be a WALL, APERTURE, or EXIT.
 */
public class Cell {
	private CellComponents left, right, up, down;
	
	/**
     * Creates a new cell with specified components for each direction.
     * @param left The component on the left side
     * @param right The component on the right side
     * @param up The component on the upper side
     * @param down The component on the lower side
     */
	public Cell(CellComponents left, CellComponents right, CellComponents up, CellComponents down) {
		this.setLeft(left);
		this.setRight(right);
		this.setUp(up);
		this.setDown(down);
	}

	/**
     * Default constructor that creates an empty cell.
     */
	public Cell() {
		this.left = CellComponents.WALL;
        this.right = CellComponents.WALL;
        this.up = CellComponents.WALL;
        this.down = CellComponents.WALL;
	}
	
	/**
     * Gets the component on the left side of the cell.
     * @return The left component
     */
	public CellComponents getLeft() {
		return left;
	}

	/**
     * Sets the component on the left side of the cell.
     * @param left The left component to set
     */
	public void setLeft(CellComponents left) {
		if (left == null ) {
			this.left = CellComponents.WALL;
		} else {
			this.left = left;
		}
	}

	/**
     * Gets the component on the right side of the cell.
     * @return The right component
     */
	public CellComponents getRight() {
		return right;
	}

	/**
     * Sets the component on the right side of the cell.
     * @param right The right component to set
     */
	public void setRight(CellComponents right) {
		if (right == null ) {
			this.right = CellComponents.WALL;
		} else {
			this.right = right;
		}
	}

	/**
     * Gets the component on the upper side of the cell.
     * @return The upper component
     */
	public CellComponents getUp() {
		return up;
	}

	/**
     * Sets the component on the upper side of the cell.
     * @param up The upper component to set
     */
	public void setUp(CellComponents up) {
		if (up == null ) {
			this.up = CellComponents.WALL;
		} else {
			this.up = up;
		}
	}

	/**
     * Gets the component on the lower side of the cell.
     * @return The lower component
     */
	public CellComponents getDown() {
		return down;
	}

	/**
     * Sets the component on the lower side of the cell.
     * @param down The lower component to set
     */
	public void setDown(CellComponents down) {
		if (down == null ) {
			this.down = CellComponents.WALL;
		} else {
			this.down = down;
		}
	}

	/**
     * Returns a string representation of the cell.
     * @return String representation of the cell's components
     */
    @Override
    public String toString() {
        return "Cell [left=" + left + ", right=" + right + ", up=" + up + ", down=" + down + "]";
    }
}
