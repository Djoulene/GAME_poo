package tiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import main.Utilitytool;
import main.gamepanel;
import java.io.FileInputStream;
public class tilemanager {
 gamepanel gp;
 public tile[] tiles;
 public int maptilenum[][][];
 public tilemanager(gamepanel gp) {

	 this.gp = gp;
	 
	 tiles = new tile[50];
	 	 
	 maptilenum = new int[gp.maxmap][gp.maxworldcol][gp.maxworldrow];
	 
	 gettileimage();

	 loadmap("/maps/map00",0);
	 loadmap("/maps/map01",1);
	 loadmap("/maps/map02",2);
	 loadmap("/maps/map03",3);
	 loadmap("/maps/worldV2.txt",4);


 }
 public void gettileimage() {
		 setup(0 , "grass00" , false);
		 setup(1 , "grass00" , false);
		 setup(2 , "grass00" , false);
		 setup(3 , "grass00" , false);
		 setup(4 , "grass00" , false);
		 setup(5 , "grass00" , false);
		 setup(6 , "grass00" , false);
		 setup(7 , "grass00" , false);
		 setup(8 , "grass00" , false);
		 setup(9 , "grass00" , false);
		 //place holders to avoid pointer error
		 setup(10 , "grass00" , false);
		 setup(11 , "grass01" , false);
		 setup(12 , "water00" , true);
		 setup(13 , "water01" , true);
		 setup(14 , "water02" , true);
		 setup(15 , "water03" , true);
		 setup(16 , "water04" , true);
		 setup(17 , "water05" , true);
		 setup(18 , "water06" , true);
		 setup(19 , "water07" , true);
		 setup(20 , "water08" , true);
		 setup(21 , "water09" , true);
		 setup(22 , "water10" , true);
		 setup(23 , "water11" , true);
		 setup(24 , "water12" , true);
		 setup(25 , "water13" , false);
		 setup(26 , "road00" , false);
		 setup(27 , "road01" , false);
		 setup(28 , "road02" , false);
		 setup(29 , "road03" , false);
		 setup(30 , "road04" , false);
		 setup(31 , "road05" , false);
		 setup(32 , "road06" , false);
		 setup(33 , "road07" , false);
		 setup(34 , "road08" , false);
		 setup(35 , "road09" , false);
		 setup(36 , "road10" , false);
		 setup(37 , "road11" , false);
		 setup(38 , "road12" , false);
		 setup(39 , "wall" , true);
		 setup(40 , "tree" , true);
		 setup(41 , "hut" , false);
		 setup(42 , "floor01" , false);
		 setup(43 , "table01" , true);
		 setup(44 , "earth" , false);
		 setup(45 , "door" , false);
		 setup(46 , "046" , true);


	 }

 public void setup(int index , String imageName , boolean collision) {
	 Utilitytool utool = new Utilitytool();
	 try {
		tiles[index]=new tile();
		tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
		tiles[index].image = utool.scaleImage(tiles[index].image,gp.tilesize,gp.tilesize);
		tiles[index].collision = collision;
		} catch(IOException e) {
		 e.printStackTrace();
	 }
 }
 public void loadmap(String filepath , int map) {
	 
	 try{
		
		 InputStream is = getClass().getResourceAsStream(filepath);
		 BufferedReader br = new BufferedReader(new InputStreamReader(is));
		 
		 int col = 0;
		 int row = 0;
		 while(col < gp.maxworldcol && row < gp.maxworldrow) {
			 String line = br.readLine();
			 
					 while(col < gp.maxworldcol) {
						 
				 String numbers[] = line.split(" ");
				 int num = Integer.parseInt(numbers[col]);
				 maptilenum[map][col][row] = num;
				 col++;
				 
			 }
			 if(col == gp.maxworldcol) {
				 col = 0;
				 row++;
			 }
		 }
		 br.close();
	 } catch(Exception e) {
		    e.printStackTrace();

 }
 }
 
 public void draw(Graphics2D g2) {

int worldcol = 0;
int worldrow = 0;

while (worldcol < gp.maxworldcol && worldrow < gp.maxworldrow) {
	
	int tilenum = maptilenum[gp.currentmap][worldcol][worldrow];
	int worldx = worldcol * gp.tilesize;
	int worldy = worldrow * gp.tilesize;
	int screenX = worldx - gp.player.worldx + gp.player.screenx;
	int screenY = worldy - gp.player.worldy + gp.player.screeny;

	if( worldx + gp.tilesize > gp.player.worldx - gp.player.screenx 
	&&  worldx - gp.tilesize < gp.player.worldx + gp.player.screenx 
	&&  worldy + gp.tilesize > gp.player.worldy - gp.player.screeny
	&&  worldy - gp.tilesize < gp.player.worldy + gp.player.screenx) {
		
		g2.drawImage(tiles[tilenum].image , screenX , screenY , gp.tilesize , gp.tilesize , null);

	}
		
	worldcol++;
	
	if(worldcol == gp.maxworldcol) {
		worldcol = 0;
		worldrow++;
	}
}

 }
}

