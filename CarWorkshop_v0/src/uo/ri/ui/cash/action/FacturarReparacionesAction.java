package uo.ri.ui.cash.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uo.ri.business.CashService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;
import uo.ri.ui.util.Printer;
import alb.util.console.Console;
import alb.util.menu.Action;

public class FacturarReparacionesAction implements Action {

	public FacturarReparacionesAction()
	{
		
	}
	
	@Override
	public void execute() throws BusinessException 
	{
		
		List<Long> idsAveria = new ArrayList<Long>();
		
		do 
		{
			Long id = Console.readLong("ID de averia");
			idsAveria.add(id);
		} 
		while (masAverias());

		CashService cs = ServicesFactory.getCashService();
		Map<String, Object> mapa = cs.createInvoiceFor(idsAveria);
		
		Printer.printInvoice(mapa);

	}

	private boolean masAverias() {
		return Console.readString("¿Añadir más averias? (s/n) ").equalsIgnoreCase("s");
	}

}
