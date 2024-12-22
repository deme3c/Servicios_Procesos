package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class PrincipalCliente extends Thread {
    private Socket socket;
    private DataInputStream dis;

    public PrincipalCliente(Socket socket) {
        this.socket = socket;
        try {
            this.dis = new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String mensajeServidor;
            do {
                mensajeServidor = dis.readUTF();
                System.out.println("(Servidor): " + mensajeServidor);
            } while (!mensajeServidor.equalsIgnoreCase("fin"));
        } catch (Exception e) {
            System.err.println("Error en la recepción: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                System.err.println("Error cerrando el socket: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Socket socket = new Socket("127.0.0.1", 7000);
            System.out.println("Conectado al servidor");

            PrincipalCliente cliente = new PrincipalCliente(socket);
            cliente.start(); 

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String mensaje;
            do {
                mensaje = sc.nextLine();
                out.writeUTF(mensaje);
            } while (!mensaje.equalsIgnoreCase("fin"));

            cliente.join(); 
            System.out.println("Comunicación finalizada");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            sc.close();
        }
    }
}