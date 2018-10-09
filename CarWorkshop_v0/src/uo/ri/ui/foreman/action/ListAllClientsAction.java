package uo.ri.ui.foreman.action;

import java.util.List;
import java.util.Map;

import uo.ri.business.ForemanService;
import uo.ri.conf.ServicesFactory;
import uo.ri.ui.util.Printer;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListAllClientsAction implements Action{

	public ListAllClientsAction()
	{
		
	}

	@Override
	public void execute() 
	{
		
		Console.println("Listado de todos los clientes");
		
		List<Map<String, Object>> lista;

		ForemanService fs = ServicesFactory.getForemanService();
		lista = fs.findAllClients();
		
		if(lista.isEmpty())
		{
			Console.println("No hay clientes registrados en el sistema");
		}
		else
		{
			Printer.printClients(lista);
		}
	}
	
	
}
