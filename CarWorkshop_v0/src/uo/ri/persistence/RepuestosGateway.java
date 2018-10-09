package uo.ri.persistence;

import java.sql.Connection;
import java.sql.SQLException;

public interface RepuestosGateway {
	
	/**
	 * Establece la conexión con el gateway
	 * @param c la conexión a la base de datos
	 */
	public void setConnection(Connection c);
	
	/**
	 * Devuelve el importe de una avería
	 * @param idAveria especificada
	 * @return importe 
	 * @throws SQLException
	 */
	public double getImpote(Long idAveria) throws SQLException;

}
