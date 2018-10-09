package uo.ri.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;
import alb.util.jdbc.Jdbc;


public class AddClient {

	private String dni;
	private String nombre;
	private String apellidos;
	private String city;
	private String street;
	private String telefono;
	private String correo;
	private String zipcode;
	private Long idRecomendador;	
	
	private ClientesGateway clienteGateway;
	
	public AddClient(String city, String street, String zipcode,String apellidos, 
			String dni, String nombre, String telefono, String correo, Long idRecomendador)
	{
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.city = city;
		this.street = street;
		this.telefono = telefono;
		this.correo = correo;
		this.zipcode = zipcode;
		this.idRecomendador = idRecomendador;
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
			
			comprobarClienteExite(dni);
			
			if(idRecomendador != null) 
			{
				comprobarRecomendadorRegistrado(idRecomendador);	
				comprobarRecomendadorFacturaPagada(idRecomendador);
			}
			
			clienteGateway.añadirCliente(city, street, zipcode, apellidos, dni, nombre, 
					telefono, correo, idRecomendador);
			
			Long id = clienteGateway.obtenerIdAPartirDNI(dni);
			
			clienteGateway.asociarMedioPago(id);
			
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
	 * Comprueba que el cliente recomendador tenga, al menos, una factura abonada
	 * @param idRecomendador del recomendador
	 * @throws BusinessException Puede ser que no tenga factura pagada
	 */
	private void comprobarRecomendadorFacturaPagada(Long idRecomendador) throws BusinessException 
	{
		if(clienteGateway.recomendadorFacturaPagada(idRecomendador) == false)
		{
			throw new BusinessException("El cliente " + idRecomendador +
					" no tiene ninguna factura pagada");
		}
	}

	/**
	 * Comprueba que el cliente recomendador se encuentre registrado en el sistema
	 * @param idRecomendador del recomendador
	 * @throws BusinessException Puede ser que no esté registrado
	 */
	private void comprobarRecomendadorRegistrado(Long idRecomendador) throws BusinessException
	{
		if(clienteGateway.recomendadorRegistrado(idRecomendador) == false) 
		{
			throw new BusinessException("El cliente " + idRecomendador +
					" no se encuentra registrado en nuestro sistema");
		}
	}	
	
	/**
	 * Hace una llamada a la BBDD para comprobar si el cliente que se va a registrar
	 * ya esxiste en el sistema
	 * @param dni del que se va a realizar la comprobación
	 * @throws BusinessException Puede ser que ya se encuentre registrado
	 */
	private void comprobarClienteExite(String dni) throws BusinessException
	{
		if(clienteGateway.comprobarClienteExistePorDni(dni) == true)
		{
			throw new BusinessException("El DNI "+dni+" ya se "
					+ "encuentra registrado en el sistema");
		}
	}
	
}
