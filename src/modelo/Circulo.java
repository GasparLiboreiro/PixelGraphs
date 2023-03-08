package modelo;

public class Circulo extends Objeto{

	public Circulo(Vector2[] puntos) {
		super(Objeto.CIRCULO, puntos);
	}

	public Circulo(Vector2 origen, Vector2 borde) {
		super(Objeto.CIRCULO, new Vector2[] {origen, borde});
	}
	
	public Circulo(Vector2 origen, double radio) {
		super(Objeto.CIRCULO, new Vector2[] {origen, origen.mover$(radio, 0)});
	}


	@Override
	public void draw(int[][] out) {
		if(out==null) return;
		int alto = PixelGraphs.renderer.res;
		int ancho = (int)(PixelGraphs.renderer.res * PixelGraphs.renderer.ratio);
		
		int estimacion = (int)(2 * Math.PI * puntos[0].distancia(puntos[1]) * PixelGraphs.renderer.ppu )+1; // 2 Pi R = perimetro, estimacion de cuantos pixeles tengo que dibujar
		for(int c=0; c<estimacion; c++) {
			double h = Math.cos(((double)(c)/estimacion)*2*Math.PI) *
					Vector2.distancia(
							puntos[0], 
							puntos[1]) 
					+ puntos[0].x;
			
			double k = Math.sin(((double)(c)/estimacion)*2*Math.PI) *
					Vector2.distancia(
							puntos[0], 
							puntos[1]) 
					+ puntos[0].y;

			
			Vector2i pixPos = PixelGraphs.renderer.unitToPixel(new Vector2(h,k));
			
			pixPos.mover(ancho/2, alto/2);
			if(pixPos.x >= 0 && pixPos.y >= 0 && pixPos.x<ancho && pixPos.y<alto)
				out[pixPos.x][pixPos.y] = PRI;
			if(pixPos.x >= 0 && pixPos.y-1 >= 0 && pixPos.x<ancho && pixPos.y-1<alto && out[pixPos.x][pixPos.y-1] == BAC)
				out[pixPos.x][pixPos.y-1] = SEC;
		}
	}

}
