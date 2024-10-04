import java.util.Scanner;

public class principal {

	static Scanner sc = new Scanner(System.in);
	static Scanner scLine = new Scanner(System.in);


	public static void main(String[] args) {

		Cliente cliente1 = new Cliente("Cliente 1");
		Cliente cliente2 = new Cliente("Cliente 2");
		Cliente cliente3 = new Cliente("Cliente 3");
		
		CajeraSC cajera1 = new CajeraSC();
		cajera1.setNombre("Cajera");
		
		CajeraCC cajeraCC1 = new CajeraCC(cliente1, "Cajera 1");
		CajeraCC cajeraCC2 = new CajeraCC(cliente2, "Cajera 2");
		CajeraCC cajeraCC3 = new CajeraCC(cliente3, "Cajera 3");
		
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
			
			cajeraCC1.setParametro(respuestaPrecision);
			cajeraCC2.setParametro(respuestaPrecision);
			cajeraCC3.setParametro(respuestaPrecision);
			
			//1º PARTE
			compraClientes(cliente1, cliente2, cliente3);
			pasarCompraCajeraSC(cliente1, cliente2, cliente3, cajera1, respuestaPrecision);
		
			pulsarEnter();
			//2º PARTE	
			Thread hilo1 = new Thread(cajeraCC1);
			Thread hilo2 = new Thread(cajeraCC2);
			Thread hilo3 = new Thread(cajeraCC3);
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
	        
	        tiemposCajeras(cajera1, cajeraCC1, cajeraCC2, cajeraCC3);
	        

	        
	        //REPETIR
			System.out.println("Pasar otra compra (s/n)?");
			respuesta = scLine.nextLine();
		}

		while (respuesta.equalsIgnoreCase("s"));

	} // main


	private static void tiemposCajeras(CajeraSC cajera1, CajeraCC cajeraCC1, CajeraCC cajeraCC2, CajeraCC cajeraCC3) {
		System.out.println("Tiempo invertido en pasar las 3 compras por una cajera "
				 + Math.round(cajera1.getTiempoTotal()) + " segundos" + "\n");
		
		
		double tiempoCajera1 = cajeraCC1.getTiempoTotal();
		double tiempoCajera2 = cajeraCC2.getTiempoTotal();
		double tiempoCajera3 = cajeraCC3.getTiempoTotal();

		if (tiempoCajera1 >= tiempoCajera2 && tiempoCajera1 >= tiempoCajera3) {
		    System.out.println("Tiempo invertido en pasar las 3 compras por 3 cajeras en paralelo: "
		    		+ Math.round(tiempoCajera1) + " segundos");
		    System.out.println("\nTIMEPO GANADO EN EL SEGUNDO CASO: " + Math.round(cajera1.getTiempoTotal()-tiempoCajera1) + " segundos");
		} else if (tiempoCajera2 >= tiempoCajera1 && tiempoCajera2 >= tiempoCajera3) {
		    System.out.println("Tiempo invertido en pasar las 3 compras por 3 cajeras en paralelo: "
		    		+ Math.round(tiempoCajera2) + " segundos");
		    System.out.println("\nTIMEPO GANADO EN EL SEGUNDO CASO: " + Math.round(cajera1.getTiempoTotal()-tiempoCajera2) + " segundos");
		} else {
		    System.out.println("Tiempo invertido en pasar las 3 compras por 3 cajeras en paralelo: "
		    		+ Math.round(tiempoCajera3) + " segundos");
		    System.out.println("\nTIMEPO GANADO EN EL SEGUNDO CASO: " + Math.round(cajera1.getTiempoTotal()-tiempoCajera3) + " segundos");
		}
	}


	private static void pulsarEnter() {
		System.out.println("Pulsa enter para continuar");
		scLine.nextLine();
	}


	private static void pasarCompraCajeraSC(Cliente cliente1, Cliente cliente2, Cliente cliente3, CajeraSC cajera1, int respuestaPrecision) {
		cajera1.pasarCompra(cliente1, respuestaPrecision);
		cajera1.pasarCompra(cliente2, respuestaPrecision);
		cajera1.pasarCompra(cliente3, respuestaPrecision);
		System.out.println("La cajera ha tardado " + Math.round(cajera1.getTiempoTotal()) + " segundos en procesar la compra de los clientes");
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
