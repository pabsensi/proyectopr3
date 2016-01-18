package framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
/**
 * Clase GameObject para todos los objetos que se van a introducir en pantalla.
 * Cada objeto de juego que se muestre en pantalla deberá tener obligatoriamente
 * un sprite, una posición x e y, y una velocidad x e y, tanto como bounds para 
 * la colisión que se definen desde los propios objetos.
 * @author Pablosensi
 *
 */
public abstract class GameObject {
	protected boolean facingRight;
	public boolean isFacingRight() {
		return facingRight;
	}
	protected volatile boolean shoot;
	protected ObjectId id;
	protected volatile BufferedImage sprite;
	protected boolean movingRight= false, movingLeft = false;
	public boolean isMovingRight() {
		return movingRight;
	}
	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}
	public boolean isMovingLeft() {
		return movingLeft;
	}
	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}
	protected float x, y;
	protected boolean falling = false;
	protected boolean jumping = false;
	public boolean isFalling() {
		return falling;
	}
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	public boolean isJumping() {
		return jumping;
	}
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	protected float velX = 0, velY = 0;
	
	public GameObject(float x, float y, ObjectId id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	//detección de colisiones
	public abstract void tick(ArrayList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
	public float getvelX(){
		return velX;
	}
	public float getvelY(){
		return velY;
	}
	public void setvelX(float velX){
		this.velX = velX;
	}
	public void setvelY(float velY){
		this.velY = velY;
	}
	public ObjectId getId(){
		return id;
	}
	public void setShoot(boolean b) {
		// TODO Auto-generated method stub
		shoot = b;
		
	}
	public void setCrouching(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
