package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.BonosGateway;

public class GenerarBonos {
	
	private BonosGateway bonoGateway;
	
	public GenerarBonos() 
	{
		
	}
		
	public void execute() 
	{
		Connection c = null;

		try 
		{	
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
			
			bonoGateway = PersistenceFactory.getBonosGateway();
			bonoGateway.setConnection(c);
			
			generarBonos();
			
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
	 * Se encarga de, para cada cliente candidato a tener bonos, generar sus respectivos
	 * bonos.
	 * @param clientes lista de clintes que van a recibir bono.
	 */
	private void generarBonos() 
	{
		ArrayList<Long> clientes = bonoGateway.clientesCandidatos();
		
		for(int i=0; i<clientes.size(); i++) 
		{
			ArrayList<Long> averias = null;
			averias = bonoGateway.obtenerAveriasCliente(clientes.get(i));
			
			int bonos = averias.size() / 3;
			int averiasUsadasBono = bonos * 3;
			
			marcarAveriasUsadas(averiasUsadasBono, averias);
			crearBono(bonos, clientes.get(i));
		}
	}
	
	/**
	 * Marca las averías utilizadas para generar el bono como utilizadas
	 * @param averiasUsadasBono número de averías que van a ser marcadas
	 * @param averias lista de averías del cliente 
	 */
	private void marcarAveriasUsadas(int averiasUsadasBono, ArrayList<Long> averias)
	{
		for(int i=0; i<averiasUsadasBono; i++)
		{
			bonoGateway.marcarAveriaUsada(averias.get(i));
		}
	}
	
	/**
	 * Genera los bonos para cliente especificado
	 * @param numeroBonos que se van a dar al cliente
	 * @param idCliente que va a recibir los bonos
	 */
	private void crearBono(int numeroBonos, Long idCliente)
	{
		for(int w=0; w<numeroBonos; w++)
		{
			String codigo = obtenerCodigoUltimoBono();
			
			if(codigo == null)
			{
				codigo = "B1010";
			}
			
			codigo = obtenerCodigoNuevoBono(codigo);
			
			bonoGateway.añadirBono("TBonos", 0.0, codigo, 20.0, idCliente, "Por tres averías");
		}
	}
	
	/**
	 * Obtiene el código del último bono añadido a medios de pago
	 * @return el código del bono
	 */
	private String obtenerCodigoUltimoBono()
	{
		String codigo= bonoGateway.obtenerCodigoUltimoBono();
		return codigo;
	}
	
	/**
	 * Método auxiliar para generar el código del
	 * nuevo bono a añadir.
	 * @param codigo del último bono añadido
	 * @return el nuevo código
	 */
	private String obtenerCodigoNuevoBono(String codigo) 
	{
		String [] div = codigo.split("B");
		int n = Integer.parseInt(div[1]);
		codigo = "B"+String.valueOf((n+10));
		return codigo;
	}
	
}
