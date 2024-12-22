package tester;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class MaquinaTester extends Thread {

	private int muestrasTomadas;
	private int contenedorActual[];
	private static boolean testerCC = false;

	private  Semaphore mutex;
	
	public MaquinaTester(int[] contenedorActual, Semaphore mutex) {
		super();
		this.contenedorActual = contenedorActual;
		this.mutex = mutex;

	}

	public int[] getContenedorActual() {
		return contenedorActual;
	}

	public void setContenedorActual(int[] contenedorActual) {
		this.contenedorActual = contenedorActual;
	}

	public int getMuestrasTomadas() {
		return muestrasTomadas;
	}

	public void setMuestrasTomadas(int muestrasTomadas) {
		this.muestrasTomadas = muestrasTomadas;
	}



	@Override
	public void run() {
		if (testerCC) {
			tomarMuestraCC();
		} else {
			tomarMuestraSC();
		}

	}

	public void tomarMuestraCC() {
	    Random r = new Random();
	    
		boolean continuar = true;
		while (continuar) {
	        int extraerTiempo = r.nextInt(11) + 30; 

	        
	        try {
	            Thread.sleep(extraerTiempo);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        try {
	            mutex.acquire();
	            
	            if (contenedorActual[0] == 5000) {
	                continuar = false;
	            } else if (contenedorActual[0] > 0) { 
	                contenedorActual[0]--; 
	                muestrasTomadas++;
	            }
	            
	            if (buscarError()) {
	                try {
	                    Thread.sleep(50);
	                    System.out.println("Error en la muestra, maquinas limpiandose");
	                //    System.out.println(contenedorActual[0]);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }

	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } 
	           
	        System.out.println("Tester: " + contenedorActual[0]);
	        mutex.release();
	        
	        
	    }
	}
	
	public void tomarMuestraSC() {
	    Random r = new Random();
	    
		boolean continuar = true;
		while (continuar) {
	        int extraerTiempo = r.nextInt(11) + 30; 

	        
	        try {
	            Thread.sleep(extraerTiempo);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	            
				if (contenedorActual[0] == 5000) {
					continuar = false;
					testerCC = true;
				}
	       
	            if (contenedorActual[0] > 0 || contenedorActual[0] != 5000) { 
	            	
	                contenedorActual[0]--; 
	                muestrasTomadas++;
	            }
	            
	            if (buscarError()) {
	                try {
	                    Thread.sleep(50);
	                    System.out.println("Error en la muestra, maquinas limpiandose");
	                //    System.out.println(contenedorActual[0]);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	    }
	}

	public boolean buscarError() {
		
    
		Random r = new Random();
		int error = r.nextInt(10) + 1;
		if (error == 5) {
			return true;
		} else {
			return false;
		}

	}

}
