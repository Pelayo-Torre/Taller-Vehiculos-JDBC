package uo.ri.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class UpdateClient {
	
	private Long id;
	private String apellidos;
	private String nombre;
	private String city;
	private String street;
	private String zipcode;
	private String telefono;
	private String correo;
	
	private ClientesGateway clienteGateway;

	public UpdateClient(Long id, String nombre, String apellidos, String city
			,String street, String zipcode, String telefono, String correo)
	{
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
		this.telefono = telefono;
		this.correo = correo;
	}
	
	public void execute() throws BusinessException
	{
		Connection c = null;
		
		try
		{
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
			
			clienteGateway = PersistenceFactory.getClientesGateway();
			clienteGateway.setConnection(c);
			
			comprobarClienteExiste(id);
			
			clienteGateway.updateClient(id, nombre, apellidos, city, street, zipcode, telefono, correo);
			
			c.commit();
			
		} 
		catch (SQLException e)
		{
			try 
			{
				c.rollback();
			} 
			catch (SQLException e1) {}
			
			throw new RuntimeException(e);
		}
		finally
		{
			Jdbc.close(c);
		}
	}

	/**
	 * Comprueba que el cliente que se quiere actualizar está registrado en el sistema
	 * @param id del cliente que se quiere actualizar
	 * @throws BusinessException Puede ser que el cliente no esté registrado
	 */
	private void comprobarClienteExiste(Long id) throws BusinessException 
	{
		if(clienteGateway.comprobarClienteExistentePorId(id) == false) 
		{
			throw new BusinessException("El cliente con ID "+id+
					" no se encuentra registrado en nuestro sistema");
		}
	}
	
}
