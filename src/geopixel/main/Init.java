package geopixel.main;

import geopixel.database.*;
import geopixel.dao.*;
import geopixel.resource.Import;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Init {

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		DataBase dataBase=DataBaseService.getPostgresParameters();
    	Connection conn = DataBaseService.connect(dataBase);
    	
		Charset charset = Charset.forName("UTF-8");
		Path file = Paths.get(args[0], args[1]);    		
		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
		    String line = null;
		    int count=0;
		    List <String> columns = new ArrayList<String>();
		    List <String> years = new ArrayList<String>();
		    List <String> indicators = new ArrayList<String>();
		    List <String> dataValues = new ArrayList<String>();
		    
		    String table="";
		    String id = "";
		    String geocode="";
		    String cityName="";
		    String indicator="";
		    String year="";
		    String value="";
		    while ((line = reader.readLine()) != null) {
		    	count++;
		    	switch (count) {
			    	case 1:{
			    		columns = Import.getTokens(line);	
			    		break;
			    	}
			    	case 2:{
			    		years = Import.getTokens(line);
			    		break;
			    	}
			    	case 3:{
			    		indicators = Import.getTokens(line);	
			    		break;
			    	}
			    	case 4:{
			    		//documentation line
			    		break;
			    	}
			    	default:{
			    		dataValues=Import.getTokens(line);
			    		for (int i=3;i<columns.size();i++){
			    			id=dataValues.get(0); 
			    			geocode=dataValues.get(1); 
				    		cityName=dataValues.get(2);
				    		indicator=indicators.get(i);
				    		if (indicator.length()>0){
    				    		year=years.get(i);
    				    		value=dataValues.get(i);
    				    		System.out.println(geocode+";"+cityName+";"+indicator+";"+year+";"+value);
    				    		table = "tab_valores_teste";
    				    		Dao.insertRow (conn, table, id, geocode, cityName, indicator, year, value);    		
				    		}
			    		}
			    		break;	
			    	}  	
		    	}
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}	 	
    	
	}

}
