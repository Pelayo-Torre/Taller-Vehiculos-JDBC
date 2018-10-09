package uo.ri.ui.foreman;

import alb.util.menu.BaseMenu;
import uo.ri.ui.foreman.action.ListAllClientsAction;
import uo.ri.ui.foreman.action.ListClientsByIdAction;
import uo.ri.ui.foreman.action.ListClientsRecommendedAction;

public class ListadoClientesMenu extends BaseMenu{
	
	public ListadoClientesMenu() {
		menuOptions = new Object[][] { 
			{ "Jefe de Taller > Gestión de Clientes > Listado de Clientes", null },

			{ "Listar todos los clientes", ListAllClientsAction.class }, 
			{ "Ver detalle de un cliente", ListClientsByIdAction.class }, 
			{ "Listar clientes recomendados por otro cliente", ListClientsRecommendedAction.class }, 
		};
	}

}
