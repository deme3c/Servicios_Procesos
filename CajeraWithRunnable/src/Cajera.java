
public class Cajera extends Empleado implements Runnable{

	private double tiempoTotal = 0;
	private Cliente cliente;
	private int parametro;
	
	public double getTiempoTotal() {
		return tiempoTotal;
	}

	public void setTiempoTotal(double tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getParametro() {
		return parametro;
	}

	public void setParametro(int parametro) {
		this.parametro = parametro;
	}
	
	public Cajera(int id, String nombre, String apellidos, String dni, String puesto, Cliente cliente) {
		super(id, nombre, apellidos, dni, puesto);
		this.cliente = cliente;
	}

	public Cajera(int id, String nombre, String apellidos, String dni, String puesto) {
		super(id, nombre, apellidos, dni, puesto);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		pasarCompra(cliente, parametro);
		
	}
	
	
	public void pasarCompra(Cliente c, int parametro) {

		long tiempoCompraInicial = System.currentTimeMillis();
		System.out.println(this.nombre  + " comienza a procesar la compra del " + c.getNombre() + " en el segundo "
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
				System.out.println(this.nombre + " acaba de pasar el producto " + (i + 1) + " del cliente " + c.getNombre()
						+ " en el segundo " + Math.round(tiempoProducto * 10.0) / 10.0);
			} else {
				System.out.println(this.nombre + " acaba de pasar el producto " + (i + 1) + " del cliente " + c.getNombre()
						+ " en el segundo " + Math.round(tiempoProducto * 100.0) / 100.0);
			}

		}
		tiempoTotal += tiempoProducto;
		long tiempoCompraFinal = System.currentTimeMillis();
		String name = this.nombre;
		System.out.println(name +  " ha tardado " + (tiempoCompraFinal - tiempoCompraInicial) / 1000.0
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

	
}
