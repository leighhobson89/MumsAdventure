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
