package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import modelo.Objeto;
import modelo.PixelGraphs;
import modelo.Punto;
import modelo.Vector2;
import modelo.Vector2i;
import vista.JPanelHandler;

public class GameInputManager implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	
	private Vector2i ledToPixel(Vector2i ledPos) {
		Vector2i tamVentana = JPanelHandler.tamVentana;
		int alto = PixelGraphs.renderer.res;
		int ancho = (int) (PixelGraphs.renderer.res * PixelGraphs.renderer.ratio);
		
		
		int pixelTam;
		if(tamVentana.y/alto < tamVentana.x/ancho) { //limitado verticalmente
			pixelTam = tamVentana.y/alto;
		} else { //limitado horizontalmente
			pixelTam = tamVentana.x/ancho;
		}
		
		Vector2i separacion = new Vector2i(//  separacion vv
				(tamVentana.x-ancho*pixelTam)/2,  //   |      [     display pixeles      ]      |
				(tamVentana.y-alto*pixelTam)/2);

		Vector2i out = new Vector2i(
				(ledPos.x-separacion.x)/pixelTam,
				(tamVentana.y-ledPos.y-separacion.y)/pixelTam);
		
		
		return out;
	}
	
	private Vector2 ledToUnit(Vector2i ledPos) {
		int alto = PixelGraphs.renderer.res;
		int ancho = (int) (PixelGraphs.renderer.res * PixelGraphs.renderer.ratio);
		
		return PixelGraphs.renderer.pixelToUnit(
				ledToPixel(
						new Vector2i(ledPos.x, ledPos.y)).mover(-ancho/2, -alto/2)
				);
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_Z:
			if(PixelGraphs.escena.size()>0)
				PixelGraphs.escena.remove(PixelGraphs.escena.size()-1);
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			PixelGraphs.mano = Objeto.getObjetoFromType(
					PixelGraphs.tipoSelec, 
					new Vector2[] {
							ledToUnit(new Vector2i(e.getX(), e.getY())), 
							ledToUnit(new Vector2i(e.getX(), e.getY())), 
						});
			
			PixelGraphs.mano.PRI=PixelGraphs.mano.SEC;
			PixelGraphs.mano.SEC=PixelGraphs.mano.BAC;
		} else if(e.getButton() == MouseEvent.BUTTON2) {
			PixelGraphs.mano = null;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(PixelGraphs.mano!=null) {
			PixelGraphs.mano.PRI = Objeto.COLORES[0];
			PixelGraphs.mano.SEC = Objeto.COLORES[1];
			PixelGraphs.mano.BAC = Objeto.COLORES[2];
			PixelGraphs.escena.add(PixelGraphs.mano);
			PixelGraphs.mano = null;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(PixelGraphs.mano!=null) {
			PixelGraphs.mano.puntos[1].set(ledToUnit(new Vector2i(e.getX(), e.getY())));
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	private Vector2 screenToUnits(Vector2 coords) {
		
		
		
		return null;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation() > 0) {
			PixelGraphs.tipoSelec++;
			if(PixelGraphs.tipoSelec>PixelGraphs.maxTipo)
				PixelGraphs.tipoSelec=0;
		} else {
			PixelGraphs.tipoSelec--;
			if(PixelGraphs.tipoSelec<0)
				PixelGraphs.tipoSelec=PixelGraphs.maxTipo;
		}
	}
	
}
