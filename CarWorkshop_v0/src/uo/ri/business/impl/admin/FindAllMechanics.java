package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;
import alb.util.jdbc.Jdbc;

public class FindAllMechanics {

	public FindAllMechanics()
	{
		
	}
	
	public List<Map<String, Object>> execute()
	{
		List<Map<String, Object>> lista = null;

		Connection c = null;

		try 
		{
			c = Jdbc.getConnection();
			MecanicosGateway mg = PersistenceFactory.getMecanicosGateway();
			mg.setConnection(c);
			lista = mg.findAll();
			
		} 
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(c);
		}
		
		return lista;
	}
	
}
