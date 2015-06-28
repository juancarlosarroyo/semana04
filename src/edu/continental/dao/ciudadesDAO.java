package edu.continental.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONArray;

import edu.continental.util.ToJSON;
import edu.continental.util.conexion;

public class ciudadesDAO {

	public JSONArray listaCiudades() {
		
		//obtener la conexion a la DB
		conexion cn = new conexion();
		Connection con = cn.getConnection();
		
		//Sentencia sql
		String sql = "Select id, nombre, altitud, estado from ciudades where estado = 'A'";
		
		Statement st = null;
		ResultSet rs = null;
		
		//Para convertir a JSOn
		ToJSON convertidor = new ToJSON();
		JSONArray arreglo = new JSONArray();
		
		try {
			//creo la sentencia
			st = con.createStatement();
			//Se ejecuta la sentencia
			rs = st.executeQuery(sql);
			
			arreglo = convertidor.toJSONArray(rs);
			st.close();
			
		} catch (Exception ex) {
			System.out.println("error: " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		return arreglo;
	}
	
}
