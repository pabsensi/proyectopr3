package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import framework.GameObject;
import framework.ObjectId;

public class Projectile extends GameObject {
	protected int danyo;
	public Projectile(float x, float y, ObjectId id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}
	public int getDanyo(){
		return danyo;
	}
	@Override
	public void tick(ArrayList<GameObject> objectlist) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
