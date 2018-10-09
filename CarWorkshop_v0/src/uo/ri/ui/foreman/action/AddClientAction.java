package uo.ri.ui.foreman.action;

import uo.ri.business.ForemanService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class AddClientAction implements Action{
	
	public AddClientAction()
	{
		
	}

	@Override
	public void execute() throws BusinessException 
	{
	
		String dni = Console.readString("DNI");
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");
		String city = Console.readString("Ciudad");
		String street = Console.readString("Calle");
		String zipcode = Console.readString("Código Postal");
		String telefono = Console.readString("Teléfono");
		String correo = Console.readString("Correo");
		Long idRecomendador = null;
			
		if(Console.readString("¿Viene usted recomendado por algún cliente? (s/n) ").equalsIgnoreCase("s"))
		{
			idRecomendador = Console.readLong("Introduzca el id del cliente que le recomendó ");
		}
		
		ForemanService fs = ServicesFactory.getForemanService();
		fs.addClient(city, street, zipcode, apellidos, dni, nombre, telefono, correo, idRecomendador);
		
		Console.println("Cliente añadido correctamente");
	}	

}
