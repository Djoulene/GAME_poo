package entity;

import main.gamepanel;
import main.keyhandler;
import object.objfireball;

public class Warrior extends Player{
	public Warrior(gamepanel gp, keyhandler keyh) {
		super(gp, keyh);
	}
	
	public void setdefaultvalues() {	
		
		speed = 3;
		attack = 4;
		defense = 2;
		exp = 0;
		nextlvlexp = 5;
		lvl = 1;
		maxlife = 10;
		life = maxlife;
		projectile = new objfireball(gp);
	}

	public void getplayerimage() 
	{
		up1 = setup("/knight/kngiht_up_1",gp.tilesize,gp.tilesize);
		up2 = setup("/knight/kngiht_up_2",gp.tilesize,gp.tilesize);
		down1 = setup("/knight/kngiht_down_1",gp.tilesize,gp.tilesize);
		down2 = setup("/knight/kngiht_down_2",gp.tilesize,gp.tilesize);
		right1 = setup("/knight/kngiht_right_1",gp.tilesize,gp.tilesize);
		right2 = setup("/knight/kngiht_right_2",gp.tilesize,gp.tilesize);
		left1 = setup("/knight/kngiht_left_1",gp.tilesize,gp.tilesize);
		left2 = setup("/knight/kngiht_left_2",gp.tilesize,gp.tilesize);
		
		atkup1 = setup("/knight/kngiht_attack_up_1", gp.tilesize, gp.tilesize * 2);
		atkup2 = setup("/knight/kngiht_attack_up_2", gp.tilesize, gp.tilesize * 2);
		atkdown1 = setup("/knight/kngiht_attack_down_1", gp.tilesize, gp.tilesize * 2);
		atkdown2 = setup("/knight/kngiht_attack_down_2", gp.tilesize, gp.tilesize * 2);

		atkright1 = setup("/knight/kngiht_attack_right_1", gp.tilesize * 2, gp.tilesize);
		atkright2 = setup("/knight/knight_attack_right_2", gp.tilesize * 2, gp.tilesize);

		atkleft1 = setup("/knight/knight_attack_left_1", gp.tilesize * 2, gp.tilesize);
		atkleft2 = setup("/knight/kngiht_attack_left_2", gp.tilesize * 2, gp.tilesize);

	}

}
