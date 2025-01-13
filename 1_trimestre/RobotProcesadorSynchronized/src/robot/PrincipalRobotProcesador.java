package robot;

public class PrincipalRobotProcesador {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Procesador robot1 = new Procesador(1);
		Procesador robot2 = new Procesador(2);
		Procesador robot3 = new Procesador(3);
		
		robot1.start();
		robot2.start();
		robot3.start();
		
	}

}
