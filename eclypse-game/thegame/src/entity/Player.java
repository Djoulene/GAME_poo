package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Utilitytool;
import main.gamepanel;
import main.keyhandler;
import main.sound;
import object.objfireball;

public class Player extends Entity {
	
public keyhandler keyh;

public final int screenx;
public final int screeny;
int standcounter;
public boolean attackcanceled = false;
sound se = new sound();

public Player(gamepanel gp , keyhandler keyh) {
	super(gp);
	this.keyh = keyh;	
screenx = gp.screenwidth/2 - (gp.tilesize/2);
screeny = gp.screenheight/2 - (gp.tilesize/2);
attackarea = new Rectangle(0,0, 36,36);
solidarea = new Rectangle(10,20,32,32);	
solidareadefaultx = solidarea.x;
solidareadefaulty = solidarea.y;	
}
public void setdefaultposition(){
	worldx = gp.tilesize * 25;
	worldy = gp.tilesize * 25;
	gp.currentmap = 0;
	direction = "down";
	invincible = false;
	life = maxlife;
       
}

public void setdefaultvalues() {	
	speed = 4;
	attack = 4;
	defense = 1;
	exp = 0;
	nextlvlexp = 5;
	lvl = 1;
	maxlife = 10;
	life = maxlife;
}

public void getplayerimage() {}

public void update() {

	if(attacking == true) {
			attacking();

	}else
	if(keyh.uppressed == true || keyh.downpressed == true ||
			keyh.leftpressed ==true || keyh.rightpressed ==true
			||keyh.enterpressed == true) {
		
		if(keyh.uppressed) {
			direction = "up";
			//worldy -= speed;
		}
		if(keyh.downpressed) {
			direction = "down";
			//worldy += speed;
		}
		if(keyh.leftpressed) {
			direction = "left";
			//worldx -= speed;
		}
		if(keyh.rightpressed) {
			direction = "right";
			//worldx += speed;
		}
		
		
		//check tile collision
		collisionon = false;
		gp.cc.checktile(this);
		//check obj collision
		int objindex = gp.cc.checkobj(this,true);
		pickupobj(objindex);
		//check npc collision
		int npcindex = gp.cc.checkentity(this, gp.npc);
		interactnpc(npcindex);
		//check monster collision
		int monsterindex = gp.cc.checkentity(this, gp.mon);
		contactmonster(monsterindex);
		//check event
		gp.eventH.checkevent();
		
		//if collision is false player don't move
		if(collisionon == false && keyh.enterpressed == false) {
			
			switch (direction) {			
			case "up":worldy -= speed;break;	
			case "down":worldy += speed;break;
			case "right":worldx += speed;break;	
			case "left":worldx -= speed;break;			
			}
		}
		if(keyh.enterpressed == true && attackcanceled == false) {
			attacking = true;
			spritecounter = 0;
		}
		
		attackcanceled = false;
		keyh.enterpressed = false;

		spritecounter++;
		if(spritecounter>12) {
			if(spritenum==1) {
				spritenum = 2;
		    }	
			else if(spritenum==2) {
				spritenum = 1;
			}
			spritecounter=0;
		}
	  }else {
		standcounter ++;
		if( standcounter == 20) {
			spritenum = 1;
			standcounter = 0;
		}
	}
	if(gp.keyh.shotingkey == true && projectile.alive == false && shotavailablecounter == 30 && this instanceof Mage) {
		projectile.set(worldx , worldy , direction , true, this);
		gp.projectilelist.add(projectile);
		shotavailablecounter = 0;
	}


	//invincible
	if(invincible == true) {
		invinciblecounter++;
		if(invinciblecounter>60) {
			invincible = false;
			invinciblecounter = 0;
		}
	}
	if(shotavailablecounter <30) {
		shotavailablecounter++;
	}

}



public void attacking() {
	spritecounter++;

	if(spritecounter<=5) {
		spritenum = 1;
	}
	if(spritecounter >5 && spritecounter <=25) {
		spritenum = 2;
		//temp for saving
		int currentworldx = worldx;
		int currentworldy = worldy;
		int solidareawidth = solidarea.width;
		int solidareaheight = solidarea.height;
		
		switch (direction) {			
		case "up":worldy -= attackarea.height;break;	
		case "down":worldy += attackarea.height;break;
		case "right":worldx += attackarea.width;break;	
		case "left":worldx -= attackarea.width;break;			
		}
		solidarea.width = attackarea.width;
		solidarea.height = attackarea.height;
		
		int monsterindex = gp.cc.checkentity(this,gp.mon);
		damagemonster(monsterindex,attack);
		worldx = currentworldx;
		worldy = currentworldy;
		solidarea.width = solidareawidth;
		solidarea.height = solidareaheight;
	}
	if(spritecounter>25) {
		spritenum = 1;
		spritecounter = 0;
		attacking = false;
	}

}


public void pickupobj(int i) {
	if(i != 999) {
		
		}
	}

public void interactnpc(int index) {
	if(gp.keyh.enterpressed == true){
		if(index!=999) {
			attackcanceled = true;
				gp.gameState = gp.dialoguestate;
				gp.npc[gp.currentmap][index].speak();
				gp.player(4);
		}
		}
	}

public void contactmonster(int i) {
	if(i !=999) {
		if(invincible == false && gp.mon[gp.currentmap][i].dying == false) {
			int damage =gp.mon[gp.currentmap][i].attack - defense;
			if(damage < 0 ) {
				damage = 0;
			}

			life -= damage;
			invincible = true;
			gp.mon[gp.currentmap][i].hpbaron = true;

		}
		if(life <= 0) {
			gp.gameState = gp.gameoverstate;
		}
	}
}

public void damagemonster(int i, int attack) {
	if(i !=999) {
		int damage = attack - gp.mon[gp.currentmap][i].defense;

		if(damage < 0 ) {
			damage = 0;
		}
		if(gp.mon[gp.currentmap][i].invincible == false) {
			gp.mon[gp.currentmap][i].life -= damage;
			gp.mon[gp.currentmap][i].invincible = true;
			gp.mon[gp.currentmap][i].damagereaction();
			gp.player(2);
			if(gp.mon[gp.currentmap][i].life<=0) {
				gp.mon[gp.currentmap][i].dying = true;
				exp += gp.mon[gp.currentmap][i].exp;
				checklvlup();
			}
			gp.mon[gp.currentmap][i].hpbaron = true;
		}
	}
}

public void checklvlup() {
	if(exp >= nextlvlexp) {
		gp.player(3);
		lvl++;
		nextlvlexp = nextlvlexp*2;
		attack++;
		if(lvl % 2 == 0) {
			maxlife = maxlife+2;
			life = life+2;
		}
		
	gp.gameState = gp.dialoguestate;
	gp.UI.currentDialogue = "you are level "+lvl+" now ! next lvl at : "+nextlvlexp+" exp";
	exp = 0;
	}
}

public void draw(Graphics2D g2) {

	BufferedImage image = null;
	int tempscreenx = screenx;
	int tempscreeny = screeny;

	switch(direction) {
	case "up":
		if(attacking == false) {
			if(spritenum==1) {image = up1;}
			if(spritenum==2) {image = up2;}
		}
		if(attacking == true) {
			tempscreeny = screeny -gp.tilesize;
			if(spritenum==1) {image = atkup1;}
			if(spritenum==2) {image = atkup2;}
		}
		break;
	case "down":
		if(attacking == false) {
			if(spritenum==1) {image = down1;}
			if(spritenum==2) {image = down2;}
		}
		if(attacking == true) {
			if(spritenum==1) {image = atkdown1;}
			if(spritenum==2) {image = atkdown2;}
		}
		break;
	case "right":
		if(attacking == false) {
			if(spritenum==1) {image = right1;}
			if(spritenum==2) {image = right2;}
		}
		if(attacking == true) {
			if(spritenum==1) {image = atkright1;}
			if(spritenum==2) {image = atkright2;}
		}
		break;
	case "left":
		if(attacking == false) {
			if(spritenum==1) {image = left1;}
			if(spritenum==2) {image = left2;}
		}
		if(attacking == true) {
			tempscreenx = screenx -gp.tilesize;
			if(spritenum==1) {image = atkleft1;}
			if(spritenum==2) {image = atkleft2;}
			System.out.printf(""+atkleft1.getWidth());

		}
		break;
		
	}
	if( invincible == true) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER , 0.3f));
	}
	g2.drawImage(image, tempscreenx, tempscreeny,image.getWidth(),image.getHeight(), null);
	
	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER , 1f));
}

}