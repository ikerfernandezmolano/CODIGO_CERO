package viewController;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class MusicTool {
	private static MusicTool myMT=new MusicTool();
	private Clip clip;
	private FloatControl gainControl;
	
	private MusicTool() {}
	
	public static MusicTool getMusicTool() {
		return myMT;
	}
	
    public void startMusic() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/viewController/music/song1.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setVolume(int pVol) {
        if (gainControl != null && pVol >= 0 && pVol <= 100) {
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * (pVol / 100.0f)) + gainControl.getMinimum();
            gainControl.setValue(gain);
        }
    }
    
    public boolean onOffVol() {
    	float gain=gainControl.getValue();
    	boolean status=false;
    	if(gain==gainControl.getMinimum()) {
    		this.setVolume(70);
    		status=true;
    	}
    	else this.setVolume(0);
    	return status;
    }
}
