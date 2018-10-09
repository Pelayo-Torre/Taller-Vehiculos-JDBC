package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class AddMechanicAction implements Action {
	
	public AddMechanicAction(){
		
	}

	@Override
	public void execute() throws BusinessException
	{
		
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
		
		AdminService as = ServicesFactory.getAdminService();
		as.newMechanic(nombre, apellidos);
		
		Console.println("Nuevo mecánico añadido");
	}

}
