package main;

import entity.Entity;

public class collisioncheck {
	
	gamepanel gp;
	
public collisioncheck(gamepanel gp) {
	this.gp = gp;
}
public void checktile(Entity entity) {
	int entityleftworldx = entity.worldx + entity.solidarea.x;
	int entityrightworldx = entity.worldx + entity.solidarea.x + entity.solidarea.width;
	int entitytopworldy = entity.worldy + entity.solidarea.y;
	int entitybottomworldy = entity.worldy + entity.solidarea.y + entity.solidarea.height;
	
	int entityleftcol = entityleftworldx/gp.tilesize;
	int entityrightcol = entityrightworldx/gp.tilesize;
	int entitytoprow = entitytopworldy/gp.tilesize;
	int entitybottomrow = entitybottomworldy/gp.tilesize;
	
	int tile1;
	int tile2;
	
	switch(entity.direction) {
	case "up":
		entitytoprow = (entitytopworldy - entity.speed)/gp.tilesize;
		tile1 = gp.tilem.maptilenum[gp.currentmap][entityleftcol][entitytoprow];
		tile2 = gp.tilem.maptilenum[gp.currentmap][entityrightcol][entitytoprow];
		if(gp.tilem.tiles[tile1].collision == true || gp.tilem.tiles[tile2].collision == true) {
			entity.collisionon = true;
		}
		break;
		
	case "down":
		entitybottomrow = (entitybottomworldy + entity.speed)/gp.tilesize;
		tile1 = gp.tilem.maptilenum[gp.currentmap][entityleftcol][entitybottomrow];
		tile2 = gp.tilem.maptilenum[gp.currentmap][entityrightcol][entitybottomrow];
		if(gp.tilem.tiles[tile1].collision == true || gp.tilem.tiles[tile2].collision == true) {
			entity.collisionon = true;
		}
		break;
		
	case "right":
		entityrightcol = (entityrightworldx + entity.speed)/gp.tilesize;
		tile1 = gp.tilem.maptilenum[gp.currentmap][entityrightcol][entitytoprow];
		tile2 = gp.tilem.maptilenum[gp.currentmap][entityrightcol][entitybottomrow];
		if(gp.tilem.tiles[tile1].collision == true || gp.tilem.tiles[tile2].collision == true) {
			entity.collisionon = true;
		}
		break;
		
	case "left":
		entityleftcol = (entityleftworldx - entity.speed)/gp.tilesize;
		tile1 = gp.tilem.maptilenum[gp.currentmap][entityleftcol][entitytoprow];
		tile2 = gp.tilem.maptilenum[gp.currentmap][entityleftcol][entitybottomrow];
		if(gp.tilem.tiles[tile1].collision == true || gp.tilem.tiles[tile2].collision == true) {
			entity.collisionon = true;
		}
		break;
		
	}
	
	
}

public int checkobj(Entity entity , boolean player) {
	
int index = 999;
		for(int i = 0 ; i < gp.obj[1].length ; i++) {
			if ( gp.obj[gp.currentmap][i] != null ) {
				//get etity's solid area postion
				entity.solidarea.x += entity.worldx;
				entity.solidarea.y += entity.worldy;	
				//get obj sold area pos
				gp.obj[gp.currentmap][i].solidarea.x += gp.obj[gp.currentmap][i].worldx;
				gp.obj[gp.currentmap][i].solidarea.y += gp.obj[gp.currentmap][i].worldy;
				
				switch(entity.direction) {
				case "up":entity.solidarea.y -= entity.speed;break;
				case "down":entity.solidarea.y += entity.speed;break;
				case "right":entity.solidarea.x += entity.speed;break;
				case "left":entity.solidarea.x -= entity.speed;break;
				}
				
				if(entity.solidarea.intersects(gp.obj[gp.currentmap][i].solidarea)) {
					if(gp.obj[gp.currentmap][i].collision == true) {
						entity.collisionon = true;
					}
					if(player == true) {
						index = i;
					}
				}
			entity.solidarea.x = entity.solidareadefaultx;
			entity.solidarea.y = entity.solidareadefaulty;
			gp.obj[gp.currentmap][i].solidarea.x = gp.obj[gp.currentmap][i].solidareadefaultx;
			gp.obj[gp.currentmap][i].solidarea.y = gp.obj[gp.currentmap][i].solidareadefaulty;
		}

	}
	return index;
}

public int checkentity(Entity entity, Entity[][] target) {
	
int index = 999;
		for(int i = 0 ; i < target[1].length ; i++) {
			if ( target[gp.currentmap][i] != null ) {
				entity.solidarea.x += entity.worldx;
				entity.solidarea.y += entity.worldy;
					
				target[gp.currentmap][i].solidarea.x += target[gp.currentmap][i].worldx;
				target[gp.currentmap][i].solidarea.y += target[gp.currentmap][i].worldy;
				switch(entity.direction) {
				case "up":entity.solidarea.y -= entity.speed;break;
				case "down":entity.solidarea.y += entity.speed;break;
				case "right":entity.solidarea.x += entity.speed;break;
				case "left":entity.solidarea.x -= entity.speed;break;
				}
				if(entity.solidarea.intersects(target[gp.currentmap][i].solidarea)) {
					if(target[gp.currentmap][i] != entity) {
						entity.collisionon = true;
						index = i;
					}
			}
			entity.solidarea.x = entity.solidareadefaultx;
			entity.solidarea.y = entity.solidareadefaulty;
			target[gp.currentmap][i].solidarea.x = target[gp.currentmap][i].solidareadefaultx;
			target[gp.currentmap][i].solidarea.y = target[gp.currentmap][i].solidareadefaulty;
		}

	}
	return index;
}
public boolean checkplayer(Entity entity) {
	boolean contactplayer = false;
			entity.solidarea.x += entity.worldx;
			entity.solidarea.y += entity.worldy;
				
			gp.player.solidarea.x += gp.player.worldx;
			gp.player.solidarea.y += gp.player.worldy;
			
			switch(entity.direction) {
			case "up":entity.solidarea.y -= entity.speed;break;
			case "down":entity.solidarea.y += entity.speed;break;
			case "right":entity.solidarea.x += entity.speed;break;
			case "left":entity.solidarea.x -= entity.speed;break;
			}
			if(entity.solidarea.intersects(gp.player.solidarea)) {
				entity.collisionon = true;
				contactplayer = true;
		}
		entity.solidarea.x = entity.solidareadefaultx;
		entity.solidarea.y = entity.solidareadefaulty;
		gp.player.solidarea.x = gp.player.solidareadefaultx;
		gp.player.solidarea.y = gp.player.solidareadefaulty;
		return contactplayer;
}
}
