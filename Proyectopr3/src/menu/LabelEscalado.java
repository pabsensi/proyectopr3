package menu;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class LabelEscalado extends JLabel {
	// la posición X,Y se hereda de JLabel
	protected int anchuraObjeto;  // Anchura del objeto en pixels (depende de la imagen)
	protected int alturaObjeto;  // Altura del objeto en pixels (depende de la imagen)
	protected ImageIcon icono;  // icono del objeto
	protected Image imagenObjeto;  // imagen para el escalado
	private static final long serialVersionUID = 1L;  // para serializar

	
	public LabelEscalado( String nombreImagenObjeto, int anchura, int altura ) {
		anchuraObjeto = anchura;
		alturaObjeto = altura;
		// Cargamos el icono (como un recurso - vale tb del .jar)
        	icono = new ImageIcon(nombreImagenObjeto);
    		setIcon( icono );
			imagenObjeto = icono.getImage();
    	setSize( anchura, altura );
	}
	
	/** Devuelve la anchura del rectángulo gráfico del objeto
	 * @return	Anchura
	 */
	public int getAnchuraObjeto() {
		return anchuraObjeto;
	}
	
	/** Devuelve la altura del rectángulo gráfico del objeto
	 * @return	Altura
	 */
	public int getAlturaObjeto() {
		return alturaObjeto;
	}

	// Dibuja este componente de una forma no habitual (si es proporcional)
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;  // El Graphics realmente es Graphics2D
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
        g2.drawImage(imagenObjeto, 0, 0, anchuraObjeto, alturaObjeto, null);
	}
	
}