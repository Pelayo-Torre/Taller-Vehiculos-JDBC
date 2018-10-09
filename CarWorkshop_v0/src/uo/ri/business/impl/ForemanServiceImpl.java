package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.ForemanService;
import uo.ri.business.impl.foreman.AddClient;
import uo.ri.business.impl.foreman.DeleteClient;
import uo.ri.business.impl.foreman.FindAllClients;
import uo.ri.business.impl.foreman.FindClientsById;
import uo.ri.business.impl.foreman.FindClientsRecommended;
import uo.ri.business.impl.foreman.UpdateClient;
import uo.ri.common.BusinessException;

public class ForemanServiceImpl implements ForemanService{

	/**
	 * Constructor de la clase
	 */
	public ForemanServiceImpl() {
		
	}
	
	/**
	 * Instancia y ejecuta el AddClient.
	 */
	@Override
	public void addClient(String city, String street, String zipcode, String apellidos, String dni, String nombre,
			String telefono, String correo, Long idRecomendador) throws BusinessException 
	{
		AddClient ac = new AddClient(city, street, zipcode ,apellidos, dni, nombre, telefono, correo, idRecomendador);
		ac.execute();
	}

	/**
	 * Instancia y ejecuta el DeleteClient.
	 */
	@Override
	public void deleteClient(Long id) throws BusinessException
	{
		DeleteClient dc = new DeleteClient(id);
		dc.execute();
	}

	/**
	 * Instancia y ejecuta el UpdateClient.
	 */
	@Override
	public void updateClient(Long id, String nombre, String apellidos,
			String city, String street, String zipcode,
			String telefono, String correo) throws BusinessException
	{
		UpdateClient uc = new UpdateClient(id, nombre, apellidos, city,
				street, zipcode, telefono, correo);
		uc.execute();
	}

	/**
	 * Instancia y ejecuta el FindAllClients.
	 * @throws BusinessException 
	 */
	@Override
	public List<Map<String, Object>> findAllClients()
	{
		FindAllClients fac = new FindAllClients();
		return fac.execute();
	}

	/**
	 * Instancia y ejecuta el FindClientById.
	 */
	@Override
	public Map<String, Object> findClientById(Long id) throws BusinessException
	{
		FindClientsById fcbi = new FindClientsById(id);
		return fcbi.execute();
	}

	/**
	 * Instancia y ejecuta el FindClientRecommended.
	 */
	@Override
	public List<Map<String, Object>> FindClientsRecommended(Long id) throws BusinessException
	{
		FindClientsRecommended lcr = new FindClientsRecommended(id);
		return lcr.execute();
	}

}
