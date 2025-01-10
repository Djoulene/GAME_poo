package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.gamepanel;

public class objheart extends Entity {
	public objheart(gamepanel gp) {
		super(gp);
		name = "heart";
		image1 = setup("/objects/heart_full",gp.tilesize,gp.tilesize);
		image2 = setup("/objects/heart_half",gp.tilesize,gp.tilesize);
		image3 = setup("/objects/heart_blank",gp.tilesize,gp.tilesize);

}
}