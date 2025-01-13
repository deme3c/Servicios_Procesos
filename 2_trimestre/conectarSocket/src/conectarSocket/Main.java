package conectarSocket;

import java.net.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Instancia de Scanner para leer datos del usuario
        Scanner sc = new Scanner(System.in);

        Socket socket;
        String mensaje = "";

        try {
            // Instanciamos un socket con la dirección del destino y el puerto
            socket = new Socket("192.168.153.151", 7001);

            // Declaramos e instanciamos el objeto DataOutputStream
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // Creamos un bucle do-while en el que enviamos al servidor el mensaje
            do {
                System.out.println("Escribe un mensaje:");
                mensaje = sc.nextLine();

                // Enviamos el mensaje codificado en UTF
                out.writeUTF(mensaje);
            } while (!mensaje.equals("fin"));

            // Cerramos el socket
            socket.close();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            // Cerramos el scanner
            sc.close();
        }
    }
}
