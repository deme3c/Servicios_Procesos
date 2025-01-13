package robot;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Procesador extends Thread {

	private int id;
	private static int[] robots = new int[5];
	private static int[] productos = new int[5];
	private static boolean cadenaMontajeFin = false;

	private Semaphore mutex;

	public Procesador(int id, Semaphore mutex) {
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

			int producto = (int) (Math.random() * 4) + 1;
			boolean sitioLibre = true;
			try {
				mutex.acquire();

				for (int i = 0; i < robots.length; i++) {

					if (productos[i] == 0) {
						robots[i] = id;
						productos[i] = producto;
						sitioLibre = false;
						System.out.println("El robot productor " + id + " ha colocado el producto " + producto
								+ " en la posicion " + i + " de la cadena de montaje");
						System.out.println("Cadena de montaje [Robots] " + Arrays.toString(robots));
						System.out.println("Cadena de montaje [Produc] " + Arrays.toString(productos) + "\n");
						break;

					}
				}
				if (sitioLibre) {
					System.out.println("El productor " + id + " no encuentra sitio para el producto");
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				mutex.release();
			}

		}
	}

	public void liberarProducto(int posicion) {
		productos[posicion] = 0;
	}

	public void terminar() {
		cadenaMontajeFin = true;
		System.out.println("El procesador " + id + " se ha apagado");

	}
}
