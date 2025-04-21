package maze;

public class Grid {
	private Row[] rows;
	
	//Creates an array of rows
	public Grid(Row[] rows) {
		this.rows = rows;
	}

	public Row[] getRows() {
		return rows;
	}
}
