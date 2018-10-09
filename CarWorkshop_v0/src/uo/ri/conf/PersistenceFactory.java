package uo.ri.conf;

import uo.ri.persistence.AveriasGateway;
import uo.ri.persistence.BonosGateway;
import uo.ri.persistence.ClientesGateway;
import uo.ri.persistence.FacturasGateway;
import uo.ri.persistence.IntervencionesGateway;
import uo.ri.persistence.MecanicosGateway;
import uo.ri.persistence.RepuestosGateway;
import uo.ri.persistence.impl.AveriasGatewayImpl;
import uo.ri.persistence.impl.BonosGatewayImpl;
import uo.ri.persistence.impl.ClientesGatewayImpl;
import uo.ri.persistence.impl.FacturasGatewayImpl;
import uo.ri.persistence.impl.IntervencionesGatewayImpl;
import uo.ri.persistence.impl.MecanicosGatewayImpl;
import uo.ri.persistence.impl.RepuestosGatewayImpl;

public class PersistenceFactory {
	
	public PersistenceFactory(){
		
	}

	public static MecanicosGateway getMecanicosGateway()
	{
		MecanicosGateway mg = new MecanicosGatewayImpl();
		return mg;
	}
	
	public static FacturasGateway getFacturasGateway()
	{
		FacturasGateway fg = new FacturasGatewayImpl();
		return fg;
	}
	
	public static ClientesGateway getClientesGateway() 
	{
		ClientesGateway cg = new ClientesGatewayImpl();
		return cg;
	}
	
	public static BonosGateway getBonosGateway() 
	{
		BonosGateway bg = new BonosGatewayImpl();
		return bg;
	}
	
	public static AveriasGateway getAveriasGateway()
	{
		AveriasGateway ag = new AveriasGatewayImpl();
		return ag;
	}
	
	public static IntervencionesGateway getIntervencionesGatewayImpl()
	{
		IntervencionesGateway ig= new IntervencionesGatewayImpl();
		return ig;
	}
	
	public static RepuestosGateway getRepuestosGateway()
	{
		RepuestosGateway rg = new RepuestosGatewayImpl();
		return rg;
	}
}
