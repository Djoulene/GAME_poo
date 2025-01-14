package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Assassin;
import entity.Mage;
import entity.Warrior;

public class keyhandler implements KeyListener{
	public boolean shotingkey2, leftpressed , rightpressed , downpressed , uppressed , enterpressed, shotingkey;
	//debug
	boolean showdebugtext = false;
	gamepanel gp;
	
	
	
	public keyhandler(gamepanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(gp.UI.titlescreenstate==0) {if(gp.gameState == gp.titlestate) {titlestate1(code);}}
		else if(gp.UI.titlescreenstate==1) {if(gp.gameState == gp.titlestate) {titlestate2(code);}}
		else if(gp.gameState == gp.playstate) {playstate(code);}
		else if(gp.gameState == gp.characterstate) {characterstate(code);}
		else if(gp.gameState == gp.dialoguestate) {dialoguestate(code);}
	    else if(gp.gameState == gp.gameoverstate) {gameoverstate(code);}
	}
public void gameoverstate(int code) {
	if(code == KeyEvent.VK_ENTER) {
			gp.player.setdefaultposition();
			gp.player.setdefaultvalues();
			gp.gameState = gp.playstate;
	}
}
public void titlestate1(int code) {	
	if(code == KeyEvent.VK_S) {
		gp.UI.commandnum--;
		if(gp.UI.commandnum < 0) {
			gp.UI.commandnum = 1;
		}
	}else
	if(code == KeyEvent.VK_Z) {
		gp.UI.commandnum++;
		if(gp.UI.commandnum > 1) {
			gp.UI.commandnum = 0;
		}
	}
		
	if(code==KeyEvent.VK_ENTER) {
		if(gp.UI.commandnum == 0) {
		gp.UI.titlescreenstate = 1;
		enterpressed = false;
		}
		if(gp.UI.commandnum == 1) {
			System.exit(0);
		}
	}
}
public void titlestate2(int code) {
	if(code==KeyEvent.VK_D) {
		gp.UI.commandnum--;
	}
	if(code==KeyEvent.VK_Q) {
		gp.UI.commandnum++;
	}
	if(gp.UI.commandnum >2) {
		gp.UI.commandnum = 0;
	}
	if(gp.UI.commandnum <0) {
		gp.UI.commandnum = 2;
	}
	if(code==KeyEvent.VK_ENTER) {
		if(gp.UI.commandnum == 0) {
			gp.player = new Warrior(gp,this);
			gp.gameState = gp.playstate;
			enterpressed = false;
			gp.player.maxlife = 10;
		}
		if(gp.UI.commandnum == 1) {
			gp.player = new Mage(gp,this);
			gp.gameState = gp.playstate;
			enterpressed = false;
		}
		if(gp.UI.commandnum == 2) {
			gp.player = new Assassin(gp,this);
			gp.gameState = gp.playstate;
			enterpressed = false;
		}		
		gp.player.getplayerimage();
		gp.player.setdefaultposition();
		gp.player.setdefaultvalues();
		gp.UI.titlescreenstate=10;
	}
}
public void dialoguestate(int code) {
	if(code == KeyEvent.VK_ENTER) {
		gp.gameState = gp.playstate;
	}
}

public void playstate(int code) {
	if(code == KeyEvent.VK_ENTER) {
			enterpressed = true;
	}
	if(code==KeyEvent.VK_Z) {
		uppressed = true;
	}
	if(code==KeyEvent.VK_S) {
		downpressed = true;
	}
	if(code==KeyEvent.VK_Q) {
		leftpressed = true;
	}
	if(code==KeyEvent.VK_D) {
		rightpressed = true;
	}
	if(code==KeyEvent.VK_P) {
	gp.gameState = gp.characterstate;
	}
	if(code==KeyEvent.VK_E) {
		shotingkey = true;
	}
	if(code==KeyEvent.VK_A) {
		shotingkey2 = true;
	}
	if(code == KeyEvent.VK_T) {
		if(showdebugtext == true) {
			showdebugtext = false;
		}else if(showdebugtext == false) {
			showdebugtext = true;
		}
	}
}
public void characterstate(int code) {
	if(code == KeyEvent.VK_P) {
		gp.gameState = gp.playstate;
	}
}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code ==KeyEvent.VK_ENTER) {
			enterpressed = false;
		}
		if(code==KeyEvent.VK_Z) {
			uppressed = false;
		}
		if(code==KeyEvent.VK_S) {
			downpressed = false;
		}
		if(code==KeyEvent.VK_Q) {
			leftpressed = false;
		}
		if(code==KeyEvent.VK_D) {
			rightpressed = false;
		}
		if(code==KeyEvent.VK_E) {
			shotingkey = false;
		}
		if(code==KeyEvent.VK_A) {
			shotingkey2 = false;
		}
	}
}
