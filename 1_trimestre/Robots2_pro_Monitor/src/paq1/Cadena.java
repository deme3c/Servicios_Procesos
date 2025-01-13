package paq1;

import java.util.Arrays;

public class Cadena {

	public int[] cadenaRob=new int[5];
	public int[] cadenaPro=new int[5];

	public synchronized void producir(int id, int producto) {

		System.out.println("El productor " + id + " produce el producto " + producto);

		int i = 0;
		while (cadenaPro[i] != 0) {
			i++;
			if (i == 5)
				break;
		}

		if (i < 5) {

			cadenaRob[i] = id;
			cadenaPro[i] = producto;
			System.out.println("El robot productor " + id + " ha colocado " + "el producto " + producto
					+ " en la posición " + i + " de la cadena de montaje");
			System.out.println("Cadena de montaje [Robots] " + Arrays.toString(cadenaRob));
			System.out.println("Cadena de montaje [Produc] " + Arrays.toString(cadenaPro) + "\n");

		} else {
			System.out.println("El procesador " + id + " no encuentra sitio para el producto\n");
		}

	}

	public synchronized void empaquetar(int id, int producto) {

		System.out.println("El empaquetador " + id + " se dispone a empaquetar " + "un producto " + producto);

		int i = 0;
		while (cadenaPro[i] != producto) {
			i++;
			if (i == 5)
				break;
		}

		if (i < 5) {

			System.out.println("Producto " + producto + " encontrado");
			char cadenaAst[] = { ' ', ' ', ' ', ' ', ' ' };
			cadenaRob[i] = 0;
			cadenaPro[i] = 0;
			cadenaAst[i] = '*';
			System.out.println("Cadena de montaje [ProEmp] " + Arrays.toString(cadenaAst));
			System.out.println("Cadena de montaje [Produc] " + Arrays.toString(cadenaPro) + "\n");

		} else {

			System.out.println("No se ha encontrado ningun producto " + producto + "\n");
		}

	}
	
}
