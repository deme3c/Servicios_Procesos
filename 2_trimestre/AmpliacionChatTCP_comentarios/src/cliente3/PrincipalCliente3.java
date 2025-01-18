package cliente3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class PrincipalCliente3 extends Thread {
    private Socket socket;
    private DataInputStream dis;

    public PrincipalCliente3(Socket socket) {
        this.socket = socket;
        try {
            this.dis = new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.err.println("Error al crear PrincipalCliente3: " + e.getMessage());
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
            System.err.println("Error en la recepción de mensajes (Cliente3): " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                System.err.println("Error cerrando socket (Cliente3): " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        final String HOST = "127.0.0.1";
        final int PUERTO = 7000;
        Scanner sc = new Scanner(System.in);

        try {
            Socket socket = new Socket(HOST, PUERTO);
            System.out.println("Cliente3 conectado al servidor " + HOST + ":" + PUERTO);

            PrincipalCliente3 cliente3 = new PrincipalCliente3(socket);
            cliente3.start();

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String mensaje;
            do {
                mensaje = sc.nextLine();
                out.writeUTF("Cliente3: " + mensaje);
            } while (!mensaje.equalsIgnoreCase("fin"));

            cliente3.join();
            System.out.println("Cliente3 ha finalizado.");
        } catch (Exception e) {
            System.err.println("Error en Cliente3: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
