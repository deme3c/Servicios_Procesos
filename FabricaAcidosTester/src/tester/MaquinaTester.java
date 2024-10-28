package tester;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class MaquinaTester extends Thread {

	private int muestrasTomadas;
	private int contenedorActual[];
	
	private static Semaphore mutex;

	public int[] getContenedorActual() {
		return contenedorActual;
	}

	public void setContenedorActual(int[] contenedorActual) {
		this.contenedorActual = contenedorActual;
	}

	public int getMuestrasTomadas() {
		return muestrasTomadas;
	}

	public void setMuestrasTomadas(int muestrasTomadas) {
		this.muestrasTomadas = muestrasTomadas;
	}

	public MaquinaTester(int[] contenedorActual, Semaphore mutex) {
		super();
		this.contenedorActual = contenedorActual;
		this.mutex = mutex;
	}

	@Override
	public void run() {
		tomarMuestra();

	}

	public void tomarMuestra() {

		Random r = new Random();

		boolean salir = false;
		while (!salir) {
			

			
			int extraerTiempo = r.nextInt(11) + 30;
			try {
				Thread.sleep(extraerTiempo);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			if (buscarError()) {
				try {
					Thread.sleep(50);
					System.out.println("Error en la muestra, maquinas limpiandose");
					System.out.println(contenedorActual[0]);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				mutex.acquire();
				if (contenedorActual[0] == 5000) {
					salir = true;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			contenedorActual[0]--;

			muestrasTomadas++;

			
			mutex.release();
		}

	}

	public boolean buscarError() {
		Random r = new Random();
		int error = r.nextInt(10) + 1;
		if (error == 5) {
			return true;
		} else {
			return false;
		}

	}

}
