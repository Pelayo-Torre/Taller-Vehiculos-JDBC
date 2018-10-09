package uo.ri.conf;

import uo.ri.business.AdminService;
import uo.ri.business.CashService;
import uo.ri.business.ForemanService;
import uo.ri.business.impl.AdminServiceImpl;
import uo.ri.business.impl.CashServiceImpl;
import uo.ri.business.impl.ForemanServiceImpl;

public class ServicesFactory {
	
	public ServicesFactory(){
		
	}
	
	public static AdminService getAdminService()
	{
		AdminService as = new AdminServiceImpl();
		return as;
	}
	
	public static CashService getCashService()
	{
		CashService cs = new CashServiceImpl();
		return cs;
	}
	
	public static ForemanService getForemanService() 
	{
		ForemanService fs = new ForemanServiceImpl();
		return fs;
	}

}
