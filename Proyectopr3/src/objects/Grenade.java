package objects;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import juego.Juego;
import pruebas.Animator;
import sprite.player.SpriteListCreator;
import framework.GameObject;
import framework.ObjectId;

public class Grenade extends Projectile {

	private static URL url = Grenade.class.getResource("granada.wav");
	private static AudioClip clip = Applet.newAudioClip(url);
	private int danyo = 4;
	public int getDaño() {
		return danyo;
	}
	protected double antPosX, antPosY;
	protected long antTiempo;
	protected long tiempo;
	long tiempoExplosion;
	boolean explode = false;
	private HashMap<String, ArrayList<BufferedImage>> spriteHash = new HashMap<String, ArrayList<BufferedImage>>();
	private float gravity = 0.5f;
	Animator animador;
	public Grenade(float x, float y, ObjectId id, boolean right, float vel) {
		super(x, y, id);
		danyo=4;
		spriteHash = SpriteListCreator.SpriteHashCreator(new File("resources/Grenade"));
		sprite = spriteHash.get("grenade.png").get(0);
		animador = new Animator(spriteHash.get("grenade.png"));
		// TODO Auto-generated constructor stub
		velY=-9;
		if(right)
			velX = vel+ 7;
		else
			velX= vel -7 ;
	}
	

	private void Collision(ArrayList<GameObject> object){
		for(int i =0; i<object.size(); i++){
			GameObject TempObject = object.get(i);
			if(TempObject.getId()== ObjectId.Enemy){
				if((getBounds().intersects(((Enemy) TempObject).getBoundsTop())) || getBounds().intersects(((Enemy) TempObject).getBounds()) && !explode){
					tiempoExplosion = System.currentTimeMillis();
					y = TempObject.getY()-45;		
					explode = true;
					animador.setFrames(spriteHash.get("explosion"));
					velY = 0;
					((Enemy)TempObject).setVida(danyo);
					calcChoqueExacto(((Enemy)TempObject).getBounds(), antPosX, antPosY, antTiempo, x, y, tiempo, 2000);
				}
			}
				else if(TempObject instanceof Block){
					if(getBounds().intersects(TempObject.getBounds())&& !explode){
					tiempoExplosion = System.currentTimeMillis();
					explode = true;
					animador.setFrames(spriteHash.get("explosion"));
					velY = 0;
					y = TempObject.getY()-95;
					}
				}
					
			}
			}
	@Override
	public void tick(ArrayList<GameObject> objectlist) {
		// TODO Auto-generated method stub

		antPosX = x; antPosY = y; antTiempo = tiempo;
		tiempo = System.currentTimeMillis();
		Collision(objectlist);
		if(explode){
			sprite = animador.sprite;
			clip.play();
			velY= 0;
			velX= 0;
			animador.update(System.currentTimeMillis());
			if(System.currentTimeMillis() - tiempoExplosion >= 500){
				for(int i =0; i<objectlist.size(); i++){
					GameObject TempObject = objectlist.get(i);
					if(TempObject.equals(this)){
						objectlist.remove(i);
					}
				}
			}
		}
		x+=velX;
		y+=velY;
		velY+= gravity;
		

	}

	private void calcChoqueExacto( Rectangle bounds, double x1, double y1, double t1, double x2, double y2, double t2, int numRec ) {
		if (numRec==0) {  // Caso base
			double distX2 = getX()+getBounds().getWidth()/2-x2-bounds.getWidth()/2;
			double distY2 = getY()+getBounds().getHeight()/2-y2-bounds.getHeight()/2;
			double dist2 = Math.sqrt( distX2*distX2 + distY2*distY2 );
			System.out.println( "  Choque preciso: " + t2 + " en (" + x2 + "," + y2 + ") - " + 
					"distancia " + (dist2 - (getBounds().getWidth()+ bounds.getWidth())) );
		} else {  // Caso general
			double xMed = (x2+x1)/2; double yMed = (y2+y1)/2;  double tMed = (t2+t1)/2;
			double distXmed = getX()+getBounds().getWidth()/2-x2-bounds.getWidth()/2;
			double distYmed = getY()+getBounds().getHeight()/2-y2-bounds.getHeight()/2;
			double distMed = Math.sqrt( distXmed*distXmed + distYmed*distYmed ) - (getBounds().getWidth()+ bounds.getWidth());
			System.out.println( "  Punto medio choque: " + tMed + " en (" + xMed + "," + yMed + ") - " + 
					"distancia " + distMed );
			if (distMed>0) {  // Hacia la derecha
				calcChoqueExacto( bounds, xMed, yMed, tMed, x2, y2, t2, numRec-1);
			} else {   // Hacia la izquierda
				calcChoqueExacto( bounds, x1, y1, t1, xMed, yMed, tMed, numRec-1);
			}
		}
	}
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(sprite, (int)x, (int)y, null);

	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, 5,5);
	}

}
