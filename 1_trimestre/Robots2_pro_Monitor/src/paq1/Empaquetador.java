package paq1;


import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Empaquetador extends Thread{
	
		private int id;
		
		private Cadena cad;
		
		private boolean estado;

		
	public Empaquetador(int id, Cadena cad) {
		super();
		this.id = id;
		this.cad= cad;
		}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		Random r = new Random();
		
		this.estado= true;
		
		while(estado) {
			
			int producto= r.nextInt(4)+1;
			
			if (!estado) break;
			
			cad.empaquetar(this.id, producto);

				
			int espera= r.nextInt(2)+1;
			
			esperar(1000*espera);
			
			
		}
		
		System.out.println("El robot empaquetador " + this.id + " ha parado");
		
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
