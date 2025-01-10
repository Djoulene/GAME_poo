package entity;

import main.gamepanel;
import main.keyhandler;
import object.objfireball;

public class Assassin extends Player{
	public Assassin(gamepanel gp, keyhandler keyh) {
		super(gp, keyh);
	}
	@Override
	public void setdefaultvalues() {	

		speed = 6;
		attack = 2;
		defense = 0;
		exp = 0;
		nextlvlexp = 5;
		lvl = 1;
		maxlife = 8;
		life = maxlife;
		projectile = new objfireball(gp);
	}

	public void getplayerimage() 
	{
		up1 = setup("/assassin/assassin_up_1",gp.tilesize,gp.tilesize);
		up2 = setup("/assassin/assassin_up_2",gp.tilesize,gp.tilesize);
		down1 = setup("/assassin/assassin_down_1",gp.tilesize,gp.tilesize);
		down2 = setup("/assassin/assassin_down_2",gp.tilesize,gp.tilesize);
		right1 = setup("/assassin/assassin_right_1",gp.tilesize,gp.tilesize);
		right2 = setup("/assassin/assassin_right_2",gp.tilesize,gp.tilesize);
		left1 = setup("/assassin/assassin_left_1",gp.tilesize,gp.tilesize);
		left2 = setup("/assassin/assassin_left_2",gp.tilesize,gp.tilesize);
		
		atkup1 = setup("/assassin/assassin_attack_up_1", gp.tilesize, gp.tilesize * 2);
		atkup2 = setup("/assassin/assassin_attack_up_2", gp.tilesize, gp.tilesize * 2);
		atkdown1 = setup("/assassin/assassin_attack_down_1", gp.tilesize, gp.tilesize * 2);
		atkdown2 = setup("/assassin/assassin_attack_down_2", gp.tilesize, gp.tilesize * 2);
		atkright1 = setup("/assassin/assassin_attack_right_1", gp.tilesize * 2, gp.tilesize);
		atkright2 = setup("/assassin/assassin_attack_right_2", gp.tilesize * 2, gp.tilesize);
		atkleft1 = setup("/assassin/assassin_attack_left_1", gp.tilesize * 2, gp.tilesize);
		atkleft2 = setup("/assassin/assassin_attack_left_2", gp.tilesize * 2, gp.tilesize);

	}
}
