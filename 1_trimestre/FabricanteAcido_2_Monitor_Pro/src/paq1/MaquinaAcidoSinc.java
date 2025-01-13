
package paq1;

import java.util.*;
import java.util.concurrent.Semaphore;

public class MaquinaAcidoSinc extends Thread{
	
	private int id;
	private int litros_max;
	
	Contenedor c;
	
	private final int totalContenedor=5000;
	
	private int totalLitrosVertidos;
	private int totalLitrosProducidos;
	
	private int huecoContenedor;
	
	private String salida;
	
	

	public MaquinaAcidoSinc(int id, int litros_max, Contenedor c) {
		super();
		this.id = id;
		this.litros_max= litros_max;
		this.c = c;
	}


	public int getTotalLitrosVertidos() {
		return totalLitrosVertidos;
	}



	public void setTotalLitrosVertidos(int totalLitrosVertidos) {
		this.totalLitrosVertidos = totalLitrosVertidos;
	}

	

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		super.run();
		
		boolean fin=false;
		
		int litrosProducidos=0;

		while (!fin) {

			litrosProducidos = produciendoAcido();


				this.totalLitrosProducidos += litrosProducidos;

				int lit_vert= c.llenarContenedor(litrosProducidos);
				totalLitrosVertidos+=lit_vert;
				
				fin=c.isLleno();
				
				
		}
		
		System.out.println("Contenedor lleno, cantidad Contenedor: " + c.getContenedor());
		System.out.println("Litros producidos por la máquina " + id + ": " + this.totalLitrosProducidos);
		System.out.println("Litros vertidos por la máquina " + id + ": " + this.totalLitrosVertidos);
//		System.out.println("hueco contenedor " + huecoContenedor);
//		System.out.println("ultimos litros producidos " + litrosProducidos);

	}
	
	
	
	
	

	
int produciendoAcido() {
		
		Random r = new Random();
		
		int pinturaProducida= r.nextInt(1,this.litros_max);
		// int pinturaProducida= 2;
		try {
			Thread.sleep((long)(1*pinturaProducida));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pinturaProducida; 
		
	}
	
	public void esperaVertido() {
		
		try {
			Thread.sleep((5));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	

}
