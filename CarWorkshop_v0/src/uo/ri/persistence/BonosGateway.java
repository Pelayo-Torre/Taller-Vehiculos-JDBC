package uo.ri.persistence;

import java.sql.Connection;
import java.util.ArrayList;

public interface BonosGateway {
	
	/**
	 * Asigna la conexión al Gateway
	 * @param c la conexión a la Base de datos
	 */
	public void setConnection(Connection c);
	
	/**
	 * Obtiene una lista de los clientes que son candidatos a recibir 
	 * un bono, es decir, aquellos que tienen 3 o más averías facturadas
	 * @return lista de los clientes
	 */
	public ArrayList<Long> clientesCandidatos();
	
	/**
	 * Una vez que se genera el bono, hay que marcar la avería que se
	 * usó para ese bono como usada_bono = true para que no se vuelva
	 * a usar para otro bono distinto.
	 * @param idAveria que va a ser marcada
	 */
	public void marcarAveriaUsada(Long idAveria);
	
	/**
	 * Obtiene las averías del cliente que estén facturadas y que no
	 * hayan sido utilizadas para generar un bono
	 * @param idCliente del que se van a obtener las averias.
	 * @return lista de averías del cliente
	 */
	public ArrayList<Long> obtenerAveriasCliente(Long idCliente);
	
	/**
	 * Obtiene el código del último bono generado para poder 
	 * asignarle el siguiente código a un nuevo bono.
	 * @return el codigo del último bono generado
	 */
	public String obtenerCodigoUltimoBono();
	
	/**
	 * Inserta en la tabla TMediosPago el nuevo bono
	 * que ha sido generado.
	 * @param dtype es el tipo de medio de pago
	 * @param acumulado	lo que lleva acumulado
	 * @param codigo del nuevo bono
	 * @param disponible 20€ por cada bono
	 * @param idCliente del cliente al que le pertenece el bono
	 * @param descripcion de porqué se le ha generado el bono
	 */
	void añadirBono(String dtype, double acumulado, String codigo, 
			double disponible, Long idCliente, String descripcion);
	
}
