package uo.ri.business;

import java.util.List;
import java.util.Map;

public interface AdminService {
	
	/**
	 * Añade un mecánico al sistema
	 * @param nombre del mecánico a añadir
	 * @param apellidos del mecánico a añadir
	 */
	public void newMechanic(String nombre, String apellidos);
	
	/**
	 * Elimina un mecánico del sistema.
	 * @param id del mecánico a eliminar
	 */
	public void deleteMechanic(Long id);
	
	/**
	 * Actualiza los datos de un mecánico
	 * @param id del mecánico que se va a actualizar
	 * @param nombre
	 * @param apellidos
	 */
	public void updateMechanic(Long id, String nombre, String apellidos);
	
	/**
	 * Devuelve una lista de todos los mecánicos del sistema
	 * @return una lista de mapas con los mecánicos del sistema
	 */
	public List<Map<String, Object>> findAllMechanics();
	
	/**
	 * Generación de bonos.
	 */
	public void generarBonos();
}
