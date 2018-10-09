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
import uo.ri.persistence.MecanicosGateway;

public class MecanicosGatewayImpl implements MecanicosGateway{

	private Connection c;
	
	public MecanicosGatewayImpl(){
		
	}
	
	@Override
	public void setConnection(Connection c) 
	{
		this.c = c;		
	}

	@Override
	public Map<String, Object> findById(Long id) 
	{
		return null;
	}

	@Override
	public List<Map<String, Object>> findAll() 
	{
		List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();

		PreparedStatement pst = null;
		ResultSet rs = null;

		try 
		{			
			pst = c.prepareStatement(Conf.get("SQL_FIND_ALL_MECHANICS"));
			rs = pst.executeQuery();
			while(rs.next()) 
			{
				Map<String,Object> mapa = new HashMap<String,Object>();
				mapa.put("id", rs.getLong(1));
				mapa.put("nombre", rs.getString(2));
				mapa.put("apellidos", rs.getString(3));
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
	public void update(Long id, String nombre, String apellidos) 
	{
		PreparedStatement pst = null;
		ResultSet rs = null;

		try 
		{							
			pst = c.prepareStatement(Conf.get("SQL_UPDATE_MECHANIC"));
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
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
	public void save(String nombre, String apellidos) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;

		try 
		{							
			pst = c.prepareStatement(Conf.get("SQL_INSERT_MECHANIC"));
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
							
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
	public void delete(Long id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try
		{
			pst = c.prepareStatement(Conf.get("SQL_DELETE_MECHANIC"));
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

}
