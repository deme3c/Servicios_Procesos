import java.util.Random;

public class MaquinaSC extends Thread{

	private static int contenedorActual = 0;
	private static int contenedorMaximo = 5000;
	private String nombre;
	private int litrosProducidos;
	private int litrosVertidos;

	public String getNombre() {
		return nombre;
	}

	public void setId(String id) {
		this.nombre = id;
	}

	public static int getContenedorActual() {
		return contenedorActual;
	}

	public static void setContenedorActual(int contenedorActual) {
		MaquinaSC.contenedorActual = contenedorActual;
	}

	public static int getContenedorMaximo() {
		return contenedorMaximo;
	}

	public static void setContenedorMaximo(int contenedorMaximo) {
		MaquinaSC.contenedorMaximo = contenedorMaximo;
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

	public MaquinaSC(String id) {
		super();
		this.nombre = id;
		this.litrosProducidos = 0;
		this.litrosVertidos = 0;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		boolean continuar = true;
		while (continuar) {
			
			Random r = new Random();
			int produccion = r.nextInt(3) + 1;
	

			if (contenedorActual + produccion < contenedorMaximo) {
				contenedorActual += produccion;
				this.litrosProducidos += produccion;
				this.litrosVertidos += produccion;
			} else {
				int espacioDisponible = contenedorMaximo - contenedorActual;
				this.litrosProducidos += produccion;
				this.litrosVertidos += espacioDisponible;
				contenedorActual = contenedorMaximo;
				continuar = false;
			}

			try {
				Thread.sleep(produccion);
			} catch (InterruptedException e) {
			}

		}

	}
}
