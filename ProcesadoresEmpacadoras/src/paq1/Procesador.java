package paq1;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Procesador extends Thread{

	private int numero;
	private  int []cadenaProductos;
	private  int[] cadenaRobots;
	private static Semaphore mutex;
	private boolean salida[];
	
	public Procesador(int numero, int[] cadenaProductos, int[] cadenaRobots,Semaphore mutex,boolean salida[]) {
		super();
		this.numero = numero;
		this.cadenaProductos = cadenaProductos;
		this.cadenaRobots = cadenaRobots;
		Procesador.mutex = mutex;
		this.salida=salida;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int[] getCadenaProductos() {
		return cadenaProductos;
	}

	public void setCadenaProductos(int[] cadenaProductos) {
		this.cadenaProductos = cadenaProductos;
	}

	public int[] getCadenaRobots() {
		return cadenaRobots;
	}

	public void setCadenaRobots(int[] cadenaRobots) {
		this.cadenaRobots = cadenaRobots;
	}
	
	public void run() {
		
		long inicio=System.currentTimeMillis();
		
		while(!salida[0]) {
			int producto = (int) (Math.random()*4) + 1;
			int espera  =(int) (Math.random()*2)+1;
			parar(espera);
			try {
				mutex.acquire();
				if(!salida[0]) {
					for(int i = 0; i<cadenaProductos.length;i++) {
						if(cadenaProductos[i]==0) {
							cadenaProductos[i]=producto;
							cadenaRobots[i]=this.numero;
							System.out.println("El robot procesador " + this.numero + " ha colocado el producto " + producto + " en la posicion " + i + " de la cadena de montaje" );
							System.out.println(Arrays.toString(cadenaProductos));
							System.out.println(Arrays.toString(cadenaRobots));
							break;
						}
						if(i==4) {
							if(cadenaProductos[4]!=0)
								System.out.println("El robot procesador " + this.numero + " no ha encontradod hueco para colocar el producto");
							
							
							}
						}
				}
				if (salida[0])
					System.out.println("Robot procesador " + this.numero + " apagandose");
	
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.mutex.release();
			
		}
		
		
		
	}
	
	public void parar(int segundos ) {
		
		try {
			Thread.sleep(segundos*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
}
