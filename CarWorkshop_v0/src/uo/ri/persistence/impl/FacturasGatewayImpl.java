package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.FacturasGateway;

public class FacturasGatewayImpl implements FacturasGateway{

	private Connection connection;
	
	public FacturasGatewayImpl(){
		
	}
	
	@Override
	public void setConnection(Connection c) 
	{
		this.connection = c;
		
	}

	@Override
	public Long crearFactura(long numeroFactura, Date fechaFactura, double iva, double totalConIva)
			throws SQLException 
	{
		PreparedStatement pst = null;

		try {
			
			pst = connection.prepareStatement(Conf.get("SQL_INSERTAR_FACTURA"));
			pst.setLong(1, numeroFactura);
			pst.setDate(2, new java.sql.Date(fechaFactura.getTime()));
			pst.setDouble(3, iva);
			pst.setDouble(4, totalConIva);
			pst.setString(5, "SIN_ABONAR");

			pst.executeUpdate();
			
		} 
		finally 
		{
			Jdbc.close(pst);
		}
		
		return getGeneratedKey(numeroFactura); 
	}

	@Override
	public long getGeneratedKey(long numeroFactura) throws SQLException
	{
		
		PreparedStatement pst = null;
		ResultSet rs = null;

		try 
		{
			pst = connection.prepareStatement(Conf.get("SQL_RECUPERAR_CLAVE_GENERADA"));
			pst.setLong(1, numeroFactura);
			rs = pst.executeQuery();
			rs.next();

			return rs.getLong(1);
			
		} finally {
			Jdbc.close(rs, pst);
		}
		
	}

	@Override
	public Long generarNuevoNumeroFactura() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try 
		{
			pst = connection.prepareStatement(Conf.get("SQL_ULTIMO_NUMERO_FACTURA"));
			rs = pst.executeQuery();
			
			if (rs.next()) 
			{
				return rs.getLong(1) + 1; 
			} 
			else 
			{  
				return 1L;
			}
		} 
		finally 
		{
			Jdbc.close(rs, pst);
		}
	}


}
