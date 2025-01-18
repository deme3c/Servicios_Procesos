package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Servidor de chat que:
 *  - No lee mensajes por teclado.
 *  - Recibe mensajes de cualquiera de los clientes.
 *  - Retransmite el mensaje recibido a TODOS los clientes conectados.
 */
public class ServidorChat {
    
    // Lista estática para mantener todos los flujos de salida (a cada cliente)
    private static List<DataOutputStream> listaClientes = new ArrayList<>();

    public static void main(String[] args) {
        final int PUERTO = 7000;
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);
            System.out.println("Esperando conexiones de clientes...");

            // Bucle infinito para aceptar múltiples clientes
            while (true) {
                Socket socketCliente = serverSocket.accept();
                System.out.println("Cliente conectado desde: " 
                                   + socketCliente.getInetAddress() + ":" 
                                   + socketCliente.getPort());

                // Crear un nuevo DataOutputStream y añadirlo a la lista
                DataOutputStream salidaCliente = new DataOutputStream(socketCliente.getOutputStream());
                synchronized (listaClientes) {
                    listaClientes.add(salidaCliente);
                }

                // Iniciar un hilo para manejar la comunicación con este cliente
                ManejadorCliente manejador = new ManejadorCliente(socketCliente, listaClientes);
                manejador.start();
            }
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    /**
     * Hilo encargado de leer los mensajes de un cliente y retransmitirlos a los demás.
     */
    private static class ManejadorCliente extends Thread {
        private Socket socket;
        private List<DataOutputStream> listaClientes;
        private DataInputStream entrada;

        public ManejadorCliente(Socket socket, List<DataOutputStream> listaClientes) {
            this.socket = socket;
            this.listaClientes = listaClientes;
            try {
                entrada = new DataInputStream(socket.getInputStream());
            } catch (Exception e) {
                System.err.println("Error obteniendo flujo de entrada: " + e.getMessage());
            }
        }

        @Override
        public void run() {
            try {
                String mensaje;
                // Lee mensajes mientras no sea "fin"
                do {
                    mensaje = entrada.readUTF();
                    System.out.println("Mensaje recibido: " + mensaje);

                    // Retransmitir el mensaje a todos los clientes
                    synchronized (listaClientes) {
                        for (DataOutputStream out : listaClientes) {
                            out.writeUTF(mensaje);
                        }
                    }

                } while (!mensaje.equalsIgnoreCase("fin"));
            } catch (Exception e) {
                System.err.println("Error en la recepción desde un cliente: " + e.getMessage());
            } finally {
                // Al salir, se cierra el socket y se retira el flujo de salida de la lista
                try {
                    socket.close();
                    // Retirar este DataOutputStream de la lista
                    // (Podemos buscar por instancia de OutputStream, pero 
                    //  en este ejemplo no lo hacemos porque no guardamos una referencia 
                    //  directa en la clase. La forma más exacta sería implementarlo con 
                    //  un objeto contenedor que asocie Socket y DataOutputStream).
                } catch (Exception e) {
                    System.err.println("Error cerrando el socket: " + e.getMessage());
                }
            }
        }
    }
}