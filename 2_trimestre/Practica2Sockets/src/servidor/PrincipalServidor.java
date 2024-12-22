package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class PrincipalServidor extends Thread {
    private Socket socketCliente;
    private DataInputStream dis;

    public PrincipalServidor(Socket socketCliente) {
        this.socketCliente = socketCliente;
        try {
            this.dis = new DataInputStream(socketCliente.getInputStream());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String mensajeCliente;
            do {
                mensajeCliente = dis.readUTF();
                System.out.println("(Cliente): " + mensajeCliente);
            } while (!mensajeCliente.equalsIgnoreCase("fin"));
        } catch (Exception e) {
            System.err.println("Error en la recepción: " + e.getMessage());
        } finally {
            try {
                socketCliente.close();
            } catch (Exception e) {
                System.err.println("Error cerrando el socket: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(7000)) {
            System.out.println("Esperando a que alguien conecte");
            Socket socketCliente = servidor.accept();
            System.out.println("Ha conectado " + socketCliente.getInetAddress() + ":" + socketCliente.getPort());

            PrincipalServidor servidorHilo = new PrincipalServidor(socketCliente);
            servidorHilo.start(); 

            DataOutputStream out = new DataOutputStream(socketCliente.getOutputStream());
            Scanner sc = new Scanner(System.in);

            String mensaje;
            do {
                mensaje = sc.nextLine();
                out.writeUTF(mensaje);
            } while (!mensaje.equalsIgnoreCase("fin"));

            servidorHilo.join(); 
            System.out.println("Comunicación finalizada");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}