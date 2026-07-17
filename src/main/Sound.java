package main;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	FloatControl fc;
	int volumeScale=3;
	float volume;
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/title.wav");
		soundURL[1] = getClass().getResource("/sound/labirinth.wav");
		soundURL[2] = getClass().getResource("/sound/world.wav");
		soundURL[3] = getClass().getResource("/sound/rooms.wav");
		soundURL[4] = getClass().getResource("/sound/door.wav");
		soundURL[5] = getClass().getResource("/sound/item.wav");
		soundURL[6] = getClass().getResource("/sound/medallion.wav");
		soundURL[7] = getClass().getResource("/sound/chest.wav");
		soundURL[8] = getClass().getResource("/sound/attack.wav");
		soundURL[9] = getClass().getResource("/sound/cursor.wav");
		soundURL[10] = getClass().getResource("/sound/enemyDies.wav");
		soundURL[11] = getClass().getResource("/sound/enemyHit.wav");
		soundURL[12] = getClass().getResource("/sound/fireWand.wav");
		soundURL[13] = getClass().getResource("/sound/jazetaDies.wav");
		soundURL[14] = getClass().getResource("/sound/jazetaHurt.wav");
		soundURL[15] = getClass().getResource("/sound/menuOpen.wav");
		soundURL[16] = getClass().getResource("/sound/menuClosed.wav");
		soundURL[17] = getClass().getResource("/sound/stairsDown.wav");
		soundURL[18] = getClass().getResource("/sound/stairsUp.wav");
		soundURL[19] = getClass().getResource("/sound/bombExplode.wav");
		soundURL[20] = getClass().getResource("/sound/text.wav");
		soundURL[21] = getClass().getResource("/sound/cherry.wav");
		soundURL[22] = getClass().getResource("/sound/coin.wav");
		soundURL[23] = getClass().getResource("/sound/buying.wav");
		soundURL[24] = getClass().getResource("/sound/fireWand.wav");
		soundURL[25] = getClass().getResource("/sound/ending.wav");
	}
	
	public void setFIle(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
				clip = AudioSystem.getClip();
				clip.open(ais);
				fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
				checkVolume();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
	public void checkVolume() {
		switch(volumeScale) {
		case 0: volume=-80f;  break;
		case 1:volume =-32f; break;
		case 2: volume =-20f;break;
		case 3:volume =-12f; break;
		case 4: volume =-3f;break;
		case 5:volume =0f; break;
		}
		fc.setValue(volume);
	}
}
