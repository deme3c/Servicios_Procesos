package paq1;

import java.util.Scanner;

public class Contenedor {

	private int contenedor;
	
	private final int totalContenedor=5000;
	
	
	
	public int getContenedor() {
		return contenedor;
	}

	public void setContenedor(int contenedor) {
		this.contenedor = contenedor;
	}

	public synchronized int llenarContenedor(int litrosProducidos) {
		
		int litrosVertidos = 0;

		int huecoContenedor = this.totalContenedor - contenedor;
		
	//	System.out.println("Id: " + this.id + " huecoContenedor: " + huecoContenedor);

		if (huecoContenedor >= litrosProducidos) {
			
			contenedor += litrosProducidos;
			// esperaVertido();
		//	this.totalLitrosVertidos += litrosProducidos;
			litrosVertidos= litrosProducidos;
		} else {
			contenedor += huecoContenedor;
			// esperaVertido();
		//	this.totalLitrosVertidos += huecoContenedor;
			litrosVertidos= huecoContenedor;
	
		}


		return litrosVertidos;

	}
	
	public synchronized boolean isLleno() {
		
		boolean resp = false;
		
		if (contenedor>=5000) resp=true;
		
		return resp;
		
	}

	public synchronized boolean testear() {
		
		if (contenedor < totalContenedor) {
			contenedor = contenedor - 1;
			


			if (contenedor % 10 == 9) {

				Scanner sc = new Scanner(System.in);
				System.out.println("Error en la muestra, maquinas limpiandose");
				espera2();

			}

			return false;

		} else {
			return true;
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
