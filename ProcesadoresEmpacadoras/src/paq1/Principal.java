package paq1;

import java.util.concurrent.Semaphore;

public class Principal {
	static Semaphore mutex = new Semaphore(1, false);
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int cadenaProductos[]=new int [5];
		int cadenaRobots[] = new int [5];
		String cadenaEmpaquetados []= {"","","","",""};
		boolean salida[]= {false};
		
		
		Procesador p1 = new Procesador(1, cadenaProductos, cadenaRobots,mutex,salida);
		Procesador p2 = new Procesador(2, cadenaProductos, cadenaRobots,mutex,salida);
		Procesador p3 = new Procesador(3, cadenaProductos, cadenaRobots,mutex,salida);
		
		Empaquetador e1  =new Empaquetador(1, cadenaProductos, cadenaRobots, cadenaEmpaquetados,mutex,salida);
		Empaquetador e2  =new Empaquetador(2, cadenaProductos,cadenaRobots, cadenaEmpaquetados,mutex,salida);
		Empaquetador e3  =new Empaquetador(3, cadenaProductos,cadenaRobots, cadenaEmpaquetados,mutex,salida);
		
		p1.start();
		p2.start();
		p3.start();
		e1.start();
		e2.start();
		e3.start();
		
		
		
		
	}

}
