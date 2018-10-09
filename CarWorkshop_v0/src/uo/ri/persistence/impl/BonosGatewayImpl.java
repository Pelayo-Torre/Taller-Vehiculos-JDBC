package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.BonosGateway;

public class BonosGatewayImpl implements BonosGateway{
	
	private Connection c;
	
	public BonosGatewayImpl() 
	{
		
	}
	
	@Override
	public void setConnection(Connection c) 
	{
		this.c = c;
	}
	

	@Override
	public ArrayList<Long> clientesCandidatos() 
	{
		
		ArrayList<Long> clientes = new ArrayList<Long>();
		
		Statement st = null;
		ResultSet rs = null;
		
		try 
		{

			st = c.createStatement();
			rs = st.executeQuery(Conf.get("SQL_OBTENER_CLIENTES_CON_MAS_DE_DOS_AVERIAS_PAGADAS_NO_USADAS_BONO"));
			
			while(rs.next())
			{
				clientes.add(rs.getLong("ID"));
			}
			
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(rs, st);
		}
		
		return clientes;
	}

	@Override
	public void marcarAveriaUsada(Long idAveria) 
	{
		
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		try 
		{
			
			pst = c.prepareStatement(Conf.get("SQL_AVERIA_USADA_BONO_TRUE"));
			pst.setBoolean(1, true);
			pst.setLong(2, idAveria);
			
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
	public ArrayList<Long> obtenerAveriasCliente(Long idCliente) 
	{
		
		ArrayList<Long> averias = new ArrayList<Long>();
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try
		{
			
			pst = c.prepareStatement(Conf.get("SQL_OBTENER_AVERIAS_CLIENTE"));
			pst.setLong(1, idCliente);
			rs = pst.executeQuery();
			
			while(rs.next()) 
			{
				averias.add(rs.getLong("ID"));
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
		
		return averias;
	}

	@Override
	public String obtenerCodigoUltimoBono() 
	{
		
		ResultSet rs = null;
		Statement st = null;
		String codigo="";
		
		try 
		{
			
			st = c.createStatement();
			rs = st.executeQuery(Conf.get("SQL_OBTENER_CODIGO_ULTIMO_BONO"));
			
			while(rs.next()) 
			{
				codigo = rs.getString("CODIGO");
			}
			
		}
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		finally 
		{
			Jdbc.close(rs, st);
		}
		
		return codigo;
	}

	@Override
	public void añadirBono(String dtype, double acumulado, String codigo, double disponible, Long idCliente,
			String descripcion) 
	{
		
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		try 
		{
			
			pst = c.prepareStatement(Conf.get("SQL_GENERAR_BONO"));
			pst.setString(1, dtype);
			pst.setDouble(2, acumulado);
			pst.setString(3, codigo);
			pst.setDouble(4, disponible);
			pst.setLong(5, idCliente);
			pst.setString(6, descripcion);
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
