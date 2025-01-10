package main;

import entity.Entity;
import entity.oldman;
import monster.Bat;
import monster.Monslime;
import monster.Redslime;


public class assetsetter {
	gamepanel gp;
	public assetsetter (gamepanel gp) {
		this.gp = gp;
	}
	public void setobject() {
	}
	public void setnpc() {
		int mapnum = 0;
		int i=0;

		gp.npc[mapnum][i]=new oldman(gp);
		setternpc(mapnum,i,23,23);
		mapnum = 2;
		i++;
		gp.npc[mapnum][i]=new oldman(gp);
		setternpc(mapnum,i,27,25);
	}
	public void setmonster() {
		int mapnum = 1;
		int i = 0;
		
		gp.mon[mapnum][i]=new Monslime(gp);
		settermon(mapnum,i,23,32);
		i++;
		
		gp.mon[mapnum][i]=new Monslime(gp);
		settermon(mapnum,i,17,44);		
		i++;

		gp.mon[mapnum][i]=new Monslime(gp);
		settermon(mapnum,i,36,36);
		
		mapnum = 3;
		i++;

		gp.mon[mapnum][i]=new Monslime(gp);
		settermon(mapnum,i,39,17);
		i++;

		gp.mon[mapnum][i]=new Redslime(gp);
		settermon(mapnum,i,36,20);
		i++;

		gp.mon[mapnum][i]=new Redslime(gp);
		settermon(mapnum,i,24,15);
		i++;

		gp.mon[mapnum][i]=new Bat(gp);
		settermon(mapnum,i,29,28);
		i++;

		gp.mon[mapnum][i]=new Bat(gp);
		settermon(mapnum,i,34,28);
		
		mapnum++;
		i++;
	}
	public void setternpc(int map,int i,int locationx , int locationy) {

		gp.npc[map][i].worldx = gp.tilesize*locationx;
		gp.npc[map][i].worldy = gp.tilesize*locationy;
	}	
	public void setterobj(int map,int i,int locationx , int locationy) {

		gp.obj[map][i].worldx = gp.tilesize*locationx;
		gp.obj[map][i].worldy = gp.tilesize*locationy;
	}
	public void settermon(int map,int i,int locationx , int locationy) {

		gp.mon[map][i].worldx = gp.tilesize*locationx;
		gp.mon[map][i].worldy = gp.tilesize*locationy;
	}
}
