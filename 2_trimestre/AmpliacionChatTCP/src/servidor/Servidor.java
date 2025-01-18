package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Servidor {

	private static List<DataOutputStream> listaClientes = new ArrayList<>();
	
	public static void main(String[] args) {
		final int PUERTO = 7000;
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);
            System.out.println("Esperando conexiones de clientes...");
            
            while(true) {
            	Socket socketCliente = serverSocket.accept();
                System.out.println("Cliente conectado desde: " + socketCliente.getInetAddress() + ":" + socketCliente.getPort());
                
                DataOutputStream salidaCliente = new DataOutputStream(socketCliente.getOutputStream());
                synchronized (listaClientes) {
                	listaClientes.add(salidaCliente);
				}
                
                ManejadorCliente manejador = new ManejadorCliente(socketCliente, listaClientes, salidaCliente);
                manejador.start();
            }
//               
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }   
	}
	
	private static class ManejadorCliente extends Thread {
        private Socket socket;
        private List<DataOutputStream> listaClientes;
        private DataInputStream dis;
        private DataOutputStream salidaActual;
		
		public ManejadorCliente(Socket socket, List<DataOutputStream> listaClientes, DataOutputStream salidaActual) {
            this.socket = socket;
            this.listaClientes = listaClientes;
            this.salidaActual = salidaActual;
            try {
                dis = new DataInputStream(socket.getInputStream());
            } catch (Exception e) {
                System.err.println("Error obteniendo flujo de entrada: " + e.getMessage());
            }
		}
        @Override
        public void run() {
            try {
                String mensaje;
                do {
                    mensaje = dis.readUTF();
                    System.out.println("Mensaje recibido: " + mensaje);

                    synchronized (listaClientes) {
                        for (DataOutputStream out : listaClientes) {
                        	if(out != salidaActual) out.writeUTF(mensaje);
                        }
                    }
                } while (!mensaje.equalsIgnoreCase("fin"));
            } catch (Exception e) {
                System.err.println("Error en la recepción desde un cliente: " + e.getMessage());
            } finally {
                try {
                	synchronized (listaClientes) {
						listaClientes.remove(salidaActual);
					}
                    socket.close();
                } catch (Exception e) {
                    System.err.println("Error cerrando el socket: " + e.getMessage());
                }
            }
        }
		
	}
}



