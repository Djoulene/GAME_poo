package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import ai.Pathfinder;
import entity.Entity;
import entity.Player;
import tiles.tilemanager;


public class gamepanel extends JPanel implements Runnable{

	//screen settings
	final int originaltilesize = 16; 
	final int scale = 3;
	public final int tilesize = originaltilesize * scale;
	public final int maxscreencol = 28;
	public final int maxscreenrow = 20;
	public final int screenwidth = maxscreencol * tilesize;
	public final int screenheight = maxscreenrow * tilesize;
	//world settings
	public final int maxworldcol = 50;
	public final int maxworldrow = 50;
	public final int worldwidth = tilesize * maxworldcol;
	public final int worldheight = tilesize * maxworldrow;
	public final int maxmap = 10;
	public int currentmap = 0;

	//system
	public tilemanager tilem = new tilemanager(this);
	public keyhandler keyh = new keyhandler(this);
	sound music = new sound();
	sound se = new sound();
	public collisioncheck cc = new collisioncheck(this);
    public assetsetter as = new assetsetter(this);
    public ui UI = new ui(this);
    public Eventhandler eventH = new Eventhandler(this);
	Thread gamethread;
	public Pathfinder pFinder = new Pathfinder(this);
	int fps = 60;

	
    //entity and objects
    
	public Player player = new Player(this , keyh);
	public Entity obj[][] = new Entity[maxmap][20];
	public Entity npc[][] = new Entity[maxmap][20];
	public Entity mon[][] = new Entity[maxmap][20];
	ArrayList<Entity> entitylist = new ArrayList<>();
	public ArrayList<Entity> projectilelist = new ArrayList<>();
	//GAME STATES
	public int gameState;
	
	public final int titlestate = 0;
	public final int playstate = 1;
	public final int pausestate = 2;
	public final int dialoguestate = 3;
	public final int characterstate = 4;
	public final int gameoverstate = 5;
	
public gamepanel() {
	this.setPreferredSize(new Dimension(screenwidth,screenheight));
	this.setBackground(Color.black);
	this.setDoubleBuffered(true);
	this.addKeyListener(keyh);
	this.setFocusable(true);
}
public void setupgame() {
	as.setmonster();
	as.setobject();
	as.setnpc();
	playmusic(0);
	stopmusic();
	gameState = titlestate;
}

public void startgamethread() {
	gamethread = new Thread(this);
	gamethread.start();
}
	
public void run() {
	double drawintervale = 1000000000/fps;
	double delta = 0;
	long lasttime = System.nanoTime();
	long currenttime;
	while(gamethread != null) {
		currenttime=System.nanoTime();
		delta += (currenttime - lasttime)/drawintervale;
		lasttime = currenttime;
		if(delta>=1) {
			update();
			repaint();
			delta--;
		}
		

	}
}
public void update() {
	player.update();
	if(gameState == playstate) {
		for(int i=0 ; i < npc[1].length ; i++) {
			if(npc[currentmap][i] != null) {
				npc[currentmap][i].update();
			}
		}
	

		for(int i=0 ; i<mon[1].length ; i++) {
			if(mon[currentmap][i] != null) {
				if(mon[currentmap][i].alive == true && mon[currentmap][i].dying==false) {
					mon[currentmap][i].update();
				}
				if(mon[currentmap][i].alive == false) {
					mon[currentmap][i] = null;
				}
			}
		}
		for(int i=0 ; i<projectilelist.size() ; i++) {
			if(projectilelist.get(i) != null) {
				if(projectilelist.get(i).alive == true) {
					projectilelist.get(i).update();
				}
				if(projectilelist.get(i).alive == false) {
					projectilelist.remove(i);
				}
			}
		}
	}
	if(gameState == pausestate) {

	}
}


public void paintComponent(Graphics g) {
	
	super.paintComponent(g);
	
	Graphics2D g2 = (Graphics2D)g;

	
	if(gameState == titlestate) {
		UI.draw(g2);
	}
	else {
		
		tilem.draw(g2);
	//add entities to the list
		
		entitylist.add(player);
		
		for(int i = 0 ; i<npc[1].length; i++) {
			if(npc[currentmap][i] != null) {
				entitylist.add(npc[currentmap][i]);
			}
		}
		for(int i = 0 ; i<obj[1].length; i++) {
			if(obj[currentmap][i] != null) {
				entitylist.add(obj[currentmap][i]);
			}
		}
		for(int i = 0 ; i<mon[1].length; i++) {
			if(mon[currentmap][i] != null) {
				entitylist.add(mon[currentmap][i]);
			}
		}
		for(int i = 0 ; i<projectilelist.size(); i++) {
			if(projectilelist.get(i) != null) {
				entitylist.add(projectilelist.get(i));
			}
		}
	
	//sorting
		Collections.sort(entitylist , new Comparator<Entity>(){
			@Override
			public int compare(Entity e1 , Entity e2) {
				int result = Integer.compare(e1.worldy, e2.worldy);
				return result;
			}
		});
	
	//draw entities
		for(int i= 0 ; i<entitylist.size() ; i++) {
			entitylist.get(i).draw(g2);
		}
	//empty entity list
	entitylist.clear();

		UI.draw(g2);
		
	}
	if(keyh.showdebugtext == true) {
		g2.setFont(new Font("Ariel",Font.PLAIN,20));
		g2.setColor(Color.WHITE);
		int x=10;
		int y=400;
		int lineheight=20;
		g2.drawString("worldy"+player.worldy, x, y);
		y+=lineheight;
		g2.drawString("worldx"+player.worldx, x, y);
		y+=lineheight;
		g2.drawString("col"+(player.worldx+ player.solidarea.x)/tilesize , x, y);
		y+=lineheight;
		g2.drawString("row"+(player.worldy+ player.solidarea.x)/tilesize, x, y);
		
	}
	g2.dispose();

	}



public void playmusic(int i) {
	music.setfile(i);
	music.play();
	music.loop();
}
public void stopmusic() {
	music.stop();
}
public void player(int i) {
	se.setfile(i);
	se.play();
	
}
}
