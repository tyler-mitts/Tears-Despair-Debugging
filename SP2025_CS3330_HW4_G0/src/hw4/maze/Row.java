package hw4.maze;

public class Row {
	private Cell[] row;
	
	//Creates an array of cells for each row
	public Row(int size) {
		this.setRow(new Cell[size]);
	}

	public Cell[] getRow() {
		return row;
	}
	
	//Gets the cell at the index of row
	public Cell getCellAtIndex(int index) {
		return this.row[index];
	}

	public void setRow(Cell[] row) {
		this.row = row;
	}

}
