package cliente1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente1 extends Thread {
    private Socket socket;
    private DataInputStream dis;

    public Cliente1(Socket socket) {
        this.socket = socket;
        try {
            this.dis = new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.err.println("Error al crear Cliente1: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String mensajeServidor;
            do {
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
        final String HOST = "127.0.0.1";
        final int PUERTO = 7000;
        Scanner sc = new Scanner(System.in);

        try {
            Socket socket = new Socket(HOST, PUERTO);
            System.out.println("Cliente1 conectado al servidor " + HOST + ":" + PUERTO);

            Cliente1 cliente1 = new Cliente1(socket);
            cliente1.start();

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String mensaje;
            do {
                mensaje = sc.nextLine();
                out.writeUTF("Cliente1: " + mensaje);
            } while (!mensaje.equalsIgnoreCase("fin"));

            cliente1.join();
            System.out.println("Cliente1 ha finalizado.");
        } catch (Exception e) {
            System.err.println("Error en Cliente1: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
