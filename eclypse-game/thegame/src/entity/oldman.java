package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.gamepanel;

public class oldman extends Entity {
//oldman ;)
public oldman(gamepanel gp) {
	super(gp);
	direction = "down";
	speed = 0;
	
	solidarea = new Rectangle(3,30,42,42);

	
	getimage();
	setDialogue();
}
@Override
public void setDialogue() {
		dialogues[0]= "hello warrior ! welcome to the dungeon";
		dialogues[1]= "take the door and defeat monsters\n";
		dialogues[1]= "take the door and defeat monsters\n";
		dialogues[2]= "take the door and defeat monsters\n";
		dialogues[3]= "take the door and defeat monsters\n";
		dialogues[4]= "take the door and defeat monsters\n";
		dialogues[5]= "take the door and defeat monsters\n";
		dialogues[6]= "take the door and defeat monsters\n"; 

} 
	public void getimage() {
		up1 = setup("/npc/oldman_up_1",gp.tilesize,gp.tilesize);
		up2 = setup("/npc/oldman_up_1",gp.tilesize,gp.tilesize);
		down1 = setup("/npc/oldman_down_1",gp.tilesize,gp.tilesize);
		down2 = setup("/npc/oldman_down_1",gp.tilesize,gp.tilesize);
		right1 = setup("/npc/oldman_right_1",gp.tilesize,gp.tilesize);
		right2 = setup("/npc/oldman_right_1",gp.tilesize,gp.tilesize);
		left1 = setup("/npc/oldman_left_1",gp.tilesize,gp.tilesize);
		left2 = setup("/npc/oldman_left_1",gp.tilesize,gp.tilesize);

	}
public void setaction() {

}


public void speak() {
super.speak();
onPath = true;
System.out.println("onpath true");

}
}