package uo.ri.business;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface ForemanService {

	/**
	 * Añade un cliente al sistema a partir de los datos introducidos
	 * @param city	ciudad del cliente
	 * @param street	calle del cliente
	 * @param zipcode	código postal de la ciudad del cliente
	 * @param apellidos	del cliente
	 * @param dni del cliente
	 * @param nombre del cliente
	 * @param telefono del cliente
	 * @param correo del cliente
	 * @param idRecomendador 	es el id del cliente que le recomendó a darse de alta en el taller
	 * @throws BusinessException	Puede ocurrir que al añadir un cliente, el dni introducido ya 
	 * se encuentre registrado en el sistema, o el id del cliente recomendador introducido no se 
	 * encuentre registrado en el sistema o que no tenga facturas pagadas
	 */
	public void addClient(String city, String street, String zipcode,String apellidos, 
			String dni, String nombre, String telefono, String correo, Long idRecomendador)throws BusinessException;
	
	/**
	 * Elimina un cliente del sistema
	 * @param id del cliente que se quiere eliminar
	 * @throws BusinessException Puede ocurrir que se desee eliminar un cliente que no se encuentre
	 * registrado en el sistema, o tenga vehículos registrados.
	 */
	public void deleteClient(Long id)throws BusinessException;
	
	/**
	 * Actualiza los datos más importantes del cliente especificado por su id
	 * @param id del cliente que se quiere actualizar
	 * @param nombre nuevo del cliente que se va a actualizar
	 * @param apellidos nuevos del liente que se va a actualizar
	 * @param city nueva del cliente que se va a actualizar
	 * @param street nueva del cliente que se va a actualizar
	 * @param zipcode nuevo del cliente que se va a actualizar
	 * @param telefono nuevo del cliente que se va a actualizar
	 * @param correo nuevo del cliente que se va a actualizar
	 * @throws BusinessException Puede ocurrir que queramos actualizar un cliente que 
	 * no se encuentre registrado en el sistema.
	 */
	public void updateClient(Long id, String nombre, String apellidos,
			String city, String street, String zipcode,
			String telefono, String correo)throws BusinessException;
	
	/**
	 * Devuelve un listado de todos los clientes del sistema
	 * @return	listado de clientes, una lista de mapas
	 */
	public List<Map<String, Object>> findAllClients();
	
	/**
	 * Es el 'ver detalle' de un cliente, donde puedes ver su información
	 * @param id del cliente del que se desea ver la información
	 * @return	Un mapa con la información del cliente
	 * @throws BusinessException 	Puede ocurrir que el cliente especificado
	 * no se encuentre registrado en el sistema.
	 */
	public Map<String, Object> findClientById(Long id)throws BusinessException;
	
	/**
	 * Lista de clientes recomendados por un cliente especificado
	 * @param id del cliente recomendador, del que se desea ver a quien recomendó
	 * @return	lista con los clientes recomendados
	 * @throws BusinessException	Puede ocurrir que el id del cliente especificado 
	 * no se encuentre registrado en el sistema
	 */
	public List<Map<String, Object>> FindClientsRecommended(Long id)throws BusinessException;
	
}
