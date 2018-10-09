package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface MecanicosGateway {
	
	/**
	 * Establece la conexión con la base de datos
	 * @param c la conexión a la Base de datos
	 */
	public void setConnection(Connection c);
	
	/**
	 * Devuelve un mapa con la información de un mecánico
	 * @param id del mecánico del que se desea ver su información
	 * @return un mapa con la información del mecánico
	 */
	public Map<String, Object> findById(Long id);
	
	/**
	 * Lista de todos los mecánicos del sistema
	 * @return una lista de mapas con todos los mecánicos del sistema
	 */
	public List<Map<String, Object>> findAll();
	
	/**
	 * Actualiza la información de un mecánico
	 * @param id del mecánico del que se desea actualizar la información
	 * @param nombre del mecánico que se va a actualizar
	 * @param apellidos del mecánico que se va a actualizar
	 */
	public void update(Long id, String nombre, String apellidos);
	
	/**
	 * Inserta un mecánico en el sistema.
	 * @param nombre del mecánico a insertar
	 * @param apellidos del mecánico a insertar
	 */
	public void save(String nombre, String apellidos);
	
	/**
	 * Elmina un mecánico del sistema
	 * @param id del mecánico a eliminar
	 */
	public void delete(Long id);
}
