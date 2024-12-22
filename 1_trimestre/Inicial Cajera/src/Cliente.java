import java.util.Scanner;

public class Cliente {

	int producto[] = new int[6];
	int cantidad;

	public int[] getProducto() {
		return producto;
	}

	public void setProducto(int[] producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Cliente() {
		super();
	}

	public void realizarCompra() {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < producto.length; i++) {
			System.out.println("Unidades compradas del producto " + (i + 1) + "?");
			while (true) {
				cantidad = sc.nextInt();
				if (cantidad == -1 || cantidad < 5 && cantidad > 0) {
					break;
				}
				System.out.println("La cantidad debe ser entre 1 y 4");
			}
			if(cantidad == -1) {
				break;
			}
			producto[i]=cantidad;
		}
	}
	public void reinicioArray() {
		for(int i=0; i<producto.length;i++) {
			producto[i]=0;
		}
	}
	
}
