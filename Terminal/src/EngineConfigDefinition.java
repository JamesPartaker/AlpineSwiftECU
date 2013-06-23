import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class EngineConfigDefinition {

	private int version;
	private List<EngineConfigDefinitionRow> rows;
	
	EngineConfigDefinition(String filename){
		String line;
		String[] splitLine;
		BufferedReader reader = null;
		int lineNum = 1;
		
		try {
			reader = new BufferedReader(new FileReader(filename));
			
			version = Integer.parseInt(reader.readLine());
			lineNum++;
			
			while((line = reader.readLine()) != null){
				splitLine = line.split(",");
				if(splitLine.length == 4){ //TODO: magic number
					rows.add(new EngineConfigDefinitionRow(
									splitLine[0], 
									splitLine[1], 
									Double.parseDouble(splitLine[2]), 
									splitLine[3]));
				}else{
					//TODO: throw engine config definition read error on line x 
				}
				lineNum++;
			}
		
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (reader != null)reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		
	}
	
}


/*
name - Short string description
type - [int8, int16, int32, int64, uint8, ... , boolean, float(always 32)]  
conversion factor - internal value is multiplied by this factor to get the representative value (only valid for numbers)
description - Long string description

VERSION\n
NAME, TYPE, CONVERSION_FACTOR, DESCRIPTION\n
NAME, TYPE, CONVERSION_FACTOR, DESCRIPTION\n

*/