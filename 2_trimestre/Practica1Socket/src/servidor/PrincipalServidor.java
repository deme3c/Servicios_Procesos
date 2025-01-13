package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PrincipalServidor {
	public static void main(String[] args) {
		ServerSocket servidor;
		try {

			servidor = new ServerSocket(7000);
			System.out.println("Esperando a que alguien conecte");

			Socket socketCliente = servidor.accept();
			System.out.println("Ha conectado " + socketCliente.getInetAddress() + ":" + socketCliente.getPort());
			System.out.println();

			DataInputStream in = new DataInputStream(socketCliente.getInputStream());
			DataOutputStream out = new DataOutputStream(socketCliente.getOutputStream());
			out.writeUTF("Hola desde el servidor");

			boolean fin = false;
			do {
				System.out.println("Esperando a recibir un mensaje");
				String mensaje = in.readUTF();
				System.out.println("Mensaje recibido: " + mensaje);

				String respuesta = mensaje.toUpperCase();
				out.writeUTF(respuesta);
				System.out.println("Se ha contestado el mismo mensaje en mayúsculas");
				System.out.println();

				if (mensaje.equalsIgnoreCase("fin")) {
					fin = true;
				}

			} while (!fin);
			socketCliente.close();
			servidor.close();
			System.out.println();
			System.out.println("Comunicación finalizada");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}