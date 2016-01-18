package framework;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class SoundTest {
	public static void main(String[] args) throws Exception {
		
		URL url = SoundTest.class.getResource("fondo.wav");
		AudioClip clip = Applet.newAudioClip(url);
		AudioClip clip2 = Applet.newAudioClip(url);
		clip.play();
		clip2.loop();
		Thread.sleep(20000);
		clip2.stop();
		
	}
}