package tester;
import java.util.Random;

public class MaquinaSCtester extends Thread {

	private  int contenedorActual[];
	private static int contenedorMaximo = 5000;
	
	private String nombre;
	private int maxLitrosPuedeProducir;
	
	private int litrosProducidos;
	private int litrosVertidos;

	
	public int getMaxLitrosPuedeProducir() {
		return maxLitrosPuedeProducir;
	}

	public void setMaxLitrosPuedeProducir(int maxLitrosPuedeProducir) {
		this.maxLitrosPuedeProducir = maxLitrosPuedeProducir;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setId(String id) {
		this.nombre = id;
	}

	public int[] getContenedorActual() {
		return contenedorActual;
	}

	public void setContenedorActual(int[] contenedorActual) {
		this.contenedorActual = contenedorActual;
	}

	public static int getContenedorMaximo() {
		return contenedorMaximo;
	}

	public static void setContenedorMaximo(int contenedorMaximo) {
		MaquinaSCtester.contenedorMaximo = contenedorMaximo;
	}

	public int getLitrosProducidos() {
		return litrosProducidos;
	}

	public void setLitrosProducidos(int litrosProducidos) {
		this.litrosProducidos = litrosProducidos;
	}

	public int getLitrosVertidos() {
		return litrosVertidos;
	}

	public void setLitrosVertidos(int litrosVertidos) {
		this.litrosVertidos = litrosVertidos;
	}

	public MaquinaSCtester(String id, int maxLitrosProducir, int[] contenedor) {
		super();
		this.nombre = id;
		this.litrosProducidos = 0;
		this.litrosVertidos = 0;
		this.maxLitrosPuedeProducir = maxLitrosProducir;
		this.contenedorActual = contenedor;
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		boolean continuar = true;
		while (continuar) {
			
			Random r = new Random();
			int produccion = r.nextInt(3) + 1;
	

			if (contenedorActual[0] + produccion < contenedorMaximo) {
				contenedorActual[0] += produccion;
				this.litrosProducidos += produccion;
				this.litrosVertidos += produccion;
			} else {
				int espacioDisponible = contenedorMaximo - contenedorActual[0];
				this.litrosProducidos += produccion;
				this.litrosVertidos += espacioDisponible;
				contenedorActual[0] = contenedorMaximo;
				continuar = false;
			}

			try {
				Thread.sleep(produccion);
			} catch (InterruptedException e) {
			}
		}

	}

}
