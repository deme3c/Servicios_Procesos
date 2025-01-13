package ejercicio1;

import java.util.Random;

public class Impresora extends Thread{

	private int id;
	private int pags;
	private long tiempo;
	
	public Impresora(int id) {
		super();
		this.id = id;
	}

	public int getPags() {
		return pags;
	}

	public void setPags(int pags) {
		this.pags = pags;
	}

	public long getTiempo() {
		return tiempo;
	}

	public void setTiempo(long tiempo) {
		this.tiempo = tiempo;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		Random r = new Random();
		long tiempoInicial = System.currentTimeMillis();

		System.out.println("LA IMPRESORA " + id + " EMPIEZA A IMPRIMIR");
		
		for (int i=0; i<pags; i++) {
			int espera = r.nextInt(700) + 100;
			System.out.println(espera);
			try {
				Thread.sleep(espera);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("La impresora " + id + " ha impreso la hoja " + (i+1) + " del documento");
		}
	
		System.out.println("LA IMPRESORA " + id + " HA ACABADO A IMPRIMIR");
		long tiempoFinal = System.currentTimeMillis();
		
		tiempo = tiempoFinal-tiempoInicial;
	}

}
