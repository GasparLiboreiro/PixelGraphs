package modelo;

public abstract class Objeto {
	public static final int PUNTO = 0;
	public static final int RECTA = 1;
	public static final int LINEA = 2;
	public static final int CIRCULO = 3;
	public static final int[]COLORES = {0, 1, 2};
	
	public int PRI = 0;
	public int SEC = 1;
	public int BAC = 2;
	
	
	
	public int tipo;
	public Vector2[] puntos;
	
	public Objeto(int tipo, Vector2[] puntos) {
		this.tipo = tipo;
		this.puntos = puntos;
	}
	

	public abstract void draw(int[][] out);
	
	public static Objeto getObjetoFromType(int tipo, Vector2[] puntos) {
		Objeto out;
		switch(tipo) {
			case PUNTO:   out = new Punto(puntos); break;
			case RECTA:   out = new Recta(puntos); break;
			case LINEA:   out = new Linea(puntos); break;
			case CIRCULO: out = new Circulo(puntos); break;
			default: throw new Error("tipo desconocido: "+tipo);
		}
		
		return out;
	}
}
