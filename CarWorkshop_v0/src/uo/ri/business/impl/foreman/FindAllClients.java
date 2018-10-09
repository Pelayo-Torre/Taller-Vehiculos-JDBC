package uo.ri.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class FindAllClients {
	
	public FindAllClients(){
		
	}
	
	public List<Map<String, Object>> execute()
	{
		
		List<Map<String,Object>> lista = null;

		Connection c = null;

		try 
		{
			c = Jdbc.getConnection();
							
			ClientesGateway cg = PersistenceFactory.getClientesGateway();
			cg.setConnection(c);
			
			lista = cg.findAllClients();
							
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
