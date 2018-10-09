package uo.ri.ui.admin.action;

import uo.ri.business.AdminService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class UpdateMechanicAction implements Action {

	public UpdateMechanicAction(){
		
	}
	
	@Override
	public void execute() throws BusinessException {
		
		Long id = Console.readLong("Id del mecánico"); 
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
				
		AdminService as = ServicesFactory.getAdminService();
		as.updateMechanic(id, nombre, apellidos);
		
		Console.println("Mecánico actualizado");
	}

}
