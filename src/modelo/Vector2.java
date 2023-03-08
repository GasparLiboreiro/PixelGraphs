package modelo;

import modelo.Utils;

public class Vector2 {
	public double x,y;
	public static final Vector2 CERO = new Vector2(0,0);
	
	public Vector2(double x, double y) { this.x=x; this.y=y; }
	
	public Vector2() { x=0; y=0; }
	
	
	public static double distancia(Vector2 pos, Vector2 pos2) {
		return Math.sqrt(Math.pow(pos.x-pos2.x, 2)+Math.pow(pos.y-pos2.y, 2));
	}
	public double distancia(Vector2 pos) {
		return Math.sqrt(Math.pow(this.x-pos.x, 2)+Math.pow(this.y-pos.y, 2));
	}
	

	public Vector2 set(Vector2 pos) {
		this.x=pos.x;
		this.y=pos.y;
		return this;
	}
	public Vector2 set(double x, double y) {
		this.x=x;
		this.y=y;
		return this;
	}
	public Vector2 mover(double derecha, double arriba) {
		x+=derecha;
		y+=arriba;
		return this;
	}
	public Vector2 rotar(Vector2 punto, double cRad) {
		double x = this.x - punto.x;
		double y = this.y - punto.y;
		double ang = Utils.inclinacion2D(x,y);

		x = Math.cos(ang+(cRad*Math.PI))*this.distancia(punto)+punto.x;
		y = Math.sin(ang+(cRad*Math.PI))*this.distancia(punto)+punto.y;
		
		this.x=x;
		this.y=y;
		return this;
	}
	
	//funciones que no actualizan el valor de la instancia pero si retornan el resultado
	
	public Vector2 set$(double x, double y) {
		Vector2 out = this.clone();
		out.x=x;
		out.y=y;
		return out;
	}
	public Vector2 mover$(double derecha, double arriba) {
		Vector2 out = this.clone();
		out.x+=derecha;
		out.y+=arriba;
		return out;
	}
	public Vector2 rotar$(Vector2 punto, double cRad) {
		double x = this.x - punto.x;
		double y = this.y - punto.y;
		double ang = Utils.inclinacion2D(x,y);

		x = Math.cos(ang+(cRad*Math.PI))*this.distancia(punto)+punto.x;
		y = Math.sin(ang+(cRad*Math.PI))*this.distancia(punto)+punto.y;
		
		return new Vector2(x,y);
	}
	public Vector2 clone() {
		return new Vector2(x,y);
	}
	public String toString() {
		return "("+x+";"+y+")";
	}
}
