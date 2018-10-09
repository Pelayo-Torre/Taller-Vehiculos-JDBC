package uo.ri.ui.foreman.action;

import uo.ri.business.ForemanService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class DeleteClientAction implements Action{
	
	public DeleteClientAction()
	{
		
	}

	@Override
	public void execute() throws BusinessException 
	{

		Long idCliente = Console.readLong("Id del cliente que desea eliminar");

		ForemanService fs = ServicesFactory.getForemanService();
		fs.deleteClient(idCliente);
		
		Console.println("Cliente dado de baja");		
	}

}
