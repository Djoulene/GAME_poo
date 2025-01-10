package ai;

import java.util.ArrayList;

import entity.Entity;
import main.gamepanel;

public class Pathfinder {
	gamepanel gp;
	Node[][] node;
	public ArrayList<Node> pathList = new ArrayList<>();
	public ArrayList<Node> openList = new ArrayList<>();
	Node startNode , goalNode, currentNode;
	boolean goalreached = false;
	int step = 0;
	
	public Pathfinder(gamepanel gp) {
		this.gp = gp;
		instantiateNodes();
		
	}
	public void instantiateNodes() {
		node = new Node[gp.maxworldcol][gp.maxworldrow];
		
		int col = 0;
		int row = 0;
		while(col < gp.maxworldcol && row < gp.maxworldrow) {
			node[col][row] = new Node(col,row);
			
			col++;
			if(col == gp.maxworldcol) {
				col = 0;
				row++;
			}
		}
	}
	public void resetNodes() {
		int col = 0;
		int row = 0;

		while(col < gp.maxworldcol && row < gp.maxworldrow) {
			node[col][row].open = false;
			node[col][row].checked = false;
			node[col][row].solid = false;

			col++;
			if(col == gp.maxworldcol) {
				col = 0;
				row++;
			}
		}
		openList.clear();
		pathList.clear();
		goalreached = false;
		step = 0;
	}
	public void setNodes(int startcol ,int startrow ,int goalcol ,int goalrow) {
		resetNodes();

		startNode = node[startcol][startrow];
		currentNode = startNode;
		goalNode = node[goalcol][goalrow];
		openList.add(currentNode);
		
		int col = 0;
		int row = 0;
		while(col < gp.maxworldcol && row < gp.maxworldrow) {
			int tilenum = gp.tilem.maptilenum[gp.currentmap][col][row];
			if(gp.tilem.tiles[tilenum].collision == true) {
				node[col][row].solid = true;
			}
		
		getCost(node[col][row]);
		
		col++;
		if(col == gp.maxworldcol) {
			col = 0;
			row++;
		}
		}
	}
	
	public void getCost(Node node) {
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.Gcost = xDistance + yDistance;
		
		xDistance = Math.abs(node.col - goalNode.col);
		yDistance = Math.abs(node.row - goalNode.row);
		node.Hcost = xDistance + yDistance;
		
		node.Fcost = node.Gcost + node.Hcost;
	}
	
	public boolean search() {
		System.out.println("goalrow = "+goalNode.row );
		System.out.println("goalcol = "+goalNode.col );
		while(goalreached == false && step < 500) {
			
			int col = currentNode.col;
			int row = currentNode.row;

			currentNode.checked = true;
			openList.remove(currentNode);
			
			if(row-1 >= 0) {
				openNode(node[col][row-1]);
			}
			if(col-1 >= 0) {
				openNode(node[col-1][row]);
			}
			if(row+1 < gp.maxworldrow) {
				openNode(node[col][row+1]);
			}
			if(col+1 < gp.maxworldcol) {
				openNode(node[col+1][row]);
			}
		
		int bestNodeIndex = 0;
		int bestNodefcost = 999;
		for(int i = 0 ; i < openList.size(); i++) {
			if(openList.get(i).Fcost < bestNodefcost) {
				bestNodeIndex = i;
				System.out.println("index = "+bestNodeIndex );

				bestNodefcost = openList.get(i).Fcost;
			}else if(openList.get(i).Fcost == bestNodefcost) {
					if(openList.get(i).Gcost < openList.get(bestNodeIndex).Gcost) {
						bestNodeIndex = i;
					}
				}
		}
		System.out.println("row = "+currentNode.row );
		System.out.println("col = "+currentNode.col );
		if(openList.size() == 0) {
			System.out.println("ended?");
			break;

		}
		
		currentNode = openList.get(bestNodeIndex);


		if(currentNode.col == goalNode.col && currentNode.row == goalNode.row) {
			System.out.println("1");

			goalreached = true;
			trackpath();

		}
		
		step++;
		System.out.println("step = "+step );

		}

		return goalreached;

	}
	public void trackpath() {
		Node current = goalNode;
		while(current != startNode) {
			pathList.add(0, current);
			current = current.parent;
		}
	}
	public void openNode(Node node) {
		if(node.open == false && node.checked == false && node.solid == false) {
			node.open = true;
			node.parent = currentNode;
			openList.add(node);		
		}
	}
	

	
	}
