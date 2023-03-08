package vista;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import controlador.GameInputManager;
import modelo.Objeto;
import modelo.PixelGraphs;
import modelo.Vector2;
import modelo.Vector2i;

public class JPanelHandler extends JPanel {
	private int[][] out;
	public GameInputManager inputs = new GameInputManager();
	public static Vector2i tamVentana = new Vector2i();
	
	
	public JPanelHandler() {
		this.addMouseListener(inputs);
		this.addMouseMotionListener(inputs);
		this.addMouseWheelListener(inputs);
	}
	
	
	public void paint(Graphics g) {
		tamVentana.set(this.getWidth(), this.getHeight());
		
		g.setColor(Color.BLACK);
		for(int y=0; y<this.getHeight(); y++)
			g.drawLine(0, y, this.getWidth(), y);
		
		
		int alto = PixelGraphs.renderer.res;
		int ancho = (int)(PixelGraphs.renderer.res * PixelGraphs.renderer.ratio);

		out = new int[ancho][alto];
		
		for(int x=0; x<ancho; x++)
			for(int y=0; y<alto; y++)
				out[x][y]=2;
		
		
		PixelGraphs.renderer.dibuarEjes(out, true);
		
		
		for(int o=0; o<PixelGraphs.escena.size(); o++) { //renderizar escena
			PixelGraphs.escena.get(o).draw(out);
		}
		if(PixelGraphs.mano!=null)
			PixelGraphs.mano.draw(out);
		
		int pixelTam;
		if(this.getHeight()/alto < this.getWidth()/ancho) { //limitado verticalmente
			pixelTam = this.getHeight()/alto;
		} else { //limitado horizontalmente
			pixelTam = this.getWidth()/ancho;
		}
		
		for(int x=0; x<ancho; x++) {
			for(int y=0; y<alto; y++) {
				g.setColor(PixelGraphs.COLORES[out[x][alto-y-1]]);
			
				for(int c=0; c<pixelTam; c++) {//draw square
					int h = x*pixelTam+c;
					int k = y*pixelTam;
					
					g.drawLine(this.getWidth()/2 + h-ancho*pixelTam/2, this.getHeight()/2 + k-alto*pixelTam/2, this.getWidth()/2 + h-ancho*pixelTam/2, this.getHeight()/2 + (k-alto*pixelTam/2)+pixelTam);
				}
			}
		}
	}
	
	
	
	
}