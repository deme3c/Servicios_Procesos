package cliente2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class PrincipalCliente2 extends Thread {
    private Socket socket;
    private DataInputStream dis;

    public PrincipalCliente2(Socket socket) {
        this.socket = socket;
        try {
            this.dis = new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.err.println("Error al crear PrincipalCliente2: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String mensajeServidor;
            do {
                // Recibe los mensajes que el servidor retransmite
                mensajeServidor = dis.readUTF();
                System.out.println("(Servidor) " + mensajeServidor);
            } while (!mensajeServidor.equalsIgnoreCase("fin"));
        } catch (Exception e) {
            System.err.println("Error en la recepción de mensajes (Cliente2): " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                System.err.println("Error cerrando socket (Cliente2): " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        final String HOST = "127.0.0.1";
        final int PUERTO = 7000;
        Scanner sc = new Scanner(System.in);

        try {
            Socket socket = new Socket(HOST, PUERTO);
            System.out.println("Cliente2 conectado al servidor " + HOST + ":" + PUERTO);

            PrincipalCliente2 cliente2 = new PrincipalCliente2(socket);
            cliente2.start();

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String mensaje;
            do {
                mensaje = sc.nextLine();
                out.writeUTF("Cliente2: " + mensaje);
            } while (!mensaje.equalsIgnoreCase("fin"));

            cliente2.join();
            System.out.println("Cliente2 ha finalizado.");
        } catch (Exception e) {
            System.err.println("Error en Cliente2: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
