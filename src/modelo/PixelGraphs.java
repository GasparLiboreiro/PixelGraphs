package modelo;

import java.awt.Color;
import java.util.ArrayList;

import vista.Ventana;

public class PixelGraphs { // game handler
	public static Color[] COLORES = { new Color(252,3,104), new Color(69,27,149), new Color(2,9,19)};
	//public static Color[] COLORES = { new Color(255,255,255), new Color(0,0,0), new Color(0,0,0)};
	
	
	public static RenderHandler renderer = new RenderHandler();
	

	public static Vector2i cam = new Vector2i(0,0);
	public static ArrayList<Objeto> escena = new ArrayList<Objeto>(); // escena
	
	public static Objeto mano = null; // figura en pre visualizacion
	public static int tipoSelec = Objeto.CIRCULO;
	public static final int maxTipo = 3;
	
	private Ventana window;
	private GameLoop gameloop;
	
	
	
	
	public PixelGraphs(){
		window = new Ventana();
		gameloop = new GameLoop(this, window.panel);

	
	
		gameloop.startLoop();
		window.setVisible(true);
	}
	
	public void update(double d) {
		
	}
	
}
