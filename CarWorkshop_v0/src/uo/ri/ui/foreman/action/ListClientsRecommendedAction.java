package uo.ri.ui.foreman.action;

import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ForemanService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;
import uo.ri.ui.util.Printer;

public class ListClientsRecommendedAction implements Action{

	public ListClientsRecommendedAction() 
	{
		
	}
	
	@Override
	public void execute() throws BusinessException  
	{
		
		Long id = Console.readLong("ID del cliente recomendador");
		
		List<Map<String, Object>> lista;
		
		ForemanService fs = ServicesFactory.getForemanService();
		lista = fs.FindClientsRecommended(id);
		
		if(lista.isEmpty())
		{
			Console.println("El cliente que ha especificado no ha "
					+ "recomendado a ningún otro cliente");
		}
		else 
		{
			Printer.printClients(lista);
		}
	}

}
