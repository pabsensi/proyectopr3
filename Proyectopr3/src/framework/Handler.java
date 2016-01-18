package framework;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Handler del juego, que se encarga de renderizar y acualizar cada objeto que introduzcamos
 * en el arraylist de objetos de juego.
 * @author Pablosensi
 *
 */
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
