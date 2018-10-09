package uo.ri.ui.foreman.action;

import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ForemanService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;
import uo.ri.ui.util.Printer;

public class ListClientsByIdAction implements Action{

	public ListClientsByIdAction() 
	{
		
	}
	
	@Override
	public void execute() throws BusinessException  
	{

		Long id = Console.readLong("Introduzca el ID del usuario del que se desea ver detalle");
		
		Map<String, Object> mapa;
				
		ForemanService fs = ServicesFactory.getForemanService();
		mapa = fs.findClientById(id);
		
		Printer.printClientsById(mapa);
	}

}
