package robot;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Empaquetador extends Thread {

	private int id;
	private String[] empaquetados = new String[5];
	Procesador robot;
	private boolean fin = false;
	
	private Syncronizado mutex;

	public Empaquetador(int id,Syncronizado mutex, Procesador robot) {
		super();
		this.id = id;
		this.robot = robot;
		this.mutex = mutex;
	}

	
	@Override
	public void run() {
		super.run();

		while (!fin) {
			
		
		
		int espera = (int) (Math.random() * 2) + 1;

		try {
			Thread.sleep(espera * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}

		if(fin) {
			break;
		}

			int producto = (int) (Math.random() * 4) + 1;
			System.out.println("El empaquetador " + id + " se dispone a empaquetar el producto " + producto);

			boolean encontrado = false;
			boolean[] empaquetado = {false};
			
			
			for (int i = 0; i < robot.getProductos().length; i++) {

				encontrado = mutex.empaquetar(producto, encontrado, i, empaquetados, robot, empaquetado);
				if (empaquetado[0]) {
					break;
				}

			}
			if (!encontrado) {
                System.out.println("Producto " + producto + " no encontrado");
               }

	
		
		}

	}


	public void terminar() {
		fin = true;
		System.out.println("El robot " + id + " se ha apagado");
	}

}
