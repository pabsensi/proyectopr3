package mapa;

public abstract class Mapa {
	private int ancho;
	private int alto;
	//cada cuadro del array contiene un sprite o una imagen, todas juntas conmponen el mapa
	private int[] cuadros;

	public Mapa(int ancho, int alto){
		this.ancho=ancho;
		this.alto=alto;
		
		
		cuadros=new int[ancho*alto];
		generarMapa();
	}
	//para cargar el mapa desde una ruta
	public Mapa(String ruta){
		cargarMapa(ruta);
	}
	//Metodo para generar el mapa
	private void generarMapa(){
		
	}
	//Metodo para cargar el mapa desde una ruta
	private void cargarMapa(String ruta){
		
	}
	public void actualizar(){
		
	}
	public void mostrar(){
		
	}
}
