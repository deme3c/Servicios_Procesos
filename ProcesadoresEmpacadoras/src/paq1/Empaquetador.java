package paq1;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Empaquetador extends Thread{

	private int numero;
	private  int []cadenaProductos;
	private  int[] cadenaRobots;
	private  String[] cadenaEmpaquetados;
	private static Semaphore mutex;
	private boolean salida[];
	public Empaquetador(int numero, int[] cadenaProductos,int[] cadenaRobots, String[] cadenaEmpaquetados,Semaphore mutex,boolean salida[]) {
		super();
		this.numero = numero;
		this.cadenaProductos = cadenaProductos;
		this.cadenaRobots=cadenaRobots;
		this.cadenaEmpaquetados = cadenaEmpaquetados;
		Empaquetador.mutex = mutex;
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
	public String [] getCadenaEmpaquetados() {
		return cadenaEmpaquetados;
	}
	public void setCadenaEmpaquetados(String[] cadenaEmpaquetados) {
		this.cadenaEmpaquetados = cadenaEmpaquetados;
	}
	public  Semaphore getMutex() {
		return mutex;
	}
	public void setMutex(Semaphore mutex) {
		this.mutex = mutex;
	}
	
	public void run() {
		
		parar(25);
		long inicio = System.currentTimeMillis();
		while (!salida[0]) {
			int elegido = (int) (Math.random() * 4) + 1;
			int parar = (int) ((Math.random() * 2) + 1)*10;
			try {
				mutex.acquire();
				if (!salida[0]) {
					System.out.println(
							"El empaquetador " + this.numero + " se dispone a empaquetar un producto " + elegido);
					for (int i = 0; i < cadenaProductos.length; i++) {
						if (cadenaProductos[i] == elegido) {
							System.out.println("Producto " + elegido + " encontrado ");
							cadenaProductos[i] = 0;
							cadenaEmpaquetados[i] = "*";
							System.out.println("Cadena de montaje [ProEmp]" + Arrays.toString(cadenaEmpaquetados));
							System.out.println("Cadena de montaje [Produc]" + Arrays.toString(cadenaProductos));
							cadenaEmpaquetados[i] = "";
							cadenaRobots[i]=0;
							break;

						}
						if(i==4) {
							if(cadenaProductos[i] != elegido) {
								System.out.println(
										"No se ha encontrado ningun producto " + elegido + " en la cadena de montaje");
							
							}
						}
						
					}
					if ((System.currentTimeMillis() - inicio) > 4000) {
						salida[0]=true;
					}
				}
				

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mutex.release();
			if (!salida[0]) 
				parar(parar);
		}
			System.out.println("Robot " + this.numero + " apagandose");

	}
	public void parar(int segundos ) {
		
		try {
			Thread.sleep(segundos*100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}
