package modelo;

public class Utils {
	public static double inclinacion2D(double x, double y) {
		double inclinacion=0;
		//System.out.println("input x:"+x+" y:"+y);
		if(x!=0) {
			inclinacion = Math.atan(y / x);
			
			if(x<0)
				inclinacion+=Math.PI;
		}
		else if(y>0)
			inclinacion = 0.5 * Math.PI;
		else if(y<=0)
			inclinacion = 1.5 * Math.PI;
		
		
		return inclinacion;
	}
}
