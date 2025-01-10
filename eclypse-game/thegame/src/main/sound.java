package main;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class sound {
Clip clip;
URL soundurl[] = new URL[30];
public sound() {
	soundurl[0] = getClass().getResource("/sound/dooropen.wav");
	soundurl[1] = getClass().getResource("/sound/gameover.wav");
	soundurl[2] = getClass().getResource("/sound/hitmonster.wav");
	soundurl[3] = getClass().getResource("/sound/levelup.wav");
	soundurl[4] = getClass().getResource("/sound/speak.wav");
	soundurl[5] = getClass().getResource("/sound/title.wav");


}
public void setfile(int i) {
	try {
		AudioInputStream ais = AudioSystem.getAudioInputStream(soundurl[i]);
		clip = AudioSystem.getClip();
		clip.open(ais);
	}catch (Exception e) {
		
	}
}
public void play() {
	clip.start();
}
public void loop() {
	clip.loop(clip.LOOP_CONTINUOUSLY);
}
public void stop() {
	clip.stop();
}
}
