package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Utilitytool;
import main.collisioncheck;
import main.gamepanel;

public class Entity {
gamepanel gp;
public BufferedImage up1,up2,down1,down2,right1,right2,left1,left2;
public BufferedImage atkup1,atkup2,atkdown1,atkdown2,atkright1,atkright2,atkleft1,atkleft2;
public BufferedImage image1,image2,image3;
public Rectangle solidarea = new Rectangle(0,0,24,24);
public Rectangle attackarea = new Rectangle(0,0,0,0);
public int solidareadefaultx,solidareadefaulty;
public String dialogues[] = new String[200];
//state
public int worldx,worldy;
public String direction = "down";
public boolean collision;
int dialogueindex = 0;
public int spritenum = 1;
public boolean invincible = false;
public boolean attacking = false;
public boolean onPath = false;
public int var = 1;
//counters
public int spritecounter = 0;
public int invinciblecounter;
public int actionlockcounter;
public int shotavailablecounter = 0;
public int hpbarcounter;
int dyingcounter = 0;

//character attributes
public String name;
public int type;
public int maxlife;
public int life;
public int lvl;
public int exp;
public int speed;
public int attack;
public int defense;
public int nextlvlexp;
public boolean collisionon =  false;
public boolean dying = false;
public boolean alive = true;
boolean hpbaron = false;
public Projectiles projectile;
public Projectiles projectile2;

public void setDialogue() {

}
public Entity(gamepanel gp) {
	this.gp = gp;
}
public void setaction() {}
public void damagereaction() {};
public void speak() {
	if(gp.currentmap == 2 && var != 0) {
		dialogueindex = 0;
		dialogues[0]= "hello again ! ";
		dialogues[1]= "you grew stronger\n";
		dialogues[2]= "go to the table and press ENTER to refill your health\n";
		dialogues[3]= "take the door and defeat monsters\n";
		dialogues[4]= "take the door and defeat monsters\n";
		dialogues[5]= "take the door and defeat monsters\n";
		dialogues[6]= "take the door and defeat monsters\n"; 
		var = 0;
	}
	if(dialogues[dialogueindex] == null) {
		dialogueindex = 0;
	}
	gp.UI.currentDialogue = dialogues[dialogueindex];
	dialogueindex++;
	switch(gp.player.direction) {
	case "up":
		direction = "down";
		break;
	case "down":
		direction = "up";
		break;
	case "left":
		direction = "right";
		break;
	case "right":
		direction = "left";
		break;
	}
}
public void checkcollision() {
	collisionon = false;
	gp.cc.checktile(this);
	gp.cc.checkentity(this, gp.npc);
	gp.cc.checkentity(this, gp.mon);
	gp.cc.checkobj(this, false);
	boolean contactplayer = gp.cc.checkplayer(this);

	if(this.type == 2 && contactplayer == true) {
		if(gp.player.invincible == false) {
			int damage = attack - gp.player.defense;
			if(damage < 0 ) {
				damage = 0;
			}
			gp.player.life -= damage;
			if(gp.player.life <= 0) {
				gp.gameState = gp.gameoverstate;
			}
			gp.player.invincible = true;
		}
	}
}
public void update() {
	setaction();
	checkcollision();
	
	if(collisionon == false) {
		switch (direction) {
		case "up":worldy -= speed;break;	
		case "down":worldy += speed;break;
		case "right":worldx += speed;break;	
		case "left":worldx -= speed;break;			
		}
	}		
			spritecounter++;
	if(spritecounter>15) {
		if(spritenum==1) {
			spritenum = 2;
	    }	
		else if(spritenum==2) {
			spritenum = 1;
		}
		spritecounter=0;
	}
	//invincible
	if(invincible == true) {
		invinciblecounter++;
		if(invinciblecounter>20) {
			invincible = false;
			invinciblecounter = 0;
		}
	}
}


public void draw(Graphics2D g2) {
	BufferedImage image = null;

	int screenX = worldx - gp.player.worldx + gp.player.screenx;
	int screenY = worldy - gp.player.worldy + gp.player.screeny;

	if( worldx + gp.tilesize > gp.player.worldx - gp.player.screenx 
	&&  worldx - gp.tilesize < gp.player.worldx + gp.player.screenx 
	&&  worldy + gp.tilesize > gp.player.worldy-gp.player.screeny
	&&  worldy - gp.tilesize < gp.player.worldy + gp.player.screeny) {
	
			switch(direction) {
			case "up":   
				if(spritenum==1) {image = up1;}
				if(spritenum==2) {image = up2;}break;
			case "down": 
				if(spritenum==1) {image = down1;}		 
				if(spritenum==2) {image = down2;}break;
			case "right":
				if(spritenum==1) {image = right1;}
				if(spritenum==2) {image = right2;}break;
			case "left": 
				if(spritenum==1) {image = left1;}
				if(spritenum==2) {image = left2;}break;
			}
			
		//monster hp
		if(type == 2 && hpbaron == true) {
			double oneScale = (double)gp.tilesize/maxlife;
			double hpBarValue = oneScale*life;
			g2.setColor(new Color(35,35,35));
			g2.fillRect(screenX-1 , screenY-16,gp.tilesize , 12 );
			
		if(life<0) {
			hpBarValue = 0;
		}
			g2.setColor(Color.RED);
			g2.fillRect(screenX , screenY-15, (int)hpBarValue , 10 );
			hpbarcounter++;
			if(hpbarcounter>600) {
				hpbarcounter = 0;
				hpbaron = false;
			}
		}

		if(invincible == true) {
			hpbarcounter = 0;
			changealpha(g2,0.4f);
		}
		if(dying == true) {
			dyingAnimation(g2);
		}
			g2.drawImage(image , screenX , screenY , null);
		
			changealpha(g2,1f);
		}
	}
public void dyingAnimation(Graphics2D g2) {
	dyingcounter++;
	if(dyingcounter<=40) {
		if(dyingcounter%10!=0) {
			changealpha(g2,0f);
		}else {
			changealpha(g2,1f);
		}
	}else{
		alive=false;
	}
}
public void changealpha(Graphics2D g2,float alphavallue) {
	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER , alphavallue));

}

