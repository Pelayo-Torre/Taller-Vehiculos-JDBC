package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.AdminService;
import uo.ri.business.impl.admin.AddMechanic;
import uo.ri.business.impl.admin.DeleteMechanic;
import uo.ri.business.impl.admin.FindAllMechanics;
import uo.ri.business.impl.admin.GenerarBonos;
import uo.ri.business.impl.admin.UpdateMechanic;

public class AdminServiceImpl implements AdminService{

	public AdminServiceImpl(){
		
	}
	
	@Override
	public void newMechanic(String nombre, String apellidos) {
		AddMechanic am = new AddMechanic(nombre, apellidos);
		am.execute();
	}

	@Override
	public void deleteMechanic(Long id) {
		DeleteMechanic dm = new DeleteMechanic(id);
		dm.execute();		
	}

	@Override
	public void updateMechanic(Long id, String nombre, String apellidos) {
		UpdateMechanic um = new UpdateMechanic(id,nombre,apellidos);
		um.execute();
	}

	@Override
	public List<Map<String, Object>> findAllMechanics() {
		FindAllMechanics fam = new FindAllMechanics();
		return fam.execute();
	}

	@Override
	public void generarBonos() {
		GenerarBonos gb = new GenerarBonos();
		gb.execute();
	}

}
