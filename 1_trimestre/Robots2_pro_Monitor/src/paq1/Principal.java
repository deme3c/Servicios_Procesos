// 3 robots productores y 3 empaquetadores

package paq1;

import java.util.concurrent.Semaphore;

public class Principal {


	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		Cadena cad = new Cadena();
		
		Procesador p1 = new Procesador(1, cad);
		Procesador p2 = new Procesador(2, cad);
		Procesador p3 = new Procesador(3, cad);
		
		Empaquetador e1 = new Empaquetador(1, cad);
		Empaquetador e2 = new Empaquetador(2, cad);
		Empaquetador e3 = new Empaquetador(3, cad);		
		
		System.out.println("LOS ROBOTS PROCESADORES SE PONEN EN MARCHA\n");
		p1.start();
		p2.start();
		p3.start();
		
		Thread.sleep(2500);
		
		System.out.println("LOS ROBOTS EMPAQUETADORES SE PONEN EN MARCHA\n");
		
		e1.start();
		e2.start();
		e3.start();		
		
		Thread.sleep(4000);

		
		System.out.println("¡¡¡¡SE APAGA LA FABRICA!!!!");
		
		
		p1.pararRobot();
		p2.pararRobot();
		p3.pararRobot();
		e1.pararRobot();
		e2.pararRobot();
		e3.pararRobot();
		
		
		
	}

}
