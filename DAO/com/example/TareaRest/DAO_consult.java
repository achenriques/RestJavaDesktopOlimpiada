package com.example.TareaRest;

import java.sql.*;
import java.util.ArrayList;

public class DAO_consult 
{
	private static final String theConsult = ("SELECT C.ID_PAIS, C.NOMBRE_CIUDAD, C.ID_CIUDAD, P.NOMBRE_PAIS, " +
					" IFNULL(C.VALOR_CIUDAD, P.VALOR_PAIS) AS Valor, (select count(S.AÑO) " +
					" FROM sede_jjoo S WHERE S.SEDE = C.ID_CIUDAD) AS N_Veces_Sede, SUB.DESCRIPCION_TIPO AS Tipo " +
					" FROM pais AS P, ciudad AS C " +
					"LEFT JOIN (SELECT SJ.SEDE as SEDE, TJ.DESCRIPCION_TIPO as DESCRIPCION_TIPO " + 
					"FROM tipo_jjoo TJ, sede_jjoo SJ " +
					"where TJ.ID_TIPO_JJOO = SJ.ID_TIPO_JJOO) as SUB " + 
					"On C.ID_CIUDAD = SUB.SEDE " +
					"WHERE C.ID_PAIS = P.ID_PAIS;");
	
	private static final String selectSSJJ = ("SELECT SJ.SEDE, C.NOMBRE_CIUDAD AS Ciudad, SJ.AÑO, SJ.ID_TIPO_JJOO AS Tipo, " +
					"TJ.DESCRIPCION_TIPO AS Epoca FROM sede_jjoo SJ, ciudad C, tipo_jjoo TJ WHERE SJ.SEDE = C.ID_CIUDAD " +
					"AND SJ.ID_TIPO_JJOO = TJ.ID_TIPO_JJOO ORDER BY AÑO ASC");
	
	private static final String selectCiudad = ("select ID_CIUDAD, NOMBRE_CIUDAD from ciudad");
	
	public static ArrayList<Olimpiada> toConsult(Connection conn)
	{
		Statement statement;
		ResultSet result;
		ArrayList<Olimpiada> toret = new ArrayList<>();
		int count = 0;
		
		try {
			statement = conn.createStatement ();
			statement.executeQuery (theConsult);
			result = statement.getResultSet ();
		
		
		while (result.next ())
		{
	       int id_pais = result.getInt ("ID_PAIS");
	       String nombre_ciudad = result.getString ("NOMBRE_CIUDAD");
	       int id_ciudad = result.getInt ("ID_CIUDAD");
	       String nombre_pais = result.getString ("NOMBRE_PAIS");
		   int valor = result.getInt ("Valor");
		   int n_veces_sede = result.getInt ("N_VECES_SEDE");
		   String tipo = result.getString ("Tipo");
		   
	       ++count;
	       
	       toret.add(new Olimpiada(id_pais, nombre_ciudad, id_ciudad, nombre_pais, valor, n_veces_sede, tipo));
		}
		
			result.close ();
			statement.close ();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if(toret.size() == 0)
				System.out.println("Bad request");
			System.out.println (count + " rows were retrieved");
		}
		return toret;
	}
	
	public static ArrayList<SedeJJOO> getSJJOO(Connection conn)
	{
		Statement statement;
		ResultSet result;
		ArrayList<SedeJJOO> toret = new ArrayList<>();
		int count = 0;
		
		try {
			statement = conn.createStatement ();
			statement.executeQuery (selectSSJJ);
			result = statement.getResultSet ();
		
		
		while (result.next ())
		{
	       int sede = result.getInt ("año");
	       String ciudad = result.getString ("Ciudad");
		   int ano = result.getInt ("AÑO");
		   int id = result.getInt("Tipo");
	       String epoca = result.getString ("Epoca");
		   
	       ++count;
	       
	       toret.add(new SedeJJOO(ano, sede, ciudad, id, epoca));
		}
			result.close ();
			statement.close ();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if(toret.size() == 0)
				System.out.println("Bad request");
			System.out.println (count + " rows were retrieved");
		}
		return toret;
	}
	
	public static boolean insertSJJOO(Connection conn, int ano, int sede, int id_tipo)
	{
		Statement statement;
		ResultSet result;
		int count = 0;
		boolean toret = false;
		
		try
		{
			statement = conn.createStatement ();
			statement.executeQuery ("select count(AÑO) as c from sede_jjoo WHERE AÑO = " + ano +"");
			result = statement.getResultSet ();
			result.next ();
			count = result.getInt ("c");
			if(count == 0)
			{
				statement.executeUpdate (
						"INSERT INTO sede_jjoo (AÑO, SEDE, ID_TIPO_JJOO) VALUES ("+ ano +", "+ sede +", "+ id_tipo +")");
				toret = true;
			}
		    statement.close ();
		    
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			toret = false;
		}
		finally {
			if(count != 0)
			{
				toret = false;
				System.out.println("Bad request");
			} else {
				System.out.println (count + " rows were inserted");
			}
		}
		return toret;
	}
	
	public static boolean updateSJJOO(Connection conn, int anoViejo, int anoNuevo, int tipo)
	{
		Statement statement;
		ResultSet result;
		boolean toret = false;
		int count = 0;
		
		try
		{
			statement = conn.createStatement ();
			statement.executeQuery ("select count(AÑO) as c from sede_jjoo WHERE AÑO = " + anoNuevo +"");
			result = statement.getResultSet ();
			result.next ();
			count = result.getInt ("c");			
			if(count == 0)
			{
				statement.executeUpdate (
						"UPDATE sede_jjoo SET AÑO = " + anoNuevo +", ID_TIPO_JJOO = " + tipo + " WHERE AÑO = " + anoViejo + "");
				
				toret = true;
			}
		    statement.close ();
		    		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(count != 0)
			{
				toret = false;
				System.out.println("Bad request");
			} else {
				System.out.println ("1 row was updated");
			}
		}
			return toret;
	}
	
	public static boolean deleteSJJOO(Connection conn, int ano)
	{
		Statement statement;
		boolean toret = false;
		
		try
		{
			statement = conn.createStatement ();
			statement.executeUpdate (
					"delete FROM sede_jjoo WHERE AÑO = " + ano + "");
		    statement.close ();
		    
		    toret = true;
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(toret)
			{
				System.out.println("Bad request");
			} else {
				System.out.println (" 1 row was deleted");
			}
			
		}
		return toret;
	}
	
	public static ArrayList<ArrayList<String>> selectPais(Connection conn)
	{
		Statement statement;
		ResultSet result;
		ArrayList<ArrayList<String>> toret = new ArrayList<>();
		int count = 0;
		
		try {
			statement = conn.createStatement ();
			statement.executeQuery (selectCiudad);
			result = statement.getResultSet ();
		
			while (result.next ())
			{
			   ArrayList<String> x = new ArrayList<>();
				
		       int sede = result.getInt ("ID_CIUDAD");
		       String ciudad = result.getString ("NOMBRE_CIUDAD");
			   		
		       x.add(String.valueOf(sede));
		       x.add(ciudad);
		       
		       toret.add(x);
		       ++count;
			}
				result.close ();
				statement.close ();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if(count == 0)
				System.out.println("Bad request");
			System.out.println (count + " rows were retrieved");
		}
		return toret;
	}
	
	public static ArrayList<SedeJJOO> selectPaisSJJOO(Connection conn)
	{
		return getSJJOO(conn); //Devuelve arrayList con objetos SedeJJOO
	}
}


