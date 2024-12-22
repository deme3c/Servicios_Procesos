
public class CajeraCC extends CajeraSC implements Runnable {

	private Cliente cliente;
	private int parametro;

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

	public CajeraCC(Cliente cliente, String nombre) {
		super();
		this.cliente = cliente;
		this.nombre = nombre;
	}

	public void run() {
		pasarCompra(cliente, 2);

	}

}
