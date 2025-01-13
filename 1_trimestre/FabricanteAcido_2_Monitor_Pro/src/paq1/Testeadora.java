package paq1;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Testeadora extends Thread{

	
	private int contenedor[];
	private boolean parar[];
	
	private int muestras;
	
	private final int totalContenedor=5000;
	
	
	
	
	
	public int getMuestras() {
		return muestras;
	}


	public void setMuestras(int muestras) {
		this.muestras = muestras;
	}


	public Testeadora(int[] contenedor) {
		super();
		this.contenedor = contenedor;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		boolean fin = false;

		while (!fin) {

			espera();

			if (contenedor[0] < totalContenedor) {
				contenedor[0] = contenedor[0] - 1;
				this.muestras++;
				if ((int) Math.random() * 10 == 9) {

					Scanner sc = new Scanner(System.in);
					System.out.println("Error en la muestra, maquinas limpiandose");
					espera2();

				}
			} else
				fin = true;

		}

		System.out.println("\nTesteadora parada");

	}

	
	public void espera() {
		
		Random r = new Random();
		
		long espera = r.nextLong(30,41);
		
		try {
			Thread.sleep(espera);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void espera2() {
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
