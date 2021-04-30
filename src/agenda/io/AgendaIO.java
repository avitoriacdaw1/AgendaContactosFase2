package agenda.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import agenda.modelo.*;

/**
 * @version 1.1
 * @author Jhon Vera, Diana Peralta, Adrian Vitoria Lee una liniea de datos para
 *         agregarlos a la agenda.
 */

public class AgendaIO {

	/**
	 * Guarda la información del metodo obtenerLineasDatos y añade la información a
	 * esta
	 * 
	 * @param agenda de la clase AgendaContactos
	 * @return
	 *  
	 */
	public static int importar(AgendaContactos agenda, String texto) throws Exception {
		int error = 0;
		String[] contactos = obtenerLineasDatos();
		try {
			for(int i = 0; i < contactos.length; i ++) {
			Contacto contacto = parsearLinea(contactos[i]);
			agenda.añadirContacto(contacto);				}
		}	
		catch (NumberFormatException e) {
			System.out.println("Error al leer " + texto);
			error ++;
		}
		catch (NullPointerException e) {
			System.out.println(e.getMessage());
			error ++;
		}
		return error;
		/*BufferedReader entrada = null;
		try
		{
			entrada = new BufferedReader(new FileReader(texto));
			String linea = entrada.readLine();
			while (linea != null)
			{
				Contacto contacto = parsearLinea(linea);
				linea = entrada.readLine();
				agenda.añadirContacto(contacto);
			}
		}
		catch (IOException e)
		{
			System.out.println("Error al leer " + texto);
			error++;
		}
		finally
		{
			if (entrada != null)
			{
				try
				{
					entrada.close();
				}
				catch (NullPointerException e)
				{
					System.out.println(e.getMessage());
					error++;
				}
				
				catch (IOException e)
				{
					System.out.println(e.getMessage());
					error++;
				}
			}
		} 
		//try /*Si se cambia el throw de importar, tambien hay que cambiarlo en el test*/
		//{
			//for (int i = 0; i < obtenerLineasDatos().length; i++) { /*Este for es el importar original*/
				//Contacto contacto = parsearLinea(obtenerLineasDatos()[i]);
				//agenda.añadirContacto(contacto);
				//agenda.añadirContacto(contacto);
			//}
		//}
		//catch (IOException e) {
			//error++;
		//}
		//finally {
			
		//}/*
	}

	public static void exportar(AgendaContactos agenda, String file) {
		FileWriter fw = null;

		try {
			fw = new FileWriter(file);
		}

		catch (IOException io) {
			System.out.println("Error al abrir el fichero");
		}
		// Escribimos

		try {
			String mensaje = "";
			for(Relacion key : agenda.personalesPorRelacion().keySet()) {
				mensaje += key.toString() + "\n\t";
				mensaje += agenda.personalesPorRelacion().get(key).toString() + "\n";
			}
			fw.write(mensaje);
			System.out.println("Fichero guardado");
		}
		
		catch (IOException io) {
			System.out.println("Error al escribir");
		}
		// cerramos el fichero

		finally {
			try {
				fw.close();
			} catch (IOException io) {
				System.out.println("Error al cerrar el archivo");
			}
		}
	}

