package uo.ri.business.impl.cash;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.AveriasGateway;
import uo.ri.persistence.FacturasGateway;
import uo.ri.persistence.IntervencionesGateway;
import uo.ri.persistence.RepuestosGateway;
import alb.util.date.DateUtil;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;

public class CreateInvoiceFor {

	private List<Long>idsAveria;
	private Connection connection;
	private FacturasGateway facturaGateway;
	private AveriasGateway averiaGateway;
	private RepuestosGateway repuestoGateway;
	private IntervencionesGateway intervencionGateway;
		
	public CreateInvoiceFor(List<Long> lista)
	{
		this.idsAveria = lista;
	}
	
	public Map<String, Object> execute() throws BusinessException
	{
		
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		try 
		{
			
			connection = Jdbc.getConnection();
			connection.setAutoCommit(false);
			
			facturaGateway = PersistenceFactory.getFacturasGateway();
			facturaGateway.setConnection(connection);
			
			averiaGateway = PersistenceFactory.getAveriasGateway();
			averiaGateway.setConnection(connection);
			
			intervencionGateway = PersistenceFactory.getIntervencionesGatewayImpl();
			intervencionGateway.setConnection(connection);
			
			repuestoGateway = PersistenceFactory.getRepuestosGateway();
			repuestoGateway.setConnection(connection);
			
			verificarAveriasTerminadas(idsAveria);

			long numeroFactura = generarNuevoNumeroFactura();
			Date fechaFactura = DateUtil.today();
			double totalFactura = calcularImportesAverias(idsAveria);
			double iva = porcentajeIva(totalFactura, fechaFactura);
			double importe = totalFactura * (1 + iva/100);
			importe = Round.twoCents(importe);
						
			long idFactura = crearFactura(numeroFactura, fechaFactura, iva, importe);
			vincularAveriasConFactura(idFactura, idsAveria);
			cambiarEstadoAverias(idsAveria, "FACTURADA");
			
			mapa.put("numeroFactura", numeroFactura);
			mapa.put("fechaFactura", fechaFactura);
			mapa.put("totalFactura", totalFactura);
			mapa.put("iva", iva);
			mapa.put("totalConIva", importe);
			
			connection.commit();
		}
		catch (SQLException e) 
		{
			try 
			{ 
				connection.rollback(); 
			} 
			catch (SQLException ex) {};
			throw new RuntimeException(e);
		}
		catch (BusinessException e) 
		{
			try
			{ 
				connection.rollback(); 
				} 
			catch (SQLException ex) {};
			throw e;
		}
		finally {
			Jdbc.close(connection);
		}
		
		return mapa;
	}
	
	private void verificarAveriasTerminadas(List<Long> idsAveria) throws SQLException, BusinessException {
		
		for (Long idAveria : idsAveria) 
		{
			averiaGateway.verificarEstadoAveria(idAveria);
		}

	}

	private void cambiarEstadoAverias(List<Long> idsAveria, String status) throws SQLException {
		
		for (Long idAveria : idsAveria)
		{
			averiaGateway.cambiarEstadoAveria(idAveria, status);
		}
	}

	private void vincularAveriasConFactura(long idFactura, List<Long> idsAveria) throws SQLException {
	
		for (Long idAveria : idsAveria) 
		{
			averiaGateway.vincularAveriaConFactura(idAveria, idFactura);
		}
		
	}

	private long crearFactura(long numeroFactura, Date fechaFactura,
			double iva, double totalConIva) throws SQLException 
	{
		
		facturaGateway.crearFactura(numeroFactura, fechaFactura, iva, totalConIva);
		return getGeneratedKey(numeroFactura);
		
	}

	private long getGeneratedKey(long numeroFactura) throws SQLException
	{
		return facturaGateway.getGeneratedKey(numeroFactura);
	}

	private Long generarNuevoNumeroFactura() throws SQLException 
	{
		return facturaGateway.generarNuevoNumeroFactura();
	}

	private double porcentajeIva(double totalFactura, Date fechaFactura) 
	{
		return DateUtil.fromString("1/7/2012").before(fechaFactura) ? 21.0 : 18.0;
	}

	protected double calcularImportesAverias(List<Long> idsAveria)
			throws BusinessException, SQLException {
		
		double totalFactura = 0.0;
		for(Long idAveria : idsAveria) 
		{
			double importeManoObra = consultaImporteManoObra(idAveria);
			double importeRepuestos = consultaImporteRepuestos(idAveria);
			double totalAveria = importeManoObra + importeRepuestos;
			
			actualizarImporteAveria(idAveria, totalAveria);
			
			totalFactura += totalAveria; 
		}
		return totalFactura;
	}

	private void actualizarImporteAveria(Long idAveria, double totalAveria) throws SQLException {
		
		averiaGateway.updateImporte(idAveria, totalAveria);
	}

	private double consultaImporteRepuestos(Long idAveria) throws SQLException
	{
		return repuestoGateway.getImpote(idAveria);
	}

	private double consultaImporteManoObra(Long idAveria) throws BusinessException, SQLException {
		
		return intervencionGateway.getImporteManoObra(idAveria);
	}
	
	
}
