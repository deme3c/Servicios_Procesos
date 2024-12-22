
public class Cliente {

	int producto[] = new int[6];
	String nombre;

	public int[] getProducto() {
		return producto;
	}

	public void setProducto(int[] producto) {
		this.producto = producto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Cliente(String nombre) {
		this.nombre = nombre;
	}

	public void realizarCompra() {
		int cantidad = 0;
		for (int i = 0; i < producto.length; i++) {
			if(i==0) {
				cantidad = (int) (Math.random() * 4) + 1 ;
			}
			else {
				cantidad = (int) (Math.random() * 5);
			}
			producto[i] = cantidad;
			if (cantidad == 0) {
				break;
			}
		}
	}
	
	public void mostarCompra() {
		for (int i = 0; i < producto.length; i++) {
			if (producto[i] == 0) {
				break;
			}
			System.out.println("Producto " + (i + 1) + ": " + producto[i]);
		}
	}
	
	public void reinicioArray() {
		for(int i=0; i<producto.length;i++) {
			producto[i]=0;
		}
	}
	
}
