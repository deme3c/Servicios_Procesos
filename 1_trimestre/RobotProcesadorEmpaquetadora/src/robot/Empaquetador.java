package robot;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Empaquetador extends Thread {

	private int id;
	private String[] empaquetados = new String[5];
	private Semaphore mutex;
	Procesador robot;
	private boolean fin = false;

	public Empaquetador(int id, Semaphore mutex, Procesador robot) {
		super();
		this.id = id;
		this.mutex = mutex;
		this.robot = robot;
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

		try {
			mutex.acquire();

			int producto = (int) (Math.random() * 4) + 1;
			System.out.println("El empaquetador " + id + " se dispone a empaquetar el producto " + producto);

			boolean encontrado = false;

			for (int i = 0; i < robot.getProductos().length; i++) {

				if (robot.getProductos()[i] == producto) {
					System.out.println("Poducto " + producto + " encontrado ");
					reinitEmpaquetados();
					empaquetados[i] = "*";
					robot.liberarProducto(i);
					encontrado = true;
					System.out.println("Cadena de montaje [ProEmp] " + Arrays.toString(empaquetados));
					System.out.println("Cadena de montaje [Produc] " + Arrays.toString(robot.getProductos()) + "\n");
					break;
				}

			}
			if (!encontrado) {
                System.out.println("Producto " + producto + " no encontrado");
               }

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mutex.release();
		}
		
		}

	}
	
	public void reinitEmpaquetados() {
		for (int i = 0; i < empaquetados.length; i++) {
			empaquetados[i] = "";
		}
	}
	
	public void terminar() {
		fin = true;
		System.out.println("El robot " + id + " se ha apagado");
	}

}
