package ejercicio3;

import java.util.Random;

public class Ola extends Thread{

	private BarcaSynchronized barca;
	private Random r = new Random();
	
	
	public Ola(BarcaSynchronized barca) {
		super();
		this.barca = barca;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		while(!barca.fin) {
			try {
				int espera = r.nextInt(800)+400;
				Thread.sleep(espera);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(barca.fin) {
				break;
			}
			barca.olaGolpeaBarco();

		}
		
	}
	
	
}
