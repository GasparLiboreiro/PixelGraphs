package modelo;

public class RenderHandler {
	public int res = 100; //altura en pixeles
	public double ratio = 16d/9; //relacion anchura altura (16d porque sino es divicion de 2 ints y retorna un int)
	public int ppu = 10; //pixels per unit
	
	
	public Vector2i unitToPixel(Vector2 cor) {
		Vector2i out = new Vector2i(0,0);
		out.x = (int) Math.round(cor.x * ppu) - PixelGraphs.cam.x;
		out.y = (int) Math.round(cor.y * ppu) - PixelGraphs.cam.y;
		return out;
	}
	
	
	public Vector2 pixelToUnit(Vector2i pixs) {
		Vector2 out = new Vector2(0,0);
		out.x = (double)(pixs.x + PixelGraphs.cam.x)/ppu ;
		out.y = (double)(pixs.y + PixelGraphs.cam.y)/ppu ;
		return out;
	}
	
	public void dibuarEjes(int[][] out, boolean dots) {
		int alto = res;
		int ancho = (int)(res*ratio);
		
		
		Vector2i origen = unitToPixel(Vector2.CERO);
		origen.mover(ancho/2, alto/2);
		for(int x = origen.x; x<ancho; x+=ppu) {
			setOut(x, origen.y-1, 1, out);
			setOut(x, origen.y  , 1, out);
			setOut(x, origen.y+1, 1, out);
		}
		
		for(int x = origen.x; x>=0; x-=ppu) {
			setOut(x, origen.y-1, 1, out);
			setOut(x, origen.y  , 1, out);
			setOut(x, origen.y+1, 1, out);
		}
		
		for(int y = origen.y; y<alto; y+=ppu) {
			setOut(origen.x-1, y, 1, out);
			setOut(origen.x  , y, 1, out);
			setOut(origen.x+1, y, 1, out);
		}
		
		for(int y = origen.y; y>=0; y-=ppu) {
			setOut(origen.x-1, y, 1, out);
			setOut(origen.x  , y, 1, out);
			setOut(origen.x+1, y, 1, out);
		}
		
		if(dots) {
			for(int k = 1; origen.y+k*ppu < alto || origen.y-k*ppu >= 0; k++) {
				for(int h = 1; origen.x+h*ppu < ancho || origen.x-h*ppu >= 0; h++) {
					setOut(origen.x + h*ppu, origen.y + k*ppu, 1, out);
					setOut(origen.x - h*ppu, origen.y + k*ppu, 1, out);
					setOut(origen.x + h*ppu, origen.y - k*ppu, 1, out);
					setOut(origen.x - h*ppu, origen.y - k*ppu, 1, out);
				}
			}
		}
		
			
	}
	

	private void setOut(int x, int y, int val, int[][] out) {
		if(inBounds(x, y)){
			out[x][y]=val;
		}	
	}
	
	
	
	private boolean inBounds(int x, int y) {
		if(x>=0 && y>=0 && x<(int)(res*ppu) && y<res) {
			return true;
		} else {
			return false;
		}
	}
	
}
