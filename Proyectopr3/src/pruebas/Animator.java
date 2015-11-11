package pruebas;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animator {
	ArrayList<BufferedImage> frames;
	int defaultframe = 0;
	BufferedImage sprite;
	public volatile boolean running = false;
	private long previousTime, speed;
	private int frameAtPause, currentFrame;
	public Animator(ArrayList<BufferedImage> frames){
		this.frames = frames;
		
	}
	public void update(long time){
		if(running){
			if(time - previousTime >= speed){
				//Update animation
				currentFrame++;
				System.out.println(currentFrame);
				try{
					sprite = frames.get(currentFrame);
				}catch(IndexOutOfBoundsException e){
					currentFrame = 0;
					sprite = frames.get(currentFrame);
					
				}
				previousTime = time;
				
			}
		}
		if(!running)
			sprite = frames.get(currentFrame);
	}
	public void setSpeed(long speed){
		this.speed = speed;
		
	}
	public void start(){
		running = true;
		previousTime = 0;
		frameAtPause = 0;
		currentFrame = 0;
	}
	public void stop(){
		running = false;
		previousTime = 0;
		frameAtPause = defaultframe;
		currentFrame = defaultframe;
	}
	public void pause(){
		frameAtPause = currentFrame;
		running= false;
		
	}
	public void resume(){
		currentFrame = frameAtPause;
	}
}
