package ejercicio2;

import java.util.concurrent.Semaphore;

public class Principal2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Semaphore mutex = new Semaphore(1);
		
		Jugador j1 = new Jugador(1, mutex);
		Jugador j2 = new Jugador(2, mutex);
		Jugador j3 = new Jugador(3, mutex);
		
		System.out.println("COMIENZA LA PARTIDA!");
		
		j1.start();
		j2.start();
		j3.start();

	}

}
