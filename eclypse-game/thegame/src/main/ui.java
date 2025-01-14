package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import entity.Entity;
import entity.Player;
import object.objheart;

public class ui {
	
	Graphics2D g2;
	gamepanel gp;
	Font arial_40, arial_80;
	public BufferedImage heart_full, heart_half,heart_blank;
	public boolean msgon=false;
	public String msg = "";
	int msgcount = 0;
	public boolean gamefinished = false;
	public String currentDialogue = "";
	public BufferedImage menuimage;
	public int commandnum = 0;
	public int titlescreenstate = 0;
	public ui(gamepanel gp) {
	this.gp = gp;
	arial_40 = new Font ("Arial", Font.PLAIN , 40);
	arial_80 = new Font ("Arial", Font.BOLD , 80);

	Entity heart = new objheart(gp);
	heart_full = heart.image1;
	heart_half = heart.image2;
	heart_blank = heart.image3;
}

public void showmsg(String txt) {
	msg = txt;
	msgon = true;
}

public void draw(Graphics2D g2) {

	this.g2 = g2;
	g2.setFont(arial_40);
	g2.setColor(Color.white);
	

	if(gp.playstate == gp.gameState) {
		drawplayerlife();
	}
	if(gp.titlestate == gp.gameState) {
		drawtitlescreen();
	}
	if(gp.gameState == gp.dialoguestate) {
		drawdialoguescreen();
		drawplayerlife();
	}
	if(gp.gameState == gp.characterstate) {
		drawcharacterscreen();
		drawplayerlife();
	}
	if(gp.gameState == gp.gameoverstate) {
		drawgameoverscreen();
		drawplayerlife();
	}
}
public void drawtitlescreen() {
	if(titlescreenstate == 0) {
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0,0,gp.screenwidth,gp.screenheight);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,96F));
		String text = "Shadow Adventures";
		int x = getXforcentredtxt(text);
		int y = gp.tilesize*3;
		g2.setColor(Color.GRAY);
		g2.drawString(text, x+3, y+3);
		
		g2.setColor(Color.WHITE);
		g2.drawString(text , x ,y);
		
		x = gp.screenwidth/2 - (gp.tilesize*2)/2;
		y += gp.tilesize*2;
		g2.drawImage(gp.player.down1, x, y, gp.tilesize*2, gp.tilesize*2,null);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD , 48F));
		text = "NEW GAME";
		x = getXforcentredtxt(text);
		y += gp.tilesize*3;
		g2.drawString(text, x, y);
		if(commandnum == 0) {
			g2.drawString(">", x-gp.tilesize, y);
		}
		text = "QUIT";
		x = getXforcentredtxt(text);
		y += gp.tilesize;
		g2.drawString(text, x, y);
		if(commandnum == 1) {
			g2.drawString(">", x-gp.tilesize, y);
		}
	}else
	if(titlescreenstate == 1) {

		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(42F));
		BufferedImage image;
		String text = "select your class :";
		int x = getXforcentredtxt(text);
		int y = gp.tilesize*3;
		g2.drawString(text,x,y);
		
		text = "warrior";
		x = getXforcentredtxt(text) + gp.tilesize*7;
		y += gp.tilesize*3;
		if(commandnum == 0) {
			g2.drawString(">", x-gp.tilesize, y);
			g2.setFont(g2.getFont().deriveFont(52F));
			g2.drawString(text,x,y);
		}else {
			g2.setFont(g2.getFont().deriveFont(42F));
			g2.drawString(text,x,y);
		}
		g2.setFont(g2.getFont().deriveFont(21F));
		
		g2.drawRoundRect(x-(gp.tilesize*3)/2, y+(gp.tilesize)/2, gp.tilesize*6, y+gp.tilesize*5, 25, 25);
		
		text = "-hp : 5 hearts";
		g2.drawString(text,x-(gp.tilesize),y+(gp.tilesize));
		text = "-attack : 4";
		g2.drawString(text,x-(gp.tilesize),y+(gp.tilesize)*2);
		text = "-defense : 2";
		g2.drawString(text,x-(gp.tilesize),y+(gp.tilesize)*3);
		text = "-speed : slow";
		g2.drawString(text,x-(gp.tilesize),y+(gp.tilesize)*4);
		text = "-magic : none";
		g2.drawString(text,x-(gp.tilesize),y+(gp.tilesize)*5);
		
		image = gp.player.setup("/knight/kngiht_down_1",gp.tilesize*2,gp.tilesize*2);
		g2.drawImage(image,x+20,y+(gp.tilesize)*7,null);

		text = "mage";
		g2.setFont(g2.getFont().deriveFont(42F));
		x = getXforcentredtxt(text);
		if(commandnum == 1) {
			g2.drawString(">", x-gp.tilesize, y);
			g2.setFont(g2.getFont().deriveFont(52F));
			g2.drawString(text,x,y);
		}else {
			g2.setFont(g2.getFont().deriveFont(42F));
			g2.drawString(text,x,y);
		}
		g2.setFont(g2.getFont().deriveFont(21F));
		
		g2.drawRoundRect(x-gp.tilesize*(5/2), y+(gp.tilesize)/2, gp.tilesize*6, y+gp.tilesize*5, 25, 25);

		text = "-hp : 3 hearts";
		g2.drawString(text,x-(gp.tilesize)*2+20,y+(gp.tilesize));
		text = "-attack : 0";
		g2.drawString(text,x-(gp.tilesize)*2+20,y+(gp.tilesize)*2);
		text = "-defense : 1";
		g2.drawString(text,x-(gp.tilesize)*2+20,y+(gp.tilesize)*3);
		text = "-speed : fast";
		g2.drawString(text,x-(gp.tilesize)*2+20,y+(gp.tilesize)*4);
		text = "-magic : fireball";
		g2.drawString(text,x-(gp.tilesize)*2+20,y+(gp.tilesize)*5);
		
		image = gp.player.setup("/mage/mage_down_1",gp.tilesize*2,gp.tilesize*2);
		g2.drawImage(image,x,y+(gp.tilesize)*7,null);

		g2.setFont(g2.getFont().deriveFont(42F));
		text = "assassin";
		x = getXforcentredtxt(text);
		if(commandnum == 2) {
			g2.drawString(">", x-gp.tilesize*8, y);
			g2.setFont(g2.getFont().deriveFont(52F));
			g2.drawString(text,x-gp.tilesize*7,y);
		}else {
			g2.setFont(g2.getFont().deriveFont(42F));
			g2.drawString(text,x-gp.tilesize*7,y);
		}
		g2.setFont(g2.getFont().deriveFont(21F));
		
		g2.drawRoundRect(x-gp.tilesize*9 + 20, y+(gp.tilesize)/2, gp.tilesize*6, y+gp.tilesize*5, 25, 25);

		text = "-hp : 4 hearts";
		g2.drawString(text,x-(gp.tilesize)*8+20,y+(gp.tilesize));
		text = "-attack : 3";
		g2.drawString(text,x-(gp.tilesize)*8+20,y+(gp.tilesize)*2);
		text = "-defense : 0";
		g2.drawString(text,x-(gp.tilesize)*8+20,y+(gp.tilesize)*3);
		text = "-speed : very fast";
		g2.drawString(text,x-(gp.tilesize)*8+20,y+(gp.tilesize)*4);
		text = "-magic : none";
		g2.drawString(text,x-(gp.tilesize)*8+20,y+(gp.tilesize)*5);
		
		image = gp.player.setup("/assassin/assassin_down_1",gp.tilesize*2,gp.tilesize*2);
		g2.drawImage(image,x-(gp.tilesize)*7+20,y+(gp.tilesize)*7,null);


	}
}
public void drawplayerlife(){
	int x = gp.tilesize/2;
	int y = gp.tilesize/2;
	int i = 0;
	while(i<gp.player.maxlife/2) {
		g2.drawImage(heart_blank,x,y,null);
		i++;
		x+=gp.tilesize;
	}
	x = gp.tilesize/2;
	i = 0;
	while(i<gp.player.life) {
		g2.drawImage(heart_half, x, y,null);
		i++;
		if(i<gp.player.life) {
			g2.drawImage(heart_full, x, y,null);
		}
		i++;
		x+=gp.tilesize;
	}
}
public void drawgameoverscreen() {
	g2.setColor(new Color(0,0,0,150));
	g2.fillRect(0, 0, gp.screenwidth, gp.screenheight);
	
	int x;
	int y;
	String text;
	g2.setFont(g2.getFont().deriveFont(Font.BOLD, 150f));
	
	text = "Game Over";
	
	g2.setColor(Color.black);
	x = getXforcentredtxt(text);
	y = gp.tilesize*4;
	g2.drawString(text, x, y);
	g2.setColor(Color.WHITE);
	g2.drawString(text, x-4, y-4);
	
	g2.setFont(g2.getFont().deriveFont(50f));

	text = "Retry";
	x = getXforcentredtxt(text);
	y += gp.tilesize*4;
	g2.drawString(text, x, y);
	g2.drawString(">", x - 40, y);

}
public void drawdialoguescreen() {
	int x = gp.tilesize;
	int y = gp.tilesize*2;
	int width = gp.screenwidth - (gp.tilesize*2);
	int height = gp.tilesize*3;
	
	drawsubwindow(x,y,width,height);
	x += gp.tilesize;
	y += gp.tilesize;
	for(String line : currentDialogue.split("\n")) {
		g2.drawString(line,x,y);
		y += 40;
	}
}
public void drawsubwindow(int x, int y , int width , int height) {
	Color c = new Color(0,0,0,220);
	g2.setColor(c);
	g2.fillRoundRect(x, y, width, height, 25, 25);
	c = new Color(255,255,255);
	g2.setStroke(new BasicStroke(5));
	g2.setColor(c);
	g2.drawRoundRect(x+5, y+5, width-10, height-10, 15, 15);
}
public void drawPauseScreen() {
String text = "PAUSED";
int x = getXforcentredtxt(text);
int y = gp.screenheight/2;

g2.drawString(text, x, y);
}
public void drawcharacterscreen(){
	
	final int framex = gp.tilesize*2;
	final int framey = gp.tilesize*2;
	final int framewidth = gp.tilesize*7;
	final int frameheight = gp.tilesize*7;
	
	drawsubwindow(framex , framey , framewidth , frameheight);
	g2.setColor(Color.white);
	g2.setFont(g2.getFont().deriveFont(28F));
	int textx = framex +20;
	int texty = framey + gp.tilesize;
	final int lineheight = 32;
	g2.drawString("level : ", textx , texty );
	texty += lineheight;
	g2.drawString("next level :", textx , texty );
	texty += lineheight;
	g2.drawString("hitpoints :" , textx , texty );
	texty += lineheight;
	g2.drawString("attack : " , textx , texty );
	texty += lineheight;
	g2.drawString("defense : " , textx , texty );
	texty += lineheight;
	g2.drawString("speed : " , textx , texty );
	texty += lineheight;

	
	String vallue;
	int tailx = (framex + framewidth) -30;
	
	texty = framey + gp.tilesize;
	vallue = String.valueOf(gp.player.lvl);
	textx = getXforalligntext(vallue , tailx);
	g2.drawString(vallue, textx , texty);
	
	texty += lineheight;
	vallue = String.valueOf(gp.player.exp+"/"+gp.player.nextlvlexp);
	textx = getXforalligntext(vallue , tailx);
	g2.drawString(vallue, textx , texty);
	
	texty += lineheight;
	vallue = String.valueOf(gp.player.life+"/"+gp.player.maxlife);
	textx = getXforalligntext(vallue , tailx);
	g2.drawString(vallue, textx , texty);
	
	texty += lineheight;
	vallue = String.valueOf(+gp.player.attack);
	textx = getXforalligntext(vallue , tailx);
	g2.drawString(vallue, textx , texty);
	
	texty += lineheight;
	vallue = String.valueOf(+gp.player.defense);
	textx = getXforalligntext(vallue , tailx);
	g2.drawString(vallue, textx , texty);
	
	texty += lineheight;
	vallue = String.valueOf(gp.player.speed);
	textx = getXforalligntext(vallue , tailx);
	g2.drawString(vallue, textx , texty);
	
	
	g2.drawImage(gp.player.down1,tailx-gp.tilesize,texty+20,null);
	g2.dispose();
}

public int getXforcentredtxt(String text) {

	int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
	int x = gp.screenwidth/2 - length/2;
	return x;
}

public int getXforalligntext(String text , int tailx) {

	int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
	int x = tailx - length;
	return x;
}
}
