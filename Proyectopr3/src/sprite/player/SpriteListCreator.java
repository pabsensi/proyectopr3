package sprite.player;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
/**
 * Se introduce el file ya inicializado como el directorio con las animaciones
 * organizadas por carpetas. Se usa un for para recorrer cada carpeta y as� meter en el Hashmap
 * un arraylist para el nombre respectivo de cada animaci�n, con el nombre de la carpeta como
 * llave del hashmap para acceder a cada animaci�n.
 * @author Pablosensi
 *
 */
public class SpriteListCreator {
	
	public static HashMap<String, ArrayList<BufferedImage>> SpriteHashCreator(File directorioSprites){
		HashMap<String, ArrayList<BufferedImage>> spritesJugador = new HashMap<>();
		for(File f : directorioSprites.listFiles()){
			if(!f.isFile()){
				try {
					spritesJugador.put(f.getName(), SpriteArrayCreator(f));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return spritesJugador;
	}
	/**
	 * M�todo utilizado por SpriteListCreator para organizar las im�genes en un arraylist
	 * @param images 
	 * la carpeta que contiene im�genes
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<BufferedImage> SpriteArrayCreator(File images) throws IOException{
		ArrayList<BufferedImage> imageArray = new ArrayList<>();
		for(File f: images.listFiles()){
			if(f.getName().endsWith(".png")){
				BufferedImage sprite = ImageIO.read(f);
				imageArray.add(sprite);
			}
		}
		return imageArray;
	}
}