	/**
	 * Guarda cada dato de cada contacto dentro de un Array
	 * 
	 * @param cada linea guardada en el metodo anterior
	 * @return un array de un nuevo contacto con su respectiva información
	 */
	private static Contacto parsearLinea(String linea) throws Exception{
		String[] datosLinea = linea.split(",");

		String nombre = datosLinea[1].trim();
		String apellidos = datosLinea[2].trim();
		String telefono = datosLinea[3].trim();
		String email = datosLinea[4].trim();
		Relacion relacion = null;
		if (Integer.valueOf(datosLinea[0].trim()) == 1) {
			String nombreEmpresa = datosLinea[5].trim();
			return new Profesional(nombre, apellidos, telefono, email, nombreEmpresa);
		} 
		else {
			String fechaNac = datosLinea[5].trim();
			String[] fechas = fechaNac.split("/");
			try
			  {
				if (fechas[0].length() <= 2) {
					if(Integer.parseInt(fechas[0]) < 1 || Integer.parseInt(fechas[0]) > 31) {
						throw new IllegalStateException("Dia del mes invalido");
					}
					else if (fechas[0].length() < 2) {
						throw new IllegalStateException("Formato de dia del mes invalido");
					}
				}
				if (fechas[1].length() <= 2) {
					if(Integer.parseInt(fechas[1]) < 1 || Integer.parseInt(fechas[1]) > 12 || fechas[1].length() < 2) {
						throw new IllegalStateException("Mes invalido");
					}
					else if (fechas[1].length() < 2) {
						throw new IllegalStateException("Formato del mes invalido");
					}
				}
				if(Integer.parseInt(fechas[2]) < 1900) {
					throw new IllegalStateException("Año invalido");
				}
			  } 
			catch(NullPointerException e) 
			  {
			    System.out.println("Formato de fecha invalido");
			  }
			
			try{
				relacion = Relacion.valueOf(datosLinea[6].trim().toUpperCase());
			  } 
			catch(IllegalArgumentException e) 
			  {
				boolean rel = false;
				for(Relacion d : Relacion.values()) {
					if(String.valueOf(d).equalsIgnoreCase(datosLinea[6].trim()) ) {
						rel = true;
					}
				}
				if(rel = false) {
					throw new IllegalStateException("Relacio invalida");
				}
			  }
			
			return new Personal(nombre, apellidos, telefono, email, fechaNac, relacion);
		}
	}
	/**
	 * 
	 * @return un array de String con todas las líneas de información de todos los
	 *         contactos. 1 significa contacto profesional, 2 significa contacto
	 *         personal
	 */
	private static String[] obtenerLineasDatos() { /*Esto hay que borrar*/
		return new String[] { "1, Isabel, Acosta Mendioroz,  678895433 ,  iacostamen@gmail.com ,  walden estrella ",
				"2,  pedro , urruti tello , 616789654 ,  urrutitello@gmail.com , 09/03/2007, amigos",
				"1, Angel , Esteban Grande , 674544123 ,  aestebang@gmail.com ,  magma publicidad ",
				"2, elena , bueno ganuza , 6786547699 ,  ebuenogan@gmail.com , 17/03/2000, amigos",
				"2, amaia , romero sein , 642222343 ,  aromerosein@gmail.com , 09/03/2012, pareja",
				"2, Ignacio ,  Anto roth ,  688912799 , iantoroth@gmail.com ,  11/11/1969 , padre",
				"1,  Isabel ,  Acosta Marin , 678895433 ,  iacostamar@gmail.com ,  publicidad holdings ",
				"1 ,    roberto , casas maura , 666777888 ,  rocasasma@gmail.com ,  strato banca ",
				"1,juan maria, garcía oliva, 699898111, jmgarcioliva@gmail.com, conway motor ",
				"2, pedro , urruti tello , 616789654 ,  urrutitello@gmail.com , 17/03/2000, amigos",
				"1,marta, sanz iris, 622999876, msanzi@gmail.com, jump literatura ",
				"1,javier, porto luque, 691256777 , japorlu@gmail.com, gas natural ",
				"1,pablo, ponce larraoz, 689123456, pabloponce@gmail.com, la caixa",
				"1, javier, marin lancho, 666666666, jruizlanchoe@gmail.com, bbva",
				"1,juan maria, garcía oliva, 699898111, jmgarcioliva@gmail.com, conway motor ",
				"2, Berta ,  andia solano ,  621123345 , bandiasol@gmail.com ,  12/12/1999 ,  HIJA",
				"2, Ignacio ,  Anto roth ,  688912799 , iantoroth@gmail.com ,  11/11/1969 , padre",
				"  1,  roberto , casas maura , 666777888 ,  rocasasma@gmail.com ,  strato banca ",
				" 2, daniel , martin martin , 678901234 ,  damrtinmartin@gmail.com , 15/07/1980, amigos",
				"  2, pablo , martin abradelo , 667788899 ,  martinabra@gmail.com , 31/01/2010, amigos",
				"  2, susana , santaolalla bilbao , 676767676 ,  ssantaolalla@gmail.com , 17/03/1998, amigos",
				"  2, adur ,  martin merino ,  611112113 , adurmartinme@gmail.com ,  14/02/2003 , primo" };

	}

}
