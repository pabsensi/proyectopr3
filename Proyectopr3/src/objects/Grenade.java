package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import framework.GameObject;
import framework.ObjectId;

public class Grenade extends GameObject {

	private float gravity = 0.5f;
	public Grenade(float x, float y, ObjectId id, boolean right, float vel) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		velY=-9;
		if(right){
			try {
				sprite = ImageIO.read(new File("resources\\grenade.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			velX = vel+ 7;
		}

		else{
			try {
				sprite = ImageIO.read(new File("resources\\grenade.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			velX= vel -7 ;
		}
	}

	@Override
	public void tick(ArrayList<GameObject> objectlist) {
		// TODO Auto-generated method stub
		x+=velX;
		y+=velY;
		velY+= gravity;

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
