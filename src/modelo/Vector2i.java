package modelo;

public class Vector2i {
	public int x,y;
	public static final Vector2i CERO = new Vector2i(0,0);
	
	public Vector2i(int x, int y) { this.x=x; this.y=y; }
	public Vector2i(double x, double y) { this.x=(int) Math.round(x); this.y=(int) Math.round(y); }
	
	public Vector2i() { x=0; y=0; }
	
	
	public static double distancia(Vector2i pos, Vector2i pos2) {
		return Math.sqrt(Math.pow(pos.x-pos2.x, 2)+Math.pow(pos.y-pos2.y, 2));
	}
	public double distancia(Vector2i pos) {
		return Math.sqrt(Math.pow(this.x-pos.x, 2)+Math.pow(this.y-pos.y, 2));
	}
	public Vector2i set(int x, int y) {
		this.x=x;
		this.y=y;
		return this;
	}
	public Vector2i mover(int derecha, int arriba) {
		x+=derecha;
		y+=arriba;
		return this;
	}
	public Vector2i rotar(Vector2i punto, double cRad) {
		int x = this.x - punto.x;
		int y = this.y - punto.y;
		double ang = Utils.inclinacion2D(x,y);

		x = (int) Math.round(Math.cos(ang+(cRad*Math.PI))*this.distancia(punto)+punto.x);
		y = (int) Math.round(Math.sin(ang+(cRad*Math.PI))*this.distancia(punto)+punto.y);
		
		this.x=x;
		this.y=y;
		return this;
	}
	
	
	public Vector2i set$(int x, int y) {
		return new Vector2i(x,y);
	}
	public Vector2i mover$(int derecha, int arriba) {
		Vector2i out = this.clone();
		out.x+=derecha;
		out.y+=arriba;
		return out;
	}
	public Vector2i rotar$(Vector2i punto, double cRad) {
		int x = this.x - punto.x;
		int y = this.y - punto.y;
		double ang = Utils.inclinacion2D(x,y);

		x = (int) Math.round(Math.cos(ang+(cRad*Math.PI))*this.distancia(punto)+punto.x);
		y = (int) Math.round(Math.sin(ang+(cRad*Math.PI))*this.distancia(punto)+punto.y);

		return new Vector2i(x,y);
	}
	
	public static Vector2i parse(Vector2 pos) {
		return new Vector2i(pos.x, pos.y);
	}
	
	public Vector2i clone() {
		return new Vector2i(x,y);
	}
	public String toString() {
		return "("+x+";"+y+")";
	}
}
