package ejercicio3;

import java.util.Random;

public class BarcaSynchronized {

	int litrosBarca = 10;
	boolean fin = false;

	public int getLitrosBarca() {
		return litrosBarca;
	}

	public void setLitrosBarca(int litrosBarca) {
		this.litrosBarca = litrosBarca;
	}
	
	
	
	
	public synchronized void achicarAguaMarinero() {
		litrosBarca -= 1;
		System.out.println("El marinero achica. Agua en la barca " + litrosBarca);
		if(litrosBarca == 0) {
		
			System.out.println("\nEL MARINERO SE HA SALVADO");
			fin=true;
		}
	}
	
	public synchronized void olaGolpeaBarco() {
		Random r = new Random();
		int litros = r.nextInt(5)+1;
		litrosBarca+=litros;
		System.out.println("OLA!!! Entran " + litros + " litros. Agua en la barca " + litrosBarca);
		
		if(litrosBarca == 20) {
	
			System.out.println("\nLA BARCA SE HA HUNDIDO");
			fin=true;
		}
		
		
	}
}