public BufferedImage setup(String imagepath , int width , int height) {
	
	Utilitytool utool = new Utilitytool();
	BufferedImage Image = null; 
	try {
	Image = ImageIO.read(getClass().getResourceAsStream(imagepath+".png"));
	Image = utool.scaleImage(Image, width , height);
}catch(IOException e) {
	e.printStackTrace();
}
	return Image;
}
public void searchPath(int goalCol,int goalRow) {
	int startcol = (worldx+solidarea.x)/gp.tilesize;
	int startrow = (worldy+solidarea.y)/gp.tilesize;

	gp.pFinder.setNodes(startcol, startrow, goalCol, goalRow);

	if(gp.pFinder.search() == true) {
		int nextx = gp.pFinder.pathList.get(0).col *gp.tilesize;
		int nexty = gp.pFinder.pathList.get(0).row *gp.tilesize;

		int enleftx = worldx + solidarea.x;
		int enrightx = worldx + solidarea.x + solidarea.width;
		int entopy = worldy + solidarea.y;
		int enbottomy = worldy + solidarea.y + solidarea.height;
		
		if( entopy > nexty && enleftx >= nextx && enrightx < nextx + gp.tilesize) {
			
			direction = "up";
			
		}else if( entopy < nexty && enleftx >= nextx && enrightx < nextx + gp.tilesize) {
			
			direction = "down";
			
		}else if( entopy >= nexty && enbottomy < nexty + gp.tilesize) {
			
			if(enleftx > nextx) {
				direction = "left";
			}
			if(enleftx < nextx) {
				direction = "right";
			}
			
		}else if(entopy > nexty && enleftx > nextx) {
			direction = "up";
			checkcollision();
			if(collisionon == true) {
				direction = "left";
			}
		}else if(entopy > nexty && enleftx < nextx) {
			direction = "up";
			checkcollision();
			if(collisionon == true) {
				direction = "right";
			}
		}else if(entopy < nexty && enleftx > nextx) {
			direction = "down";
			checkcollision();
			if(collisionon == true) {
				direction = "left";
			}
		}
		else if(entopy < nexty && enleftx < nextx) {
			direction = "down";
			checkcollision();
			if(collisionon == true) {
				direction = "right";
			}
		}
		int nextcol = gp.pFinder.pathList.get(0).col;
		int nextrow = gp.pFinder.pathList.get(0).row;
		if( nextcol == goalCol && nextrow == goalRow) {
			onPath = false;
		}
	}
}
}

