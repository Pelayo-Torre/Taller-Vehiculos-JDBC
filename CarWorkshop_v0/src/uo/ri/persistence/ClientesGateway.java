package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface ClientesGateway {

	/**
	 * Establece la conexión con el gateway
	 * @param c conexión para la Base de datos
	 */
	public void setConnection(Connection c);
	
	/**
	 * Comprueba que el cliente existe en el sistema
	 * @param dni del cliente del que se quiere saber si
	 * existe o no existe
	 * @return true si existe, false en caso contrario
	 */
	public boolean comprobarClienteExistePorDni(String dni);
	
	/**
	 * Añade un nuevo cliente al sistema.
	 * @param city ciudad donde vive el cliente
	 * @param street calle donde vive el cliente
	 * @param zipcode código postal del lugar donde vive
	 * @param apellidos del cliente
	 * @param dni del cliente
	 * @param nombre del cliente
	 * @param telefono del cliente
	 * @param correo del cliente
	 * @param idRecomendador es el id del cliente que le recomendó nuestro taller
	 */
	public void añadirCliente(String city, String street, String zipcode,String apellidos, 
			String dni, String nombre, String telefono, String correo, Long idRecomendador);
	
	/**
	 * Comprueba que el cliente recomendador está registrado en nuestro sistema
	 * @param idRecomendador id del cliente recomendador
	 * @return true si está registrado, false en caso contrario
	 */
	public boolean recomendadorRegistrado(Long idRecomendador);
	
	/**
	 * Comprueba que el cliente recomendador tiene una factura pagada
	 * @param idRecomendador id del cliente recomendador
	 * @return true si tiene una factura pagada, false en caso contrario.
	 */
	public boolean recomendadorFacturaPagada(Long idRecomendador);
	
	/**
	 * Obtiene el id de un cliente dado su dni
	 * @param dni del cliente del que se desea obtener su id.
	 * @return id del cliente
	 */
	public Long obtenerIdAPartirDNI(String dni);
	
	/**
	 * Crea un medio de pago de tipo metálico cuando se registra 
	 * un cliente en el taller
	 * @param id del cliente que se registró.
	 */
	public void  asociarMedioPago(Long id);
	
	/**
	 * Comprueba que el cliente está registrado en el sistema 
	 * a partir de si id
	 * @param id del cliente del que se quiere saber si existe
	 * @return true si existe, false en caso contrario.
	 */
	public boolean comprobarClienteExistentePorId(Long id);
	
	/**
	 * Comprueba si el cliente tiene un vehículo registrado
	 * en el taller
	 * @param id del cliente del que se quiere saber 
	 * si tiene un vehículo registrado o no.
	 * @return true si lo tiene, false en caso contrario
	 */
	public boolean clienteConVehiculoRegistrado(Long id);
	
	/**
	 * Elimina un cliente del sistema
	 * @param id del cliente que se desea eliminar
	 */
	public void eliminarCliente(Long id);
	
	/**
	 * Elimina el medio de pago metálico
	 * del cliente que se acaba de eliminar
	 * @param id del cliente del que se va a eliminar el medio de pago
	 * metálico
	 */
	public void eliminarMediosPago(Long id);
	
	/**
	 * Devuelve una lista con todos los clientes del sistema
	 * @return una lista de maps con todos los clientes
	 */
	public List<Map<String, Object>> findAllClients();	
	
	/**
	 * es la operación VER DETALLE de un cliente concreto
	 * @param id del cliente del que se desea ver detalle
	 * @return un mapa con la información del cliente.
	 */
	public Map<String, Object> findClientsById(Long id);
	
	/**
	 * Devuelve una lista con los clientes recomendados por otro cliente.
	 * @param id del cliente recomendador del que se desean ver los 
	 * clientes recomendados
	 * @return lista de mapas con la información de los clientes 
	 * recomendados
	 */
	public List<Map<String, Object>> findClientsRecommended(Long id);
	
	/**
	 * Actualiza los datos de un cliente especificado
	 * @param id del cliente del que se va a actualizar su informacion.
	 * @param nombre del cliente a actualizar
	 * @param apellidos del cliente a actualizar
	 */
	public void updateClient(Long id, String nombre, String apellidos, String city,
			String street, String zipcode, String telefono, String correo);
}
