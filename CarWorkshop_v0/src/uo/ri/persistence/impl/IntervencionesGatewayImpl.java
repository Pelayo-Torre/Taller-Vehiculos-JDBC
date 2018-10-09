package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Conf;
import uo.ri.persistence.IntervencionesGateway;

public class IntervencionesGatewayImpl implements IntervencionesGateway{

	private Connection connection;
	
	public IntervencionesGatewayImpl() {
		
	}
	
	@Override
	public void setConnection(Connection c) 
	{
		this.connection = c;		
	}

	@Override
	public double getImporteManoObra(Long idAveria) throws SQLException, BusinessException 
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try 
		{
			pst = connection.prepareStatement(Conf.get("SQL_IMPORTE_MANO_OBRA"));
			pst.setLong(1, idAveria);
			
			rs = pst.executeQuery();
			if (rs.next() == false)
			{
				throw new BusinessException("La averia no existe o no se puede facturar");
			}
			
			return rs.getDouble(1);
			
		} 
		catch (BusinessException e) 
		{
			throw e;
		}
		finally 
		{
			Jdbc.close(rs, pst);
		}
	}

}
