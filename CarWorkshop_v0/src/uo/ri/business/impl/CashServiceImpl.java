package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.CashService;
import uo.ri.business.impl.cash.CreateInvoiceFor;
import uo.ri.common.BusinessException;

public class CashServiceImpl implements CashService{

	public CashServiceImpl(){
		
	}
	
	@Override
	public Map<String, Object> createInvoiceFor(List<Long> lista) throws BusinessException
	{
		CreateInvoiceFor cif = new CreateInvoiceFor(lista);
		return cif.execute();
	}

}
