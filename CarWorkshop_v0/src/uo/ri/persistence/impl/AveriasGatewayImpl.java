package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Conf;
import uo.ri.persistence.AveriasGateway;

public class AveriasGatewayImpl implements AveriasGateway{

	private Connection connection;
	
	@Override
	public void setConnection(Connection c) 
	{
		this.connection = c;
	}

	@Override
	public void verificarEstadoAveria(Long idAveria) throws SQLException, BusinessException
	{
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(Conf.get("SQL_VERIFICAR_ESTADO_AVERIA"));
			pst.setLong(1, idAveria);
				
			rs = pst.executeQuery();
			if (rs.next() == false)
			{
				throw new BusinessException("No existe la averia " + idAveria);
			}
				
			String status = rs.getString(1); 
			if (! "TERMINADA".equalsIgnoreCase(status) ) 
			{
				throw new BusinessException("No está terminada la avería " + idAveria);
			}
				
			rs.close();
			
		} finally 
		{
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void cambiarEstadoAveria(Long idAveria, String status) throws SQLException 
	{
		PreparedStatement pst = null;
		
		try 
		{
			pst = connection.prepareStatement(Conf.get("SQL_ACTUALIZAR_ESTADO_AVERIA"));
			
			pst.setString(1, status);
			pst.setLong(2, idAveria);

			pst.executeUpdate();
			
		} 
		finally 
		{
			Jdbc.close(pst);
		}
	}

	@Override
	public void vincularAveriaConFactura(Long idAveria, Long idFactura) throws SQLException
	{
		PreparedStatement pst = null;
		
		try 
		{
			pst = connection.prepareStatement(Conf.get("SQL_VINCULAR_AVERIA_FACTURA"));

			pst.setLong(1, idFactura);
			pst.setLong(2, idAveria);

			pst.executeUpdate();
			
		} 
		finally 
		{
			Jdbc.close(pst);
		}
	}

	@Override
	public void updateImporte(Long idAveria, double totalAveria) throws SQLException {
		
		PreparedStatement pst = null;
		
		try 
		{
			
			pst = connection.prepareStatement(Conf.get("SQL_UPDATE_IMPORTE_AVERIA"));
			pst.setDouble(1, totalAveria);
			pst.setLong(2, idAveria);
			pst.executeUpdate();
		}	
		finally 
		{
			Jdbc.close(pst);
		}
	}

}
