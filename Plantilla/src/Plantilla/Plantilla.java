package Plantilla;
import java.util.*;
import java.time.*;
public class Plantilla {
	
	static Scanner sc = new Scanner(System.in);
	static Scanner scLine = new Scanner(System.in);
	static Random r = new Random();
	
	public static void main(String[] args) {
		
		System.out.println("\nPulse enter para continuar");
		scLine.nextLine();
	
		LocalDateTime ldt1 = LocalDateTime.now();
		LocalDateTime ldt2 = LocalDateTime.of(1999, 7,15,18,30,0);
		
		
		
		int a = r.nextInt(3); //saldrá 0,1,2
		
        double num = r.nextDouble() * 10;  // Genera un número entre 0 y 10
        num += 10;  // Añade 10 para obtener un número entre 10 y 20
        // Redondear a 2 decimales
        num = Math.round(num * 100.0) / 100.0;
        System.out.println("Número aleatorio entre 10 y 20: " + num);
		
		// s.setGanancias(Math.round(ganancias * 100.0) / 100.0);    REDONDEO A 2 DECIMALES, 1000.0 1000.0 3 DECIMALES
		
		boolean salir = false;
		while (!salir) {
			int opcion = 0;
			do {
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				try {
					System.out.println("\nSelecciona entre ");
					opcion = sc.nextInt();
				} catch (Exception e) {
					System.out.println("Ingresa un número válido");
					sc.next();
				}
			} while (opcion < 1 || opcion > 5);
			switch (opcion) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
				
				
				break;
			} // fin switch
		} // fin while
		
	} // fin main
	
	public static void ordenar() {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - i - 1; j++) {
				if (array[j].getRecorrido() < array[j + 1].getRecorrido()) {
					Caballo temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}	
	}
	
	public static void ordenar2() {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 1; j < array.length; j++) {
				if (array[i].getRecorrido() < array[j].getRecorrido()) {
					Caballo temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}	
	}
	
	
	public static int seleccionarNumero (int a, int b) {
		Scanner sc = new Scanner(System.in);
		
		boolean salir = false;
		int numeroSeleccionado = -100;
		while(!salir) {
			
			int opcion = 0;
			
			do {
				try {
					System.out.println("¿Qué opción deseas escoger?");
					opcion = sc.nextInt();
				}
				catch (Exception e){
					System.out.println("Seleccione un número valido");
					sc.next();
				}
			} while(opcion < a || opcion > b);
			
			numeroSeleccionado = opcion;
		}
		return numeroSeleccionado;
	}
	
	
	
}

