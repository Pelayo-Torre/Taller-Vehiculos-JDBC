package uo.ri.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class FindClientsRecommended {
	
	private Long id;
	
	private ClientesGateway clienteGateway;

	public FindClientsRecommended(Long id)
	{
		this.id = id;
	}
	
	public List<Map<String, Object>> execute() throws BusinessException 
	{
		
		List<Map<String,Object>> lista = null;
		
		Connection c = null;

		try 
		{
			c = Jdbc.getConnection();
							
			clienteGateway = PersistenceFactory.getClientesGateway();
			clienteGateway.setConnection(c);
			
			comprobarClienteExiste(id);
				
			lista = clienteGateway.findClientsRecommended(id);
							
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

	/**
	 * Comprueba que el cliente del que se desea ver sus recomendaciones existe en el sistema
	 * @param id del cliente del que se desea ver sus recomendaciones
	 * @throws BusinessException Puede ser que el cliente recomendador no esté en el sistema
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
