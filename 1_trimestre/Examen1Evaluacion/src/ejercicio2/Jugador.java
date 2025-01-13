package ejercicio2;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Jugador extends Thread {

	private int identificador;
	private static int dadoComun = 1;

	private static int muertos = 0;

	private int dado;
	private boolean vivo = true;

	private Semaphore mutex;
	Random r = new Random();

	public Jugador(int id, Semaphore mutex) {
		super();
		this.identificador = id;
		this.mutex = mutex;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		while (vivo) {
			
			int espera = r.nextInt(2)+1;
			try {
				Thread.sleep(espera*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (muertos == 2) {
				vivo = false;
				System.out.println("\nEL GANADOR ES EL JUGADOR " + identificador);
				break;
			}
			
			try {
				mutex.acquire();
				if (muertos == 2) {
					vivo = false;
					System.out.println("\nEL GANADOR ES EL JUGADOR " + identificador);
					break;
				}

				dado = r.nextInt(6) + 1;
				System.out.println("El jugador " + identificador + " ha sacado " + dado);
				System.out.println("El dado de la mesa era " + dadoComun);

				if (dado == dadoComun) {
					System.out.println("Los dados son iguales. Jugador " + identificador + " eliminado");
					vivo = false;
					muertos++;
				} else {
					System.out.println("Los dados son diferentes");
				}

				dadoComun = r.nextInt(6) + 1;
				System.out.println("Ahora el dado de la mesa es " + dadoComun + "\n");

				

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			mutex.release();
		}

	}


	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int id) {
		this.identificador = id;
	}

	public static int getDadoComun() {
		return dadoComun;
	}

	public static void setDadoComun(int dadoComun) {
		Jugador.dadoComun = dadoComun;
	}


	public static int getMuertos() {
		return muertos;
	}

	public static void setMuertos(int muertos) {
		Jugador.muertos = muertos;
	}

	public int getDado() {
		return dado;
	}

	public void setDado(int dado) {
		this.dado = dado;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public Semaphore getMutex() {
		return mutex;
	}

	public void setMutex(Semaphore mutex) {
		this.mutex = mutex;
	}

}
