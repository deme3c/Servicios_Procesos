package paq1;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc= new Scanner(System.in);
		
		int contenedor[] = {0};

		MaquinaAcido maq1 = new MaquinaAcido(1, 3, contenedor);
		MaquinaAcido maq2 = new MaquinaAcido(2, 4, contenedor);
		MaquinaAcido maq3 = new MaquinaAcido(3, 5, contenedor);
		Testeadora test1 = new Testeadora(contenedor);
		
		System.out.println("MÁQUINAS SIN SINCRONISMO");
		
		long t1= System.currentTimeMillis();
		maq1.start();
		maq2.start();
		maq3.start();
		test1.start();
		
		
		
		try {
			maq1.join();
			maq2.join();
			maq3.join();
			test1.join();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long t2= System.currentTimeMillis();
		
		System.out.println("\nTotal contenedor: " + contenedor[0]);
		System.out.println("Total vertido: " + (maq1.getTotalLitrosVertidos()+ 
				maq2.getTotalLitrosVertidos() + maq3.getTotalLitrosVertidos()));
		System.out.println("Muestras tomadas: " + test1.getMuestras());
		System.out.println("Tiempo: " + (t2-t1) + " ms");
		
		System.out.println("\nPulsa enter");
		
		sc.nextLine();
		
		System.out.println("MÁQUINAS CON SINCRONISMO");
		
		Contenedor c = new Contenedor();

		MaquinaAcidoSinc maq4 = new MaquinaAcidoSinc(4, 3, c);
		MaquinaAcidoSinc maq5 = new MaquinaAcidoSinc(5, 4, c);
		MaquinaAcidoSinc maq6 = new MaquinaAcidoSinc(6, 5, c);
		TesteadoraSinc test2 = new TesteadoraSinc(c);
		
		long t3= System.currentTimeMillis();
		maq4.start();
		maq5.start();
		maq6.start();
		test2.start();
		
		
		try {
			maq4.join();
			maq5.join();
			maq6.join();
			test2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long t4= System.currentTimeMillis();
		
		System.out.println("\nTotal contenedor: " + contenedor[0]);
		System.out.println("Total vertido: " + (maq4.getTotalLitrosVertidos()+ 
				maq5.getTotalLitrosVertidos() + maq6.getTotalLitrosVertidos()));
		System.out.println("Muestras tomadas: " + test2.getMuestras());
		System.out.println("Tiempo: " + (t4-t3)+ " ms");
		
	}

}
