import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class MusicPlayer {

    private InputStream is, is2, bis, bis2;
    private AudioInputStream stream, stream2;
    private AudioFormat format, format2;
    private DataLine.Info info, info2;
    private Clip clip, clip2;
    
    public MusicPlayer() {
        try {
            is = this.getClass().getResourceAsStream("Kaelan.wav");
            InputStream bis = new BufferedInputStream(is);
            
            stream = AudioSystem.getAudioInputStream(bis);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void play() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop() {
        clip.stop();
    }
    
    public void playHit() {
        try {
            is = this.getClass().getResourceAsStream("jab.wav");
            InputStream bis = new BufferedInputStream(is);
            
            stream = AudioSystem.getAudioInputStream(bis);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        clip.loop(0);
    }

    public void playWin() {
        try {
            is = this.getClass().getResourceAsStream("grunt.wav");
            bis = new BufferedInputStream(is);
            
            stream = AudioSystem.getAudioInputStream(bis);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            
            is2 = this.getClass().getResourceAsStream("applause.wav");
            bis2 = new BufferedInputStream(is2);
            
            stream2 = AudioSystem.getAudioInputStream(bis2);
            format2 = stream2.getFormat();
            info2 = new DataLine.Info(Clip.class, format2);
            clip2 = (Clip) AudioSystem.getLine(info2);
            clip2.open(stream2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        clip.loop(0);
        clip2.loop(0);
    }

}