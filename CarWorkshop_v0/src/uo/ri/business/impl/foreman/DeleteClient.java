package uo.ri.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class DeleteClient {

	private Long id;
	
	private ClientesGateway clienteGateway;
	
	public DeleteClient(Long id)
	{
		this.id = id;
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
			
			comprobarClienteConVehiculoRegistrado(id);
			
			clienteGateway.eliminarMediosPago(id);
			
			clienteGateway.eliminarCliente(id);
			
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
	 * Comprueba que el cliente tenga un vehículo registrado antes de eliminarlo
	 * @param id del cliente del que se desea saber si tiene vehículo registrado
	 * @throws BusinessException Puede ser que tenga vehículo registrado y no se pueda eliminar
	 */
	private void comprobarClienteConVehiculoRegistrado(Long id) throws BusinessException 
	{
		if(clienteGateway.clienteConVehiculoRegistrado(id) == true) 
		{
			throw new BusinessException("El cliente con ID "+id+
					" tiene vehículos registrados");
		}
	}

	/**
	 * Comprueba que el cliente que se quiere eliminar existe en el sistema
	 * @param id del cliente que se quiere eliminr
	 * @throws BusinessException Puede ser que se quiera eliminar un cliente que no exista en el sistema
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
