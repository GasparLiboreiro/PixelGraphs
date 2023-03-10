package modelo;

import javax.swing.JPanel;

public class GameLoop extends Thread{
    private static final double MAX_UPS = 200;
    private static final double UPS_DELAY = 1000/MAX_UPS;
    public boolean isRunning = false;
    private JPanel panel;
    private PixelGraphs game;
    private double averageUPS = -1;
    private double averageFPS = -1;

    public GameLoop(PixelGraphs game, JPanel panel) {
    	this.game=game;
    	this.panel=panel;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();

        int updateCount = 0;
        int frameCount = 0;

        long startTime;
        long elapsedTime;
        long sleepTime;
        long updateTime;


        startTime = System.currentTimeMillis();
        updateTime = System.currentTimeMillis();
        while(isRunning) {
			//update y render
        	game.update((double)(System.currentTimeMillis()-updateTime)/1000);
			updateTime = System.currentTimeMillis();
			updateCount++;
			
			panel.repaint();
			frameCount++;
			
			elapsedTime = System.currentTimeMillis() - startTime;
			sleepTime = (long)(updateCount * UPS_DELAY) - elapsedTime;
			if(sleepTime > 0){
			    try {
			        sleep(sleepTime);
			    } catch (InterruptedException e) {
			        e.printStackTrace();
			    }
			}
			// si el procesamiento no da a basto, podemos elejutar updates sin renderizar
			while(sleepTime < 0 && updateCount < MAX_UPS-1) {
				game.update((double)(System.currentTimeMillis()-updateTime)/1000);
				updateTime = System.currentTimeMillis();
			    updateCount++;
			    elapsedTime = System.currentTimeMillis() - startTime;
			    sleepTime = (long)(updateCount * UPS_DELAY) - elapsedTime;
			}
			
			// este if hace que toda la logica del game loop tenga sentido, gracias al if,  updateCount y frameCount representan la cantidad de frames/updates que hubieron desde el anterior segundo(o mas facil, en el segundo actual) y que 'elapsedTime' represente la cantidad de milisegundos que pasaron desde el ultimo segundo, haciendo que cosas como '(long)(updateCount * UPS_DELAY) - elapsedTime' tenga sentido
			if(elapsedTime >= 1000) {                                           // startTime solamente cambia aca adentro, entonces con cada loop que no se entre aca, elapsedTime se va haciendo cada vez mas grande, hasta que es mayor a 1000 y entra aca, sabiendo que aca se entra cada 1 segundo, podemos hacer los calculos de UPS y FPS
			    averageUPS = updateCount / (1E-3 * elapsedTime);
			    averageFPS = frameCount / (1E-3 * elapsedTime);
			    updateCount = 0;
			    frameCount = 0;
			    startTime = System.currentTimeMillis();
			}
        }
    }

    public void pauseLoop() {
        isRunning=false;
        try {
            join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
