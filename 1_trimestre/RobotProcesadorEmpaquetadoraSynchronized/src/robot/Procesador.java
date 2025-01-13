package robot;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Procesador extends Thread {

	private int id;
	private static int[] robots = new int[5];
	private static int[] productos = new int[5];
	private static boolean cadenaMontajeFin = false;

	private Syncronizado mutex;

	public Procesador(int id, Syncronizado mutex) {
		this.id = id;
		this.mutex = mutex;

	}

	public static int[] getRobots() {
		return robots;
	}

	public static void setRobots(int[] robots) {
		Procesador.robots = robots;
	}

	public int[] getProductos() {
		return productos;
	}

	public void setProductos(int[] productos) {
		Procesador.productos = productos;
	}

	public static boolean isCadenaMontajeFin() {
		return cadenaMontajeFin;
	}

	public static void setCadenaMontajeFin(boolean cadenaMontajeFin) {
		Procesador.cadenaMontajeFin = cadenaMontajeFin;
	}

	@Override
	public void run() {
		super.run();

		while (!cadenaMontajeFin) {

			int espera = (int) (Math.random() * 2) + 1;
			try {
				Thread.sleep(espera * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block

			}

			if (cadenaMontajeFin) {
				break;
			}
			
			int producto = (int) (Math.random() * 4) + 1;
			boolean sitioLibre = true;

			boolean[] encontrado = {false};
			
			for (int i = 0; i < robots.length; i++) {

				sitioLibre = mutex.procesar(producto, sitioLibre, i, id, productos, robots, encontrado);
				if(encontrado[0]) {
					break;
				}
			}
			if (sitioLibre) {
				System.out.println("El productor " + id + " no encuentra sitio para el producto");
			}

		}
		
		System.out.println("El procesador " + id + " se ha apagado");
	}

	public void liberarProducto(int posicion) {
		productos[posicion] = 0;
	}

	public void terminar() {
		cadenaMontajeFin = true;
		

	}
}
