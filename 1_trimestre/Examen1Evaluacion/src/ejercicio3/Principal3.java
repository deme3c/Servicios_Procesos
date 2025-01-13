package ejercicio3;

public class Principal3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		BarcaSynchronized barca = new BarcaSynchronized();
		
		Marinero marinero = new Marinero(barca);
		Ola ola = new Ola(barca);
		
		marinero.start();
		ola.start();
	}

}
