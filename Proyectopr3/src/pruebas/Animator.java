package pruebas;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
/**
 * Clase animador, que desde un ArrayList de fotogramas que pasamos,
 *  cambia el sprite cada x tiempo (marcado por el parámetro speed).
 * @author Pablosensi
 *
 */
public class Animator {
	private ArrayList<BufferedImage> frames;
	private final int defaultframe = 0;
	public BufferedImage sprite;
	public volatile boolean animating = true;
	private long previousTime, speed;
	private int frameAtPause, currentFrame;
	public Animator(ArrayList<BufferedImage> frames){
		speed = 50;
		this.setFrames(frames);
	}
	public void update(long time){
		if(animating){
			if(time - previousTime >= speed){
				//Update animation
				currentFrame++;
				try{
					sprite = getFrames().get(currentFrame);
				}catch(IndexOutOfBoundsException e){
					currentFrame = 0;
					sprite = getFrames().get(currentFrame);
					
				}
				previousTime = time;
			}
		}
			else
				sprite = getFrames().get(0);
				
			}
			
		
	public void setSpeed(long speed){
		this.speed = speed;
		
	}
	public void start(){
		previousTime = 0;
		frameAtPause = 0;
		currentFrame = 0;
	}
	public void stop(){
		previousTime = 0;
		frameAtPause = defaultframe;
		currentFrame = defaultframe;
	}
	public void pause(){
		animating = false;
		
	}
	public void resume(){
		animating =true;
	}
	public ArrayList<BufferedImage> getFrames() {
		return frames;
	}
	public void setFrames(ArrayList<BufferedImage> frames) {
		this.frames = frames;
	}
}
