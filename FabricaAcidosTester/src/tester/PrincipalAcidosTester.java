package tester;

import java.util.concurrent.Semaphore;

public class PrincipalAcidosTester{

	static int contenedor[] = {0};
	private static Semaphore mutex = new Semaphore(1);
	

	public static void main(String[] args) {
		
		MaquinaSCtester sc1 = new MaquinaSCtester("Maquina 1", 3, contenedor);
		MaquinaSCtester sc2 = new MaquinaSCtester("Maquina 2", 4, contenedor);
		MaquinaSCtester sc3 = new MaquinaSCtester("Maquina 3", 5, contenedor);
		
		MaquinaCCtester cc1 = new MaquinaCCtester("Maquina 4", 3, contenedor, mutex);
		MaquinaCCtester cc2 = new MaquinaCCtester("Maquina 5", 4, contenedor, mutex);
		MaquinaCCtester cc3 = new MaquinaCCtester("Maquina 6", 5, contenedor, mutex);
		
		MaquinaTester testerSC = new MaquinaTester(contenedor, mutex);
		MaquinaTester testerCC = new MaquinaTester(contenedor, mutex);
	
		
		maquinasSinConcurrencia(sc1, sc2, sc3, testerSC);
		
		
		System.out.println("****---------------------------------****--------------------------------****");
		contenedor[0] = 0;
		
		maquinasConConcurrencia(cc1, cc2, cc3, testerCC);
		
	
		
	}

	public static void maquinasConConcurrencia(MaquinaCCtester sc1, MaquinaCCtester sc2, MaquinaCCtester sc3, MaquinaTester tester) {
		long tiempoInicio = System.currentTimeMillis();
		sc1.start();
		sc2.start();
		sc3.start();
		tester.start();
		try {
			sc1.join();
			sc2.join();
			sc3.join();
			tester.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long tiempoFin = System.currentTimeMillis();
		
		System.out.println("MÁQUINAS CON SINCRONISMO");
		datosMaquinaCC(sc1);
		datosMaquinaCC(sc2);
		datosMaquinaCC(sc3);
		
		System.out.println("\nTesteadora parada\n");
			
		System.out.println("Total contenedor: " + contenedor[0]);
		System.out.println("Total vertido: " + (sc1.getLitrosVertidos() + sc2.getLitrosVertidos()+sc3.getLitrosVertidos()));
		System.out.println("Muestras tomadas: " + tester.getMuestrasTomadas());
		System.out.println("Tiempo: " + (tiempoFin - tiempoInicio) + " milisegundos");
	}

	public static void datosMaquinaCC(MaquinaCCtester sc1) {
		System.out.println("Contenedor lleno, cantidad contenedor: " + MaquinaSCtester.getContenedorMaximo());
		System.out.println("Litros producidos por la " + sc1.getNombre()+ ": " + sc1.getLitrosProducidos());
		System.out.println("Litros vertidos por la " + sc1.getNombre()+ ": " + sc1.getLitrosVertidos());
		System.out.println("---------------------------------------------------");
	}
	
	
	
	
	
	
	
	public static void maquinasSinConcurrencia(MaquinaSCtester sc1, MaquinaSCtester sc2, MaquinaSCtester sc3, MaquinaTester tester) {
		long tiempoInicio = System.currentTimeMillis();
		sc1.start();
		sc2.start();
		sc3.start();
		tester.start();
		try {
			sc1.join();
			sc2.join();
			sc3.join();
			tester.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long tiempoFin = System.currentTimeMillis();
		
		System.out.println("MÁQUINAS SIN SINCRONISMO");
		datosMaquina(sc1);
		datosMaquina(sc2);
		datosMaquina(sc3);
		
		System.out.println("\nTesteadora parada\n");
			
		System.out.println("Total contenedor: " + MaquinaSCtester.getContenedorMaximo());
		System.out.println("Total vertido: " + (sc1.getLitrosVertidos() + sc2.getLitrosVertidos()+sc3.getLitrosVertidos()));
		System.out.println("Muestras tomadas: " + tester.getMuestrasTomadas());
		System.out.println("Tiempo: " + (tiempoFin - tiempoInicio) + " milisegundos");
	}

	public static void datosMaquina(MaquinaSCtester sc1) {
		System.out.println("Contenedor lleno, cantidad contenedor: " + MaquinaSCtester.getContenedorMaximo());
		System.out.println("Litros producidos por la " + sc1.getNombre()+ ": " + sc1.getLitrosProducidos());
		System.out.println("Litros vertidos por la " + sc1.getNombre()+ ": " + sc1.getLitrosVertidos());
		System.out.println("---------------------------------------------------");
	}
	
	
	
	
	
}
