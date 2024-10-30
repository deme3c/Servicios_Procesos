import java.util.Scanner;

public class Principal {

	static Scanner sc = new Scanner(System.in);
	static Scanner scLine = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cliente cliente1 = new Cliente("Cliente 1");
		Cliente cliente2 = new Cliente("Cliente 2");
		Cliente cliente3 = new Cliente("Cliente 3");
		Cajera cajera1 = new Cajera(1, "Adela", "Ronaldo", "12345678A", "Cajera", cliente1);
		Cajera cajera2 = new Cajera(2, "Mario", "Augusto", "12345678A", "Cajera", cliente2);
		Cajera cajera3 = new Cajera(3, "Fermin", "Flishgdegf", "12345678A", "Cajera", cliente3);

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
			
			cajera1.setParametro(respuestaPrecision);
			cajera2.setParametro(respuestaPrecision);
			cajera3.setParametro(respuestaPrecision);
			
			compraClientes(cliente1, cliente2, cliente3);	
		
			pulsarEnter();

			Thread hilo1 = new Thread(cajera1);
			Thread hilo2 = new Thread(cajera2);
			Thread hilo3 = new Thread(cajera3);
	        hilo1.start();
	        hilo2.start();
	        hilo3.start();
	        try {
	            hilo1.join();
	            hilo2.join();
	            hilo3.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }	

			System.out.println("Pasar otra compra (s/n)?");
			respuesta = scLine.nextLine();
		}

		while (respuesta.equalsIgnoreCase("s"));
		

		
	}

	private static void pulsarEnter() {
		System.out.println("Pulsa enter para continuar");
		scLine.nextLine();
	}

	private static void compraClientes(Cliente cliente1, Cliente cliente2, Cliente cliente3) {
		System.out.println("COMPRA CLIENTE 1");
		cliente1.reinicioArray();
		cliente1.realizarCompra();
		cliente1.mostarCompra();
		System.out.println("COMPRA CLIENTE 2");
		cliente2.reinicioArray();
		cliente2.realizarCompra();
		cliente2.mostarCompra();
		System.out.println("COMPRA CLIENTE 3");
		cliente3.reinicioArray();
		cliente3.realizarCompra();
		cliente3.mostarCompra();
		
		pulsarEnter();
	}

	
	public static void precisionSeg(long seg) {
		try {
			Thread.sleep(seg);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}
	
	
	
}
