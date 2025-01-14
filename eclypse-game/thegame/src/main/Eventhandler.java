package main;


public class Eventhandler {
gamepanel gp;
EventRect eventrect[][][];
int previousEventx , previousEventy;
boolean cantouchevent = true;
public Eventhandler(gamepanel gp) {
	this.gp = gp;
	
	eventrect = new EventRect[gp.maxmap][gp.maxworldcol][gp.maxworldrow];
	
	int col = 0;
	int row = 0;
	int map = 0;
	while(col<gp.maxworldcol && row<gp.maxworldrow && map < gp.maxmap) {
		eventrect[map][col][row] = new EventRect();
		eventrect[map][col][row].x = 23;
		eventrect[map][col][row].y = 23;
		eventrect[map][col][row].width = 2;
		eventrect[map][col][row].height = 2;
		eventrect[map][col][row].eventRectDefaultX = eventrect[map][col][row].x;
		eventrect[map][col][row].eventRectDefaultY = eventrect[map][col][row].y;
		col++;
		if(col == gp.maxworldcol) {
			col = 0;
			row++;
			if(row == gp.maxworldrow) {
				row = 0;
				map++;
			}
		}
	}
}


public boolean hit(int map, int col, int row, String reqDirection) {
	boolean hit = false;
	if(map == gp.currentmap) {
		gp.player.solidarea.x = gp.player.worldx + gp.player.solidarea.x;
		gp.player.solidarea.y = gp.player.worldy + gp.player.solidarea.y;
		eventrect[map][col][row].x = col*gp.tilesize + eventrect[map][col][row].x;
		eventrect[map][col][row].y = row*gp.tilesize + eventrect[map][col][row].y;
		
		if(gp.player.solidarea.intersects(eventrect[map][col][row]) && eventrect[map][col][row].eventDone ==false) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
				previousEventx = gp.player.worldx;
				previousEventy = gp.player.worldy;
			}
		}
		gp.player.solidarea.x = gp.player.solidareadefaultx;
		gp.player.solidarea.y = gp.player.solidareadefaulty;
		eventrect[map][col][row].x = eventrect[map][col][row].eventRectDefaultX;
		eventrect[map][col][row].y = eventrect[map][col][row].eventRectDefaultY;
	}
	return hit;
}
public void checkevent() {
	//cehck position of player compared to last event
	int xdistance = Math.abs(gp.player.worldx - previousEventx);
	int ydistance = Math.abs(gp.player.worldy - previousEventy);
	int distance = Math.max(xdistance, ydistance);
	
	if(distance > gp.tilesize) {
		cantouchevent = true;
	}
	
	if(cantouchevent == true) {
		if(hit(0,25,21,"any") == true) {teleport(1,24,48);gp.player(0);}
		else if(hit(0,26,21,"any") == true) {teleport(1,25,48);gp.player(0);}
		else if(hit(1,39,35,"any") == true) {teleport(2,12,24);gp.player(0);}
		else if(hit(2,34,25,"any") == true) {teleport(3,10,21);gp.player(0);}
		else if(hit(2,34,24,"any") == true) {teleport(3,10,22);gp.player(0);}
		else if(hit(3,41,28,"any") == true) {win(4,38,9);gp.player(0);}
		else if(hit(3,41,27,"any") == true) {win(4,38,9);gp.player(0);}
	}
	if(hit(2,15,28,"any") == true && gp.keyh.enterpressed == true) {healpit(gp.dialoguestate);}
	if(hit(2,16,28,"any") == true && gp.keyh.enterpressed == true) {healpit(gp.dialoguestate);}

}

private void healpit(int gamestate) {
	gp.gameState = gamestate;
	gp.player.attackcanceled = true;
	if(gp.player.life<gp.player.maxlife) {	
		gp.UI.currentDialogue = "you ate food \nyou have replenished yr health !";
		gp.player.life +=1; 
	}else {
		gp.UI.currentDialogue = "you can't eat more \nyour health is already full";
	}
	gp.keyh.enterpressed = false;
	//eventrect[col][row].eventDone = true; one time only
}

public void teleport(int map, int col,int row){
	gp.currentmap = map;
	gp.player.worldx = gp.tilesize * col;
	gp.player.worldy = gp.tilesize * row;
	previousEventx = gp.player.worldx;
	previousEventy = gp.player.worldy;
	cantouchevent = false;
}
public void win(int map, int col, int row) {
	gp.gameState = gp.dialoguestate;
	gp.UI.currentDialogue = "you have escaped the dungeon !";
	gp.player.life = gp.player.maxlife;
	teleport(map,col,row);
}
}
