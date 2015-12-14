package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import framework.GameObject;
import framework.ObjectId;

public class Bullet extends GameObject {
	volatile BufferedImage sprite;
	
	public Bullet(float x, float y, ObjectId id, boolean right) {
		super(x, y, id);
		if(right){
			try {
				sprite = ImageIO.read(new File("resources\\bullet.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			velX = 7;
		}
			
		else{
			try {
				sprite = ImageIO.read(new File("resources\\bullet2.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			velX= -7;
		}
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(ArrayList<GameObject> object) {
		x+=velX;
		y+=velY;
		for(GameObject gameobject: object){
			if(gameobject instanceof Player){
				
			}
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(sprite, (int)x, (int)y, null);

	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
