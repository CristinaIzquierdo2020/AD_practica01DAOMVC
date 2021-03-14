package com.AD_Practica1.Practica1_AD;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import dao.ClienteDaoImplementacion;
import dao.ProductoDaoImplementacion;
import exceptions.ClientNotFound;
import exceptions.DuplicateException;
import model.Cliente;
import model.Producto;


public class App 
{
	private static Scanner sc = new Scanner(System.in);
	private static ClienteDaoImplementacion cDao = new ClienteDaoImplementacion();
	private static ProductoDaoImplementacion pDao = new ProductoDaoImplementacion();
    
	public static void main( String[] args )
    {
    	int opcion = 0;
    	do {
    		try {
    			opcion = getOpcionDeMenu();
    			ejecutarOpcion(opcion);
			} catch (Exception e) {
				opcion = 0;
				System.out.println("Por favor, introduce un número. Saliendo...");
			}
    	} while (opcion != 0);
        
    	sc.close();
    	cDao.cerrarConexion();
    	pDao.cerrarConexion();
    
    }

	private static void ejecutarOpcion(int opcion) {
		Cliente c = null;
		List<Cliente> clientes = new ArrayList<>();
		switch (opcion) {
		case 1:
			c = pedirDatosCliente();
			 try {
				if (cDao.anadeCliente(c)) {
					 System.out.println("Nuevo cliente: '" + c.getNombreCliente() + "' en la base de datos");
				 }
			} catch (DuplicateException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			System.out.print("Codigo de cliente: "); String codigoCliente = sc.nextLine();
			try {
				c = cDao.MostrarCliente(Integer.parseInt(codigoCliente));
				System.out.println("Datos del cliente (" + codigoCliente + "):");
				System.out.println(c);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ClientNotFound e) {
				System.out.println(e.getMessage());
			}

			break;
		case 3:
			clientes = cDao.MostrarTodosClientes();
			for (Cliente cliente : clientes) {
				System.out.println(cliente);
			}
			break;
		case 4:
			System.out.print("Buscar por texto: "); String cadenaTexto = sc.nextLine();
			clientes = cDao.buscarPorTexto(cadenaTexto);
			
			if (clientes.size() > 0) {
				
				System.out.println("El texto: " + cadenaTexto + " se ha encontrado en:");
				for (Cliente cliente : clientes) {
					System.out.println(cliente);
				}
			} else {
				System.out.println("No se ha encontrado el texto: " + cadenaTexto );
			}

			break;
		case 5:
			System.out.print("Introduce codigo producto a modificar: "); String codigoProducto = sc.nextLine();
			Producto p = pDao.MostrarProducto(Integer.parseInt(codigoProducto));
			if (p != null) {
				System.out.println("Los datos del producto (" + codigoProducto + ") son los siguientes:");
				System.out.println(p);
			} else {
				System.out.println("El producto con id (" + codigoProducto + ") no existe");
			}
			Producto productoAModificar = pedirDatosProducto();
			pDao.editaProducto(p, productoAModificar);
			
			break;
		default:
			break;
		}
		
	}

	private static Cliente pedirDatosCliente() {
		System.out.print("(int)Codigo de cliente: "); String codigoCliente = sc.nextLine();
		System.out.print("(String)Nombre del cliente: "); String nombreCliente = sc.nextLine();
		System.out.print("(String)Teléfono: "); String telefonoCliente = sc.nextLine();
		System.out.print("(String)Fax: "); String faxCliente = sc.nextLine();
		System.out.print("(String)Ciudad: "); String ciudadCliente = sc.nextLine();
		System.out.print("(String)Direccion 1: "); String direccion1Cliente = sc.nextLine();
	
		// Hacer comprobaciones de que los datos recogidos están bien
		return new Cliente(Integer.parseInt(codigoCliente), nombreCliente, telefonoCliente, faxCliente, ciudadCliente, direccion1Cliente);
	}
	
	
	private static Producto pedirDatosProducto() throws NumberFormatException{
 		
		System.out.print("(String)Codigo de producto: "); String codigoProducto = sc.nextLine();
		System.out.print("(String)Nombre del producto: "); String nombreProducto = sc.nextLine();
		System.out.print("(String)Gama: "); String gama = sc.nextLine();
		System.out.print("(String)Dimensiones: "); String dimensiones = sc.nextLine();
		System.out.print("(String)Proveedor: "); String proveedor = sc.nextLine();
		System.out.print("(String)Descripcion: "); String descripcion = sc.nextLine();
		System.out.print("(int)Cantidad Stock: "); String cantidadStock = sc.nextLine();
		System.out.print("(double)Precio venta: "); String precioVenta = sc.nextLine();
		System.out.print("(double)Precio proveedor: "); String precioProveedor = sc.nextLine();
	
		// Hacer comprobaciones de que los datos recogidos están bien
		return new Producto(codigoProducto, nombreProducto, gama, dimensiones, proveedor, descripcion, NumberUtils.toInt(cantidadStock, 0), NumberUtils.toDouble(precioProveedor, 0.0), NumberUtils.toDouble(precioVenta, 0.0));
	}

	private static int getOpcionDeMenu() {
		System.out.println("");
		System.out.println("******************************");
		System.out.println("------------- MENÚ  -------------");
		System.out.println("******************************");
		System.out.println( "1.- Añadir un cliente" );
		System.out.println( "2.- Muestra un cliente por CODIGO" );
		System.out.println( "3.- Muestra TODOS los clientes" );
		System.out.println( "4.- Busca cliente por TEXTO" );
		System.out.println( "5.- Edita producto" );
		System.out.print( "Selecciona una opcion (0 para salir): " );
		
		String s = sc.nextLine();
		return Integer.parseInt(s);
	}
}
