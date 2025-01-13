package paq1;

import java.util.*;

public class MaquinaAcido extends Thread{
	
	private int id;
	private int litros_max;
	
	static int[] contenedor;
	private final int totalContenedor=5000;
	
	private int totalLitrosVertidos;
	private int totalLitrosProducidos;
	
	

	public MaquinaAcido(int id) {
		super();
		this.id = id;
	}


	public MaquinaAcido(int id, int litros_max, int[] contenedor) {
		super();
		this.id = id;
		this.litros_max = litros_max;
		this.contenedor = contenedor;
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
		super.run();
		
		int i=0;
		
		boolean fin=false;

		while (!fin) {

			int litrosProducidos = produciendoAcido();
			
		//	System.out.println("Cantidad litros producidos por la máquina " + id + ": " + litrosProducidos);
		//	System.out.println( id + ": " + litrosProducidos);

			this.totalLitrosProducidos += litrosProducidos;

			int huecoContenedor = this.totalContenedor - contenedor[0];
			
		//	System.out.println("hueco contenedor: " + huecoContenedor);

			if (huecoContenedor >= litrosProducidos) {

				contenedor[0] += litrosProducidos;
				//esperaVertido();
				this.totalLitrosVertidos += litrosProducidos;
			} else {

				contenedor[0] += huecoContenedor;
				//esperaVertido();
				this.totalLitrosVertidos += huecoContenedor;

			}
			if (contenedor[0] >= totalContenedor)
				fin=true;
			
			//System.out.println(i++);
		}
		
		System.out.println("Contenedor lleno, cantidad Contenedor: " + contenedor[0]);
		System.out.println("Litros producidos por la máquina " + id + ": " + this.totalLitrosProducidos);
		System.out.println("Litros vertidos por la máquina " + id + ": " + this.totalLitrosVertidos);
		
	}
	
	
	
	
	
	int produciendoAcido() {
		
		Random r = new Random();
		
		int pinturaProducida= r.nextInt(1,this.litros_max+1);
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
