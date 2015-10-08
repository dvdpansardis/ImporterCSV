package geopixel.dao;

import geopixel.database.DataBaseService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Dao {

    public static int insertRow(Connection conn,String table, String id, String geocode, String cityname, String indicator, String year, String value)
    		throws IOException, SQLException {
   	 int count = 0;    	
   	 String sqlQuery = "INSERT INTO "
    			+ table 
    			+ " (identificador, cod_ibge, nome_municipio, nome, ano, valor) "
    			+ " VALUES ( '"
    			+ id
    			+ "' , '"
    			+ geocode
    			+ "' , '"
    			+ cityname.replace('\'', ' ')     			
    			+ "' , '"
    			+ indicator
    			+ "' , "     			
    			+ year
    			+ " , "     			
    			+ value.replace(',', '.')
    			+ ")";   	     			
   	
	count = DataBaseService.buildInsert(sqlQuery, conn);
	
	return count;
   }
}
