package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class PrincipalCliente {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Socket socket;
		try {
			socket = new Socket("127.0.0.1", 7000);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			String mensajeServidor = in.readUTF();
			System.out.println(mensajeServidor);
			System.out.println();

			String mensaje = "";
			do {
				System.out.println("Escribe el mensaje a enviar:");
				mensaje = sc.nextLine();
				out.writeUTF(mensaje);
				System.out.println("Mensaje enviado");
				String respuesta = in.readUTF();
				System.out.println("Mensaje recibido del servidor: " + respuesta);
				System.out.println();
			} while (!mensaje.equalsIgnoreCase("fin"));

			socket.close();
			System.out.println("Comunicación finalizada");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			sc.close();
		}
	}
}