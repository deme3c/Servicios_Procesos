import java.util.*;

public class main {

	static Scanner sc = new Scanner(System.in);
	static Scanner scLine = new Scanner(System.in);


	public static void main(String[] args) {

		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		Cajera cajera1 = new Cajera();
		String respuesta;
		int respuestaPrecision = 0;

		do {
			
			do {
				System.out.println("1) Precisión de décimas de segundo");
				System.out.println("2) Precisión de centésimas de segundo");
				System.out.println("Elige precisión(1-2)");
				respuestaPrecision = sc.nextInt();
			}
			while (respuestaPrecision != 1 && respuestaPrecision != 2);
				
			System.out.println("COMPRA CLIENTE 1");
			cliente1.reinicioArray();
			cliente1.realizarCompra();

			System.out.println("COMPRA CLIENTE 2");
			cliente2.reinicioArray();
			cliente2.realizarCompra();
			
			cajera1.pasarCompra(cliente1, respuestaPrecision);
			cajera1.pasarCompra(cliente2, respuestaPrecision);
			
			System.out.println("Pasar otra compra (s/n)?");
			respuesta = scLine.nextLine();
		}

		while (respuesta.equalsIgnoreCase("s"));

	} // main

	
	public static void precisionSeg(long seg) {
		try {
			Thread.sleep(seg);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}
}
