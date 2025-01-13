package robot;

import java.util.Arrays;

public class Procesador extends Thread {

    private int id;
    private static int[] robots = new int[10];
    private static int[] productos = new int[10];
    private static int[] contador = {0};
    private static boolean finColocar = false;

    public Procesador(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        super.run();

        while (!finColocar) {

            int producto = (int) (Math.random() * 4) + 1;

            try {
                Thread.sleep(producto * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (contador) {
                if (contador[0] == 10) {
                    finColocar = true;
                    System.out.println("El robot " + id + " se ha apagado");
                    return; // Finaliza el hilo actual si ya se alcanzó el límite
                }

                robots[contador[0]] = id;
                productos[contador[0]] = producto;
                System.out.println("El robot productor " + id + " ha colocado el producto " + producto
                        + " en la posicion " + contador[0] + " de la cadena de montaje");
                System.out.println("Cadena de montaje [Robots] " + Arrays.toString(robots));
                System.out.println("Cadena de montaje [Produc] " + Arrays.toString(productos) + "\n");

                contador[0]++;
            }
        }
    }
}