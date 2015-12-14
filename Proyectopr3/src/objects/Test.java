package objects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import framework.GameObject;
import framework.ObjectId;


public class Test extends GameObject {

	public Test(float x, float y, ObjectId id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(ArrayList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g){
		// TODO Auto-generated method stub
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 32, 32);
	}

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public void setX(float x) {
		// TODO Auto-generated method stub
		this.x = x;
	}

	@Override
	public void setY(float y) {
		// TODO Auto-generated method stub
		this.y = y;
	}

	@Override
	public float getvelX() {
		// TODO Auto-generated method stub
		return velX;
	}

	@Override
	public float getvelY() {
		// TODO Auto-generated method stub
		return velY;
	}

	@Override
	public void setvelX(float velX) {
		// TODO Auto-generated method stub
		this.velX = velX;
	}

	@Override
	public void setvelY(float velY) {
		// TODO Auto-generated method stub
		this.velY = velY;
	}

	@Override
	public ObjectId getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
