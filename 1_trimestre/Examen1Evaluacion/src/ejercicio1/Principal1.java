package ejercicio1;

import java.util.Scanner;

public class Principal1 {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Impresora i1 = new Impresora(1);
		Impresora i2 = new Impresora(2);

		boolean salir = false;
		while(!salir) {
			
			System.out.println("Tamaño del documento que se envia a la impresora 1?");
			int tam1 = sc.nextInt();
			System.out.println("Tamaño del documento que se envia a la impresora 2?");
			int tam2 = sc.nextInt();	
			
			i1.setPags(tam1);
			i2.setPags(tam2);
			

			i1.start();
			i2.start();
			
			try {
				i1.join();
				i2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println();
			System.out.println("Tiempo de la impresora 1: " + i1.getTiempo() + " milisegundos");
			System.out.println("Tiempo de la impresora 2: " + i2.getTiempo() + " milisegundos");
			System.out.println();
			
		}
		

	}

}
