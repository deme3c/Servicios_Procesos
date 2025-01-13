package paq1;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Procesador extends Thread{

	private int id;
	
	private boolean estado;    // true: robot en marcha;   false: robot apagado

	private Cadena cad;
	
	public Procesador(int id, Cadena cad) {
		super();
		this.id= id;
		this.cad = cad;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		Random r = new Random();
		
		this.estado=true;
		
		while(this.estado){
			
			int producto= r.nextInt(4)+1;
			
			int espera = r.nextInt(2)+1;
			
			esperar(1000*espera);
			
			if (!estado) break;
			
			cad.producir(this.id, producto);

				
		}
		
		System.out.println("El robot procesador " + this.id + " ha parado");
		
	}
	
	public void pararRobot() {
		
		this.estado= false;
		
	}
	
	public void esperar(long t) {
		
		try {
			sleep(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
