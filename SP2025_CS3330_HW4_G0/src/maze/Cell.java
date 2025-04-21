package maze;

public class Cell {
	private CellComponents left,right,up,down;
	
	public Cell(CellComponents up, CellComponents down, CellComponents left, CellComponents right) {
		this.setLeft(left);
		this.setRight(right);
		this.setUp(up);
		this.setDown(down);
	}

	public CellComponents getLeft() {
		return left;
	}

	public void setLeft(CellComponents left) {
		this.left = left;
	}

	public CellComponents getRight() {
		return right;
	}

	public void setRight(CellComponents right) {
		this.right = right;
	}

	public CellComponents getUp() {
		return up;
	}

	public void setUp(CellComponents up) {
		this.up = up;
	}

	public CellComponents getDown() {
		return down;
	}

	public void setDown(CellComponents down) {
		this.down = down;
	}

}
