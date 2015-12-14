import java.awt.Graphics;
import java.util.ArrayList;

import framework.GameObject;


public class Handler {
	public ArrayList<GameObject> objectlist = new ArrayList<GameObject>();
	
	
	public void render(Graphics g){
		for(int i=0; i<objectlist.size(); i++){
			objectlist.get(i).render(g);
		}
	}
	public void tick(){
		for(int i=0; i<objectlist.size(); i++){
			objectlist.get(i).tick(objectlist);
		}
	}
	public void addObject(GameObject object){
		this.objectlist.add(object);
	}
	public void removeObject(GameObject object){
		this.objectlist.remove(object);
	}
}
