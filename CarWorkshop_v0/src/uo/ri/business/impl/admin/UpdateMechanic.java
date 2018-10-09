package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;
import alb.util.jdbc.Jdbc;

public class UpdateMechanic {
	
	private Long idMecanico;
	private String nombre;
	private String apellidos;
	
	public UpdateMechanic(Long idMecanico, String nombre, String apellidos)
	{
		this.idMecanico = idMecanico;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	public void execute()
	{

		Connection c = null;

		try 
		{
			c = Jdbc.getConnection();
			MecanicosGateway mg = PersistenceFactory.getMecanicosGateway();		
			mg.setConnection(c);
			mg.update(idMecanico, nombre, apellidos);
					
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(c);
		}
	}

}
