package uo.ri.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class FindClientsById {
	
	private Long id;
	
	private ClientesGateway clienteGateway;
	
	public FindClientsById(Long id) 
	{
		this.id = id;
	}
	
	public Map<String, Object> execute() throws BusinessException
	{
		Map<String, Object> mapa = null;
		Connection c = null;

		try {
			
			c = Jdbc.getConnection();
								
			clienteGateway = PersistenceFactory.getClientesGateway();
			clienteGateway.setConnection(c);
			
			comprobarClienteExiste(id);
			
			mapa = clienteGateway.findClientsById(id);
								
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(c);
		}
		
		return mapa;
	}

	/**
	 * Comprueba que el cliente que se quiera ver detalle se encuentre registrado en el sistema
	 * @param id del cliente del que se desea ver detalle
	 * @throws BusinessException Puede ser que el cliente no esté registrado en el sistema.
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
