package uo.ri.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;


public interface FacturasGateway {
	
	/**
	 * Establece la conexión con el gateway
	 * @param c la conexión a la Base de datos
	 */
	public void setConnection(Connection c);
	
	/**
	 * Crea una nueva factura
	 * @param numeroFactura numero de la nueva factura
	 * @param fechaFactura fecha de la nueva factura
	 * @param iva de la factura
	 * @param totalConIva importe total de la factura
	 * @return La factura nueva
	 * @throws SQLException
	 */
	public Long crearFactura(long numeroFactura, Date fechaFactura,
			double iva, double totalConIva) throws SQLException;
	
	/**
	 * Coge el ultimo numero de factura del sistema
	 * @param numeroFactura
	 * @return el ultimo numero de factura
	 * @throws SQLException
	 */
	public long getGeneratedKey(long numeroFactura) throws SQLException;
	
	/**
	 * Genera el nuevo numero de factura
	 * @return el nuevo número de factura
	 * @throws SQLException
	 */
	public Long generarNuevoNumeroFactura() throws SQLException;
	
	
}
