package ejercicio3;

public class Marinero extends Thread{

	private BarcaSynchronized barca;


	public Marinero(BarcaSynchronized barca) {
		super();
		this.barca = barca;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		while(!barca.fin) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(barca.fin) {
				break;
			}
			barca.achicarAguaMarinero();
		}	
	}
	
	
}
