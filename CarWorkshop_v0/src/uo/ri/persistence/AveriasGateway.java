package uo.ri.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.common.BusinessException;

public interface AveriasGateway {
	
	/**
	 * Establece la conexión con el gateway
	 * @param c conexión a la base de datos
	 */
	public void setConnection(Connection c);
	
	/**
	 * Verifica que el estado de la averia sea correcto
	 * @param idAveria de la avería a verificar
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public void verificarEstadoAveria(Long idAveria)throws SQLException, BusinessException ; 
	
	/**
	 * Cambia el estado de la averia
	 * @param idAveria de la averia de la que se va a cambiar el estado.
	 * @param status
	 * @throws SQLException
	 */
	public void cambiarEstadoAveria(Long idAveria, String status)throws SQLException ;
	
	/**
	 * Vincula una avería con su correspondiente factura
	 * @param idAveria de la averia que se va a vincular
	 * @param idFactura
	 * @throws SQLException
	 */
	public void vincularAveriaConFactura(Long idAveria, Long idFactura)throws SQLException ;
	
	/**
	 * Actualiza el importe total de una aveía
	 * @param idAveria de la que se va a actualizar el importe
	 * @param totalAveria el importe total
	 * @throws SQLException
	 */
	public void updateImporte(Long idAveria, double totalAveria)throws SQLException ;

}
