package maze;

public class Row {
	private Cell[] row;
	
	public Row(int size) {
		this.setRow(new Cell[size]);
	}

	public Cell[] getRow() {
		return row;
	}
	
	public Cell getCellAtIndex(int index) {
		return this.row[index];
	}

	public void setRow(Cell[] row) {
		this.row = row;
	}

}
