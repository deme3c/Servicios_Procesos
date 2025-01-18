package cliente1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class PrincipalCliente1 extends Thread {
    private Socket socket;
    private DataInputStream dis;

    public PrincipalCliente1(Socket socket) {
        this.socket = socket;
        try {
            this.dis = new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.err.println("Error al crear PrincipalCliente1: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String mensajeServidor;
            do {
                // Recibe lo que el servidor retransmite (los mensajes de otros clientes)
                mensajeServidor = dis.readUTF();
                System.out.println("(Servidor) " + mensajeServidor);
            } while (!mensajeServidor.equalsIgnoreCase("fin"));
        } catch (Exception e) {
            System.err.println("Error en la recepción de mensajes (Cliente1): " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                System.err.println("Error cerrando socket (Cliente1): " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        // El host y puerto deben ser los mismos que en el servidor
        final String HOST = "127.0.0.1";
        final int PUERTO = 7000;
        Scanner sc = new Scanner(System.in);

        try {
            // Conexión al servidor
            Socket socket = new Socket(HOST, PUERTO);
            System.out.println("Cliente1 conectado al servidor " + HOST + ":" + PUERTO);

            // Lanzamos hilo para escuchar al servidor (mensajes de chat)
            PrincipalCliente1 cliente1 = new PrincipalCliente1(socket);
            cliente1.start();

            // En el hilo principal, leemos por teclado y enviamos mensajes al servidor
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String mensaje;
            do {
                mensaje = sc.nextLine();
                out.writeUTF("Cliente1: " + mensaje);
            } while (!mensaje.equalsIgnoreCase("fin"));

            // Esperamos a que el hilo de lectura termine
            cliente1.join();
            System.out.println("Cliente1 ha finalizado.");
        } catch (Exception e) {
            System.err.println("Error en Cliente1: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
