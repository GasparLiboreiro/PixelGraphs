package modelo;

public class Punto extends Objeto{

	public Punto(Vector2[] puntos) {
		super(Objeto.PUNTO, puntos);
	}

	public Punto(Vector2 punto) {
		super(Objeto.PUNTO, new Vector2[] {punto});
	}


	@Override
	public void draw(int[][] out) {
		if(out==null) return;
		int alto = PixelGraphs.renderer.res;
		int ancho = (int)(PixelGraphs.renderer.res * PixelGraphs.renderer.ratio);
		
		Vector2i pixPos = PixelGraphs.renderer.unitToPixel(this.puntos[0]);
		pixPos.mover(ancho/2, alto/2);
		
		if( pixPos.x >= 0 && pixPos.y >= 0 && pixPos.x<ancho && pixPos.y<alto) {
			out[pixPos.x][pixPos.y] = PRI;
		}
		if( pixPos.x >= 0 && pixPos.y-1 >= 0 && pixPos.x<ancho && pixPos.y-1<alto && 
			out[pixPos.x][pixPos.y-1] == BAC) {
			out[pixPos.x][pixPos.y-1] = SEC;
		}
	}
	

}
