package robot;

import java.util.concurrent.Semaphore;

public class PrincipalRobotProcesador {

	static Syncronizado mutex = new Syncronizado();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Procesador robot1 = new Procesador(1, mutex);
		Procesador robot2 = new Procesador(2, mutex);
		Procesador robot3 = new Procesador(3, mutex);
		
		Empaquetador empaquetador1 = new Empaquetador(1, mutex, robot1);
		Empaquetador empaquetador2 = new Empaquetador(2, mutex, robot1);
		Empaquetador empaquetador3 = new Empaquetador(3, mutex, robot1);
		
        System.out.println("LOS ROBOTS PROCESADORES SE PONEN EN MARCHA");
		
		robot1.start();
		robot2.start();
		robot3.start();
		
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("LOS ROBOTS EMPAQUETADORES SE PONEN EN MARCHA");
		
		empaquetador1.start();
		empaquetador2.start();
		empaquetador3.start();
		
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        robot1.terminar();
        robot2.terminar();
        robot3.terminar();

        empaquetador1.terminar();
        empaquetador2.terminar();
        empaquetador3.terminar();
        
        try {
			robot1.join();
			robot2.join();
			robot3.join();
			empaquetador1.join();
			empaquetador2.join();
		    empaquetador3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("¡¡¡¡SE APAGA LA FABRICA!!!!");
		
	}

}
