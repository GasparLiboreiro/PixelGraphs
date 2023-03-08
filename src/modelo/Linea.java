package modelo;

public class Linea extends Objeto {

	public Linea(Vector2[] puntos) {
		super(Objeto.LINEA, puntos);
	}

	public Linea(Vector2 p1, Vector2 p2) {
		super(Objeto.LINEA, new Vector2[] {p1, p2});
	}

	@Override
	public void draw(int[][] out) {
		if(out==null) return;
		int alto = PixelGraphs.renderer.res;
		int ancho = (int)(PixelGraphs.renderer.res * PixelGraphs.renderer.ratio);
		
		
		double dx = puntos[0].x-puntos[1].x;
		double dy = puntos[0].y-puntos[1].y;
		double angulo = dx==0?1000000000:dy/dx; //si dx es 0 la divicion deveria dar infinito :pp
		double bias = puntos[0].y-(angulo * puntos[0].x);// y - ( ang * x ) -> quitarle a y la altura recorrida desde el origen hasta x
		
		if(Math.abs(dx)>=Math.abs(dy)) {
			
			int inicio = PixelGraphs.renderer.unitToPixel(puntos[0]).x + ancho/2; 
			int fin = PixelGraphs.renderer.unitToPixel(puntos[1]).x + ancho/2;
			if(PixelGraphs.renderer.unitToPixel(puntos[1]).x + ancho/2 < inicio) {
				inicio = PixelGraphs.renderer.unitToPixel(puntos[1]).x + ancho/2;
				fin = PixelGraphs.renderer.unitToPixel(puntos[0]).x + ancho/2;
			}
			for(int h=inicio; h<=fin; h++) {
				double x = PixelGraphs.renderer.pixelToUnit(new Vector2i(h-ancho/2,0)).x;// pasar h de pixeles a units
				double y = x*angulo+bias;
				
				
				Vector2i pixPos = PixelGraphs.renderer.unitToPixel(new Vector2(x,y));
				pixPos.mover(ancho/2, alto/2);
				if(pixPos.x >= 0 && pixPos.y >= 0 && pixPos.x<ancho && pixPos.y<alto)
					out[pixPos.x][pixPos.y] = PRI;
				if(pixPos.x >= 0 && pixPos.y-1 >= 0 && pixPos.x<ancho && pixPos.y-1<alto && out[pixPos.x][pixPos.y-1]==BAC) {
					out[pixPos.x][pixPos.y-1] = SEC;
				}
			}
		} else {

			int inicio = PixelGraphs.renderer.unitToPixel(puntos[0]).y + alto/2;
			int fin = PixelGraphs.renderer.unitToPixel(puntos[1]).y + alto/2;
			if(PixelGraphs.renderer.unitToPixel(puntos[1]).y + alto/2 < inicio) {
				inicio = PixelGraphs.renderer.unitToPixel(puntos[1]).y + alto/2;
				fin = PixelGraphs.renderer.unitToPixel(puntos[0]).y + alto/2;
			}
			
			for(int k=inicio; k<=fin; k++) {
				
				double y = PixelGraphs.renderer.pixelToUnit(new Vector2i(0,k-alto/2)).y;// pasar k de pixeles a units
				double x = (y-bias)/angulo;
				
				
				Vector2i pixPos = PixelGraphs.renderer.unitToPixel(new Vector2(x,y));
				pixPos.mover(ancho/2, alto/2);
				if(pixPos.x >= 0 && pixPos.y >= 0 && pixPos.x<ancho && pixPos.y<alto)
					out[pixPos.x][pixPos.y] = PRI;
				if(pixPos.x >= 0 && pixPos.y-1 >= 0 && pixPos.x<ancho && pixPos.y-1<alto && out[pixPos.x][pixPos.y-1]==BAC) {
					out[pixPos.x][pixPos.y-1] = SEC;
				}
			}
		}
	}
}
