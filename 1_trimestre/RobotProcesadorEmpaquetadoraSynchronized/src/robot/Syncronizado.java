package robot;

import java.util.Arrays;

public class Syncronizado {

	public synchronized boolean  procesar(int producto, boolean sitioLibre, int i, int id, int[] productos, int[] robots, boolean[] encontrado) {
		if (productos[i] == 0) {
			robots[i] = id;
			productos[i] = producto;
			sitioLibre = false;
			System.out.println("El robot productor " + id + " ha colocado el producto " + producto
					+ " en la posicion " + i + " de la cadena de montaje");
			System.out.println("Cadena de montaje [Robots] " + Arrays.toString(robots));
			System.out.println("Cadena de montaje [Produc] " + Arrays.toString(productos) + "\n");
			encontrado[0] = true;
		}
		return sitioLibre;
	}
	
	
	public synchronized boolean empaquetar(int producto, boolean encontrado, int i, String[] empaquetados, Procesador robot, boolean[] empaquetado) {
		if (robot.getProductos()[i] == producto) {
			System.out.println("Poducto " + producto + " encontrado ");
			reinitEmpaquetados(empaquetados);
			empaquetados[i] = "*";
			robot.liberarProducto(i);
			encontrado = true;
			System.out.println("Cadena de montaje [ProEmp] " + Arrays.toString(empaquetados));
			System.out.println("Cadena de montaje [Produc] " + Arrays.toString(robot.getProductos()) + "\n");
			empaquetado[0] = true;
		}
		return encontrado;
	}
	
	
	public void reinitEmpaquetados(String[] empaquetados) {
		for (int i = 0; i < empaquetados.length; i++) {
			empaquetados[i] = "";
		}
	}
	
	
	
}
