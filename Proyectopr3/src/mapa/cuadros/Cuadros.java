package mapa.cuadros;

import graficos.Pantalla;
import graficos.Sprite;

public abstract class Cuadros {
	public int x;
	public int y;
	//cada sprite es una imagen que va dentro de los cuadros, una por cuadro
	public Sprite sprite;
	
	public Cuadros(Sprite sprite){
		this.sprite=sprite;
	}
	//para dibujar los sprites
	//se le pasa una posicion y una pantalla en la que dibujar
	public void mostrar(int x, int y, Pantalla pantalla){
		
	}
	//para saber que cuadros se pueden atravesar y cuales no
	public boolean solido(){
		return false;
		
	}
}
