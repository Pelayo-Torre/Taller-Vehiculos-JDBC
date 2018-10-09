package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.ClientesGateway;

public class ClientesGatewayImpl implements ClientesGateway{

	private Connection c;
	
	public ClientesGatewayImpl() 
	{
		
	}
	
	
	@Override
	public void setConnection(Connection c) 
	{
		this.c = c;
	}

	@Override
	public boolean comprobarClienteExistePorDni(String dni)
	{
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean existe = false;
		
		try 
		{
			
			pst = c.prepareStatement(Conf.get("SQL_CLIENTE_EXISTE_DNI"));
			pst.setString(1, dni);
			rs = pst.executeQuery();
			
			if(rs.next() == true) 
			{
				existe = true;
			}
			
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(rs, pst);
		}
		
		return existe;
	}

	

	@Override
	public boolean recomendadorRegistrado(Long idRecomendador) 
	{
	
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean registrado = true;
		
		try 
		{
					
			pst = c.prepareStatement(Conf.get("SQL_RECOMENDADOR_REGISTRADO"));
			pst.setLong(1, idRecomendador);
			rs = pst.executeQuery();
			
			if (rs.next() == false) 
			{
				registrado = false;
			}
			
		} 
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(rs, pst);
		}
		
		return registrado;
	}

	@Override
	public boolean recomendadorFacturaPagada(Long idRecomendador) 
	{
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		String status = null;
		boolean facturaPagada = false;
				
		try 
		{
							
			pst = c.prepareStatement(Conf.get("SQL_RECOMENDADOR_FACTURA_PAGADA"));
			pst.setLong(1, idRecomendador);
			rs = pst.executeQuery();
			
			while(rs.next()){
				status = rs.getString(1); 
				if(status.equals("ABONADA"))
					facturaPagada = true;
			}
						
			
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(rs, pst);
		}
		
		return facturaPagada;
	}

	@Override
	public Long obtenerIdAPartirDNI(String dni) 
	{
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		Long id = 0L;
		
		try 
		{
							
			pst = c.prepareStatement(Conf.get("SQL_OBTENER_ID_A_PARTIR_DNI"));	
			pst.setString(1, dni);
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				id = rs.getLong("ID");
			}
			
		}
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(rs, pst);
		}
		
		return id;
	}

	@Override
	public void asociarMedioPago(Long id) 
	{
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try 
		{

			pst = c.prepareStatement(Conf.get("SQL_CREAR_MEDIOPAGO"));
			pst.setString(1, "TMetalico");
			pst.setInt(2, 0);
			pst.setLong(3, id);
			pst.executeUpdate();
			
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(rs, pst);
		}
		
	}

	@Override
	public void añadirCliente(String city, String street, String zipcode, String apellidos, String dni, String nombre,
			String telefono, String correo, Long idRecomendador) 
	{
		
		PreparedStatement pst = null;
		ResultSet rs = null;

		try 
		{
							
			pst = c.prepareStatement(Conf.get("SQL_INSERTAR_CLIENTE"));
			pst.setString(1, city);
			pst.setString(2, street);
			pst.setString(3, zipcode);
			pst.setString(4, apellidos);
			pst.setString(5, dni);
			pst.setString(6, nombre);
			pst.setString(7, telefono);
			pst.setString(8, correo);
			
			if(idRecomendador != null) 
			{
				pst.setLong(9, idRecomendador);
			}
			else 
			{
				pst.setLong(9, 0);
			}
			pst.executeUpdate();
							
		} 
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(rs, pst);
		}
		
	}
	
	
	@Override
	public boolean comprobarClienteExistentePorId(Long id) 
	{
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean existe = true;
		
		try 
		{
			
			pst = c.prepareStatement(Conf.get("SQL_CLIENTE_EXISTE_ID"));
			pst.setLong(1, id);
			rs = pst.executeQuery();
			if(rs.next() == false)
			{
				existe = false;
			}
			
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(rs, pst);
		}
		
		return existe;
	}

	
	@Override
	public boolean clienteConVehiculoRegistrado(Long id) 
	{
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean vehiculoRegistrado = false;
		
		try 
		{
			
			pst = c.prepareStatement(Conf.get("SQL_CLIENTE_CON_VEHICULO_REGISTRADO"));
			pst.setLong(1, id);
			rs = pst.executeQuery();
			
			if(rs.next() == true) 
			{
				vehiculoRegistrado = true;
			}
			
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(rs, pst);
		}
		
		return vehiculoRegistrado;
	}

	
	@Override
	public void eliminarCliente(Long id)
	{
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try 
		{
			
			pst = c.prepareStatement(Conf.get("SQL_ELIMINAR_CLIENTE"));
			pst.setLong(1, id);
			pst.executeUpdate();
			
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(rs, pst);
		}
		
	}

	/**
	 * Elimina el medio de pago del cliente 
	 * especificado por su id.
	 */
	@Override
	public void eliminarMediosPago(Long id)
	{
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try 
		{
			
			pst = c.prepareStatement(Conf.get("SQL_ELIMINAR_MEDIO_PAGO"));
			pst.setLong(1, id);
			pst.executeUpdate();
			
		} 
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(rs, pst);
		}
		
	}

	@Override
	public List<Map<String, Object>> findAllClients() 
	{
		
		List<Map<String,Object>> lista = new ArrayList<Map<String, Object>>();
		
		PreparedStatement pst = null;
		ResultSet rs = null;

		try 
		{
							
			pst = c.prepareStatement(Conf.get("SQL_FIND_ALL_CLIENTS"));
			rs = pst.executeQuery();
			
			while(rs.next()) 
			{
				Map<String,Object> mapa = new HashMap<String,Object>();
				mapa.put("id", rs.getLong(1));
				mapa.put("nombre", rs.getString(2));
				mapa.put("apellidos", rs.getString(3));
				mapa.put("telefono", rs.getString(4));
				mapa.put("correo", rs.getString(5));
				mapa.put("city", rs.getString(6));
				mapa.put("street", rs.getString(7));
				mapa.put("zipcode", rs.getString(8));
				lista.add(mapa);
			}
							
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(rs, pst);
		}
		
		return lista;
	}

	@Override
	public Map<String, Object> findClientsById(Long id)
	{
		
		Map<String, Object> mapa = new HashMap<String,Object>();

		PreparedStatement pst = null;
		ResultSet rs = null;

		try
		{
			
			pst = c.prepareStatement(Conf.get("SQL_FIND_CLIENTE_BY_ID"));
			pst.setLong(1, id);
			rs = pst.executeQuery();
			
			while(rs.next()) 
			{
				mapa.put("id", rs.getLong(1));
				mapa.put("nombre", rs.getString(2));
				mapa.put("apellidos", rs.getString(3));
				mapa.put("telefono", rs.getString(4));
				mapa.put("correo", rs.getString(5));
				mapa.put("city", rs.getString(6));
				mapa.put("street", rs.getString(7));
				mapa.put("zipcode", rs.getString(8));
			}
								
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(rs, pst);
		}
		
		return mapa;
	}

	@Override
	public List<Map<String, Object>> findClientsRecommended(Long id)
	{
		
		List<Map<String,Object>> lista = new ArrayList<Map<String, Object>>();
		
		PreparedStatement pst = null;
		ResultSet rs = null;

		try 
		{
							
			pst = c.prepareStatement(Conf.get("SQL_CLIENTES_RECOMENDADOS_POR_RECOMENDADOR"));
			pst.setLong(1, id);
			rs = pst.executeQuery();
			
			while(rs.next()) 
			{
				Map<String,Object> mapa = new HashMap<String,Object>();
				mapa.put("id", rs.getLong(1));
				mapa.put("nombre", rs.getString(2));
				mapa.put("apellidos", rs.getString(3));
				mapa.put("telefono", rs.getString(4));
				mapa.put("correo", rs.getString(5));
				mapa.put("city", rs.getString(6));
				mapa.put("street", rs.getString(7));
				mapa.put("zipcode", rs.getString(8));
				lista.add(mapa);
			}
							
		} 
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(rs, pst);
		}
		
		return lista;
	}

	@Override
	public void updateClient(Long id, String nombre, String apellidos, String city,
			String street, String zipcode, String telefono, String correo) 
	{
		
		PreparedStatement pst = null;
		ResultSet rs = null;

		try 
		{
									
			pst = c.prepareStatement(Conf.get("SQL_ACTUALIZAR_CLIENTE"));
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setString(3, city);
			pst.setString(4, street);
			pst.setString(5, zipcode);
			pst.setString(6, telefono);
			pst.setString(7, correo);
			pst.setLong(8, id);
									
			pst.executeUpdate();
									
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(rs, pst);
		}
		
	}
	
	

}
