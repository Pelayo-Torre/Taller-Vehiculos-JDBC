package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.conf.ServicesFactory;

public class GenerarBonosAction implements Action{

	public GenerarBonosAction() {
		
	}
	
	@Override
	public void execute() 
	{
		if(Console.readString("¿Desea generar los bonos? (s/n) ").equalsIgnoreCase("s"))
		{
			AdminService as = ServicesFactory.getAdminService();
			as.generarBonos();
			
			Console.println("Bonos generados satisfactoriamente");
		}
	}

}
