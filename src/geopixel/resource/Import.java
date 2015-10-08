package geopixel.resource;

import java.util.ArrayList;
import java.util.List;

public class Import{
	public static List<String> getTokens(String line){
		List<String> tokens = new ArrayList<String>();
		int first=0;		
		int last=0;
		for (int j=0; j<line.length();j++){
			if (Character.toString(line.charAt(j)).equals(";")){
				//last=j-1;
				last=j;
				if (last<first){
					tokens.add("");
				}
				else{
					tokens.add(line.substring(first,last));    					
				}				
				first=j+1;				
			}
		}
		return tokens;	
	}
}
