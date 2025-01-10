package ai;

public class Node {
	Node parent;
	public int col;
	public int row;
	public int Gcost;
	public int Fcost;
	public int Hcost;
	boolean solid;
	boolean open;
	boolean checked;
	
	
	public Node(int col, int row) {
		this.col = col;
		this.row = row;
	}
}
