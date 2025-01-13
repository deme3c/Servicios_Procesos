package paq1;
import java.util.*;
import java.util.concurrent.Semaphore;

public class TesteadoraSinc extends Thread{

	
	private Contenedor c;
	
	private int muestras;
	
	private final int totalContenedor=5000;
	
	public TesteadoraSinc(Contenedor c) {
		super();
		this.c = c;
	}


	public int getMuestras() {
		return muestras;
	}


	public void setMuestras(int muestras) {
		this.muestras = muestras;
	}





	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		boolean fin=false;
		
		while(!fin) {
			
			espera();
			
			fin = c.testear();
			if (!fin) muestras++;

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
