
public class Cajera {

	double tiempo = 0;

	public void pasarCompra(Cliente c, int parametro) {
	
		for (int i = 0; i < c.getProducto().length; i++) {
			if (c.getProducto()[i] == 0) {
				break;
			}
			tiempo += c.getProducto()[i] * 0.55;
			pararTiempo(550 * c.getProducto()[i]);
			if(parametro==1) {
				System.out.println("Acaba de pasar el producto " + (i + 1) + " en el segundo " + Math.round(tiempo*10.0)/10.0);
			}
			else {
				System.out.println("Acaba de pasar el producto " + (i + 1) + " en el segundo " + Math.round(tiempo*100.0)/100.0);
			}
		}

		System.out.println("La cajera ha tardado " + tiempo + " segundos en procesar la compra");

	}

	public static void pararTiempo(long seg) {
		try {
			Thread.sleep(seg);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}
}