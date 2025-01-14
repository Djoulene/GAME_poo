package monster;

import java.awt.Rectangle;
import java.util.Random;
import entity.Entity;

import main.gamepanel;

public class Bat extends Entity{
	gamepanel gp;
public Bat(gamepanel gp) {
		super(gp);
		this.gp = gp;
		type = 2;
		name = "bat";
		speed = 3;
		maxlife = 8;
		attack = 3;
		defense = 0;
		exp = 10;
		life = maxlife;
		solidarea = new Rectangle(3,18,42,30);
		solidareadefaultx = solidarea.x;
		solidareadefaulty = solidarea.y;
		getimage();
	}
public void getimage() {
	up1 = setup("/monster/bat_down_1",gp.tilesize,gp.tilesize);
	up2 = setup("/monster/bat_down_2",gp.tilesize,gp.tilesize);
	down1 = setup("/monster/bat_down_1",gp.tilesize,gp.tilesize);
	down2 = setup("/monster/bat_down_2",gp.tilesize,gp.tilesize);
	right1 = setup("/monster/bat_down_1",gp.tilesize,gp.tilesize);
	right2 = setup("/monster/bat_down_2",gp.tilesize,gp.tilesize);
	left1 = setup("/monster/bat_down_1",gp.tilesize,gp.tilesize);
	left2 = setup("/monster/bat_down_2",gp.tilesize,gp.tilesize);
}
@Override
public void damagereaction() {
	actionlockcounter = 0;
	onPath = true;
}
public void update() {
	super.update();
	int xdistance = Math.abs(worldx - gp.player.worldx);
	int ydistance = Math.abs(worldy - gp.player.worldy);
	
	int tiledistance = (xdistance + ydistance)/gp.tilesize;
	if(onPath == false && tiledistance<10) {
		int i = new Random().nextInt(100)+1;
		if(i>50){
			onPath = true;
		}
	}
	if(onPath == true && tiledistance>5) {
		onPath = false;
	}
}
public void setaction() {
	if(onPath == true) {
		int goalcol = (gp.player.worldx + gp.player.solidarea.x)/gp.tilesize;
		int goalrow = (gp.player.worldy + gp.player.solidarea.y)/gp.tilesize;
		
		
		searchPath(goalcol,goalrow);
		
	}else {
		actionlockcounter++;

		if(actionlockcounter == 120) {


			Random random = new Random();
			int i = random.nextInt(100)+1;
			if(i>25 && i<=50) {
				direction = "down";
			}
			if(i>0 && i<=25) {
				direction = "right";
			}
			if(i>50 && i<=75) {
				direction = "left";
			}
			if(i>75 && i<=100) {
				direction = "up";
		}
			actionlockcounter = 0;
		}
	}
}
}
