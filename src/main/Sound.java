package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {

        soundURL[0] = getClass().getResource("/sound/inGameSong.wav");
        soundURL[1] = getClass().getResource("/sound/keys.wav");
        soundURL[2] = getClass().getResource("/sound/pills.wav");
        soundURL[3] = getClass().getResource("/sound/doorUnlock.wav");
        soundURL[4] = getClass().getResource("/sound/doorInside.wav");
        soundURL[5] = getClass().getResource("/sound/whack.wav");
        soundURL[6] = getClass().getResource("/sound/spiderHitSqueak.wav");
        soundURL[7] = getClass().getResource("/sound/spiderDeadSquelch.wav");
        soundURL[8] = getClass().getResource("/sound/discoverSpiderShriek.wav");
        soundURL[9] = getClass().getResource("/sound/levelUp.wav");
        soundURL[10] = getClass().getResource("/sound/cursor.wav");
        soundURL[11] = getClass().getResource("/sound/select.wav");
        soundURL[12] = getClass().getResource("/sound/sitInChairRelax.wav");
        soundURL[13] = getClass().getResource("/sound/phewKillSpider.wav");
        soundURL[14] = getClass().getResource("/sound/wheelieBinClosing.wav");
        soundURL[15] = getClass().getResource("/sound/wheelieBinDragToLoop.wav");
        soundURL[16] = getClass().getResource("/sound/pickupElectricGuitar.wav");
        soundURL[17] = getClass().getResource("/sound/pickupAcousticGuitar.wav");
    }

    public void setFile(int i) {
        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch(Exception e) {

        }
    }
    public void play(long position, boolean pausing) {
        clip.start();
        if (pausing) {
            clip.setMicrosecondPosition(position);
        }
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public long stop(boolean pausing) {
        long position = 0;
        if (pausing) {
            position = clip.getMicrosecondPosition();
        }
        clip.stop();
        return position;
    }
}
