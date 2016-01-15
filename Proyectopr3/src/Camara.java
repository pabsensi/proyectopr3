import framework.GameObject;


public class Camara {
	private float x, y;
	public Camara(float x,  float y) {
		this.x =  x;
		this.y = y;
		
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public void tick(GameObject Player){
		x= -Player.getX() + 300;
	}
}
