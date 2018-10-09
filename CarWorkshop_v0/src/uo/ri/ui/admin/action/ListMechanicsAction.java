package uo.ri.ui.admin.action;

import java.util.List;
import java.util.Map;

import uo.ri.business.AdminService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;
import uo.ri.ui.util.Printer;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListMechanicsAction implements Action {

	public ListMechanicsAction(){
		
	}
	
	@Override
	public void execute() throws BusinessException 
	{
		Console.println("\nListado de mecánicos\n");  

		List<Map<String, Object>> lista;

		AdminService as = ServicesFactory.getAdminService();
		lista = as.findAllMechanics();
		
		Printer.printMechanics(lista);
		
	}
}
