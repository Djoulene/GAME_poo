package entity;

import main.gamepanel;

public class Projectiles extends Entity{
	Entity user;
	public Projectiles(gamepanel gp) {
		super(gp);
	}
	public void set(int worldx ,int worldy ,String direction ,boolean alive ,Entity user) {
		this.worldx = worldx;
		this.worldy = worldy;
		this.alive = alive;
		this.user = user;
		this.life = this.maxlife;
		this.direction = direction;
	}
	public void update() {
		
		if(user == gp.player) {
			int monsterindex = gp.cc.checkentity(this, gp.mon);
			if(monsterindex != 999) {
				gp.player.damagemonster(monsterindex, attack);
				alive = false;
			}
			
		}
		if(user != gp.player) {
			
		}
		
		
		switch (direction) {			
		case "up":worldy -= speed;break;	
		case "down":worldy += speed;break;
		case "right":worldx += speed;break;	
		case "left":worldx -= speed;break;			
		}
		
		life --;
		if(life < 0) {
			alive = false;
		}
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
	  }
		
	}

