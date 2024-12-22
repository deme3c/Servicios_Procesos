public class PrincipalAcidos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MaquinaSC sc1 = new MaquinaSC("Maquina 1");
		MaquinaSC sc2 = new MaquinaSC("Maquina 2");
		MaquinaCC cc1 = new MaquinaCC("Maquina 3");
		MaquinaCC cc2 = new MaquinaCC("Maquina 4");
		
		maquinasSinConcurrencia(sc1, sc2);
		System.out.println();
		System.out.println();
		maquinasConConcurrencia(cc1, cc2);
		
	}

	public static void maquinasConConcurrencia(MaquinaCC cc1, MaquinaCC cc2) {
		long tiempoInicio = System.currentTimeMillis();
		cc1.start();
		cc2.start();
		try {
			cc1.join();
			cc2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long tiempoFin = System.currentTimeMillis();
		
		System.out.println("MÁQUINAS CON SINCRONISMO");
		datosMaquinaCC(cc1);
		datosMaquinaCC(cc2);
		System.out.println("Total contenedor: " + MaquinaCC.getContenedorMaximo());
		System.out.println("Total vertido: " + (cc1.getLitrosVertidos() + cc2.getLitrosVertidos()));
		System.out.println("Tiempo: " + (tiempoFin - tiempoInicio) + " milisegundos");
	}

	public static void datosMaquinaCC(MaquinaCC sc1) {
		System.out.println("Contenedor lleno, cantidad contenedor: " + MaquinaSC.getContenedorMaximo());
		System.out.println("Litros producidos por la " + sc1.getNombre()+ ": " + sc1.getLitrosProducidos());
		System.out.println("Litros vertidos por la " + sc1.getNombre()+ ": " + sc1.getLitrosVertidos());
		System.out.println("---------------------------------------------------");
	}
	
	
	
	/*
	 * ************************************************************************************************************************
	 */
	public static void maquinasSinConcurrencia(MaquinaSC sc1, MaquinaSC sc2) {
		long tiempoInicio = System.currentTimeMillis();
		sc1.start();
		sc2.start();
		try {
			sc1.join();
			sc2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long tiempoFin = System.currentTimeMillis();
		
		System.out.println("MÁQUINAS SIN SINCRONISMO");
		datosMaquina(sc1);
		datosMaquina(sc2);
		System.out.println("Total contenedor: " + MaquinaSC.getContenedorMaximo());
		System.out.println("Total vertido: " + (sc1.getLitrosVertidos() + sc2.getLitrosVertidos()));
		System.out.println("Tiempo: " + (tiempoFin - tiempoInicio) + " milisegundos");
	}

	public static void datosMaquina(MaquinaSC sc1) {
		System.out.println("Contenedor lleno, cantidad contenedor: " + MaquinaSC.getContenedorMaximo());
		System.out.println("Litros producidos por la " + sc1.getNombre()+ ": " + sc1.getLitrosProducidos());
		System.out.println("Litros vertidos por la " + sc1.getNombre()+ ": " + sc1.getLitrosVertidos());
		System.out.println("---------------------------------------------------");
	}
	

}
