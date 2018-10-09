package uo.ri.ui.util;

import java.util.List;
import java.util.Map;

import alb.util.console.Console;

public class Printer {
	
	/**
	 * Imprime datos de factura alamcenados en una lista de mapas
	 * @param mapa con la información de una factura
	 */
	public static void printInvoice(Map<String, Object> mapa)
	{
		Console.printf("Factura nº: %d\n", mapa.get("numeroFactura"));
		Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n", mapa.get("fechaFactura"));
		Console.printf("\tTotal: %.2f €\n", mapa.get("totalFactura"));
		Console.printf("\tIva: %.1f %% \n", mapa.get("iva"));
		Console.printf("\tTotal con IVA: %.2f €\n", mapa.get("totalConIva"));
	}
	
	/**
	 * Imprime datos de los clientes almacenados en una lista de mapas
	 * @param lista con todos los clientes
	 */
	public static void printClients(List<Map<String, Object>> lista) 
	{
		for(Map<String,Object> mapa: lista)
		{
			Console.printf("ID: %d\n\tNOMBRE: %s\n\tAPELLIDOS: %s\n\tTELÉFONO: %s"
					+ "\n\tCORREO: %s\n\tCIUDAD: %s\n\tCALLE: %s\n\tCÓDIGO POSTAL: %s\n\n\n",  
			mapa.get("id")
			,  mapa.get("nombre") 
			,  mapa.get("apellidos")
			,  mapa.get("telefono")
			,  mapa.get("correo")
			,  mapa.get("city")
			,  mapa.get("street")
			,  mapa.get("zipcode"));
		}
	}
	
	/**
	 * Imprime los datos de un cliente especificado
	 * almacenado en un mapa.
	 * @param mapa con la información del cliente
	 */
	public static void printClientsById(Map<String, Object> mapa)
	{
		Console.printf("ID: %d\n\tNOMBRE: %s\n\tAPELLIDOS: %s\n\tTELÉFONO: %s"
				+ "\n\tCORREO: %s\n\tCIUDAD: %s\n\tCALLE: %s\n\tCÓDIGO POSTAL: %s\n\n\n",    
			mapa.get("id")
			,  mapa.get("nombre") 
			,  mapa.get("apellidos")
			,  mapa.get("telefono")
			,  mapa.get("correo")
			,  mapa.get("city")
			,  mapa.get("street")
			,  mapa.get("zipcode"));
	}
	
	/**
	 * Imprime la informción de los mecánicos
	 * @param lista de mapas con la información de los mecánicos
	 */
	public static void printMechanics(List<Map<String, Object>> lista)
	{
		for(Map<String,Object> mapa: lista)
		{
			Console.printf("\t%d %s %s\n",  
			mapa.get("id")
			,  mapa.get("nombre") 
			,  mapa.get("apellidos"));
		}
	}

}
