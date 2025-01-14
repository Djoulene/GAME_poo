package object;

import entity.Projectiles;
import main.gamepanel;

public class objfireball extends Projectiles {
	gamepanel gp;
	public objfireball(gamepanel gp) {
		super(gp);
		this.gp = gp;
		name = "fireball";
		speed = 5;
		maxlife = 80;
		life = maxlife;
		attack = 2;
		alive = false;
		getimage();
	}
	public void getimage() {
		up1 = setup("/projectiles/fireball_up_1",gp.tilesize,gp.tilesize);
		up2 = setup("/projectiles/fireball_up_2",gp.tilesize,gp.tilesize);
		down1 = setup("/projectiles/fireball_down_1",gp.tilesize,gp.tilesize);
		down2 = setup("/projectiles/fireball_down_2",gp.tilesize,gp.tilesize);
		right1 = setup("/projectiles/fireball_right_1",gp.tilesize,gp.tilesize);
		right2 = setup("/projectiles/fireball_right_2",gp.tilesize,gp.tilesize);
		left1 = setup("/projectiles/fireball_left_1",gp.tilesize,gp.tilesize);
		left2 = setup("/projectiles/fireball_left_2",gp.tilesize,gp.tilesize);
	}
}
