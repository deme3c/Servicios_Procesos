
public class CajeraSC {

	double tiempoTotal = 0;
	String nombre;
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void pasarCompra(Cliente c, int parametro) {

		long tiempoCompraInicial = System.currentTimeMillis();
		System.out.println("La "+ this.nombre  + " comienza a procesar la compra del " + c.getNombre() + " en el segundo "
				+ Math.round(tiempoTotal * 10.0) / 10.0);
		double tiempoProducto = 0;
		for (int i = 0; i < c.getProducto().length; i++) {
			if (c.getProducto()[i] == 0) {
				break;
			}
			long tiempoProductoInicial = System.currentTimeMillis();
			pararTiempo(550 * c.getProducto()[i]);

			long tiempoProductoFinal = System.currentTimeMillis();
			tiempoProducto += (tiempoProductoFinal - tiempoProductoInicial) / 1000.0;

			if (parametro == 1) {
				System.out.println("La " + this.nombre + " acaba de pasar el producto " + (i + 1) + " del cliente " + c.getNombre()
						+ " en el segundo " + Math.round(tiempoProducto * 10.0) / 10.0);
			} else {
				System.out.println("La " + this.nombre + " acaba de pasar el producto " + (i + 1) + " del cliente " + c.getNombre()
						+ " en el segundo " + Math.round(tiempoProducto * 100.0) / 100.0);
			}

		}
		tiempoTotal += tiempoProducto;
		long tiempoCompraFinal = System.currentTimeMillis();
		String name = this.nombre;
		System.out.println("La "+ name +  " ha tardado " + (tiempoCompraFinal - tiempoCompraInicial) / 1000.0
				+ " segundos en procesar la compra del cliente " + c.getNombre() + "\n");

		System.out.println();
	}

	public static void pararTiempo(long seg) {
		try {
			Thread.sleep(seg);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}

	public double getTiempoTotal() {
		return tiempoTotal;
	}
}