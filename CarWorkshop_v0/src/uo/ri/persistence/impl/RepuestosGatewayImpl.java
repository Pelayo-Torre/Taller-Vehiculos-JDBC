package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.RepuestosGateway;

public class RepuestosGatewayImpl implements RepuestosGateway{
	
	private Connection connection;
	
	public RepuestosGatewayImpl() {
		
	}

	@Override
	public void setConnection(Connection c) 
	{
		this.connection = c;
	}

	@Override
	public double getImpote(Long idAveria) throws SQLException 
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try 
		{
			pst = connection.prepareStatement(Conf.get("SQL_IMPORTE_REPUESTOS"));
			pst.setLong(1, idAveria);
			
			rs = pst.executeQuery();
			if (rs.next() == false) 
			{
				return 0.0;
			}
			
			return rs.getDouble(1);
			
		}
		finally 
		{
			Jdbc.close(rs, pst);
		}
	}
	
	

}
