package entity;

import main.gamepanel;
import main.keyhandler;
import object.objfireball;

public class Mage extends Player{
	public Mage(gamepanel gp, keyhandler keyh) {
		super(gp, keyh);
	}
	@Override
	public void setdefaultvalues() {	
		speed = 4;
		attack = 1; 
		defense = 1;
		exp = 0;
		nextlvlexp = 5;
		maxlife = 6;
		lvl = 1;
		maxlife = 6;
		life = maxlife;
		projectile = new objfireball(gp);
	}

	public void getplayerimage() 
	{
		up1 = setup("/mage/mage_up_1",gp.tilesize,gp.tilesize);
		up2 = setup("/mage/mage_up_2",gp.tilesize,gp.tilesize);
		down1 = setup("/mage/mage_down_1",gp.tilesize,gp.tilesize);
		down2 = setup("/mage/mage_down_2",gp.tilesize,gp.tilesize);
		right1 = setup("/mage/mage_right_1",gp.tilesize,gp.tilesize);
		right2 = setup("/mage/mage_right_2",gp.tilesize,gp.tilesize);
		left1 = setup("/mage/mage_left_1",gp.tilesize,gp.tilesize);
		left2 = setup("/mage/mage_left_2",gp.tilesize,gp.tilesize);
		

		atkup1 = setup("/mage/mage_attack_up_1", gp.tilesize, gp.tilesize * 2);
		atkup2 = setup("/mage/mage_attack_up_2", gp.tilesize, gp.tilesize * 2);
		atkdown1 = setup("/mage/mage_attack_down_1", gp.tilesize, gp.tilesize * 2);
		atkdown2 = setup("/mage/mage_attack_down_2", gp.tilesize, gp.tilesize * 2);
		atkright1 = setup("/mage/mage_attack_right_1", gp.tilesize * 2, gp.tilesize);
		atkright2 = setup("/mage/mage_attack_right_2", gp.tilesize * 2, gp.tilesize);
		atkleft1 = setup("/mage/mage_attack_left_1", gp.tilesize * 2, gp.tilesize);
		atkleft2 = setup("/mage/mage_attack_left_2", gp.tilesize * 2, gp.tilesize);
	}
	
	
	
}
