package uo.ri.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.common.BusinessException;

public interface IntervencionesGateway {
	
	/**
	 * Establece la conexión con el gateway
	 * @param c la conexión a la base de datos
	 */
	public void setConnection(Connection c);
	
	/**
	 * Devuelve el importe total de la mano de obra
	 * @param idAveria la avería que se reparó
	 * @return importe total de la mano de obra
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public double getImporteManoObra(Long idAveria) throws SQLException, BusinessException;

}
