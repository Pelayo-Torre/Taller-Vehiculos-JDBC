package uo.ri.ui.admin;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.ui.admin.action.GenerarBonosAction;

public class MediosPagoMenu extends BaseMenu {
	
	public MediosPagoMenu() {
		menuOptions = new Object[][] { 
			{ "Jefe de Taller > Medios de pago", null },

			{ "Generar bonos", GenerarBonosAction.class }, 
			{ "Metálico", NotYetImplementedAction.class }, 
			{ "Tarjeta de crédito", NotYetImplementedAction.class }, 
		};
	}


}
