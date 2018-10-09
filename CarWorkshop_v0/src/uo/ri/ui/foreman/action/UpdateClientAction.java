package uo.ri.ui.foreman.action;

import uo.ri.business.ForemanService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class UpdateClientAction implements Action{
	
	public UpdateClientAction()
	{
		
	}

	@Override
	public void execute() throws BusinessException 
	{
		
		Long id = Console.readLong("id");
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");
		String city = Console.readString("Ciudad");
		String street = Console.readString("Calle");
		String zipcode = Console.readString("Código Postal");
		String telefono = Console.readString("Teléfono");
		String correo = Console.readString("Correo");
		
		ForemanService fs = ServicesFactory.getForemanService();
		fs.updateClient(id, nombre, apellidos, city, street, 
				zipcode, telefono, correo);
		
		Console.println("Datos del cliente actualizados");
	}
	

}
