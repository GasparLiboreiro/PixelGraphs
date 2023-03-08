package modelo;

public class Recta extends Objeto{

	public Recta(Vector2[] puntos) {
		super(Objeto.RECTA, puntos);
	}

	public Recta(Vector2 p1, Vector2 p2) {
		super(Objeto.RECTA, new Vector2[] {p1, p2});
	}


	@Override
	public void draw(int[][] out) {
		if(out==null) return;
		int alto = PixelGraphs.renderer.res;
		int ancho = (int)(PixelGraphs.renderer.res * PixelGraphs.renderer.ratio);
		
		
		double dx = puntos[0].x-puntos[1].x;
		double dy = puntos[0].y-puntos[1].y;
		double angulo = dx==0?1000000000:dy/dx;
		double bias = (puntos[0].y+(angulo * (-puntos[0].x)));// * PixelGraphs.ppu; // y + ( ang * -x ) -> quitarle a y la altura recorrida desde el origen hasta x, si x es positivo tiene que volver pasos y si es negativo tiene que avanzar, por eso invierto x
		//System.out.println("\nbias:"+bias+"\nangulo:"+angulo+"\ndx:"+dx+"\ndy:"+dy);
		if(Math.abs(dx)>Math.abs(dy)) {
			
			int inicio = (int) Math.round(PixelGraphs.cam.x*PixelGraphs.renderer.ppu); // calcular por el desfase de la camara
			
			
			for(int h=inicio; h<inicio+ancho; h++) {
				double x = (double)(h-ancho/2)/PixelGraphs.renderer.ppu;
				double y = x*angulo+bias;
				
				//System.out.println("h:"+h+"\nx:"+x+"\ny:"+y);
				
				Vector2i pixPos = PixelGraphs.renderer.unitToPixel(new Vector2(x,y));
				pixPos.mover(ancho/2, alto/2);
				if(pixPos.x >= 0 && pixPos.y >= 0 && pixPos.x<ancho && pixPos.y<alto)
					out[pixPos.x][pixPos.y] = PRI;
				
				if(pixPos.x >= 0 && pixPos.y-1 >= 0 && pixPos.x<ancho && pixPos.y-1<alto && 
					out[pixPos.x][pixPos.y-1]==BAC) {
					out[pixPos.x][pixPos.y-1] = SEC;
				}
			}
		} else {
			int inicio = (int) Math.round(PixelGraphs.cam.y*PixelGraphs.renderer.ppu); // calcular por el desfase de la camara
			
			for(int k=inicio; k<inicio+alto; k++) {
				
				double y = (double)(k-alto/2)/PixelGraphs.renderer.ppu;
				double x = (y-bias)*Math.pow(angulo,-1);
				
				
				Vector2i pixPos = PixelGraphs.renderer.unitToPixel(new Vector2(x,y));
				pixPos.mover(ancho/2, alto/2);
				if(pixPos.x >= 0 && pixPos.y >= 0 && pixPos.x<ancho && pixPos.y<alto)
					out[pixPos.x][pixPos.y] = PRI;
				
				if( pixPos.x >= 0 && pixPos.y-1 >= 0 && pixPos.x<ancho && pixPos.y-1<alto && 
				    out[pixPos.x][pixPos.y-1]==BAC) {
					out[pixPos.x][pixPos.y-1] = SEC;
				}
			}
		}
	}

}
