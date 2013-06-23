
//TODO: change name
enum theTypes {INT8, INT16, INT32, INT64, UINT8, UINT16, UINT32, UINT64, BOOLEAN, FLOAT }

public class EngineConfigDefinitionRow {

	private String name;
	private theTypes type;
	private double conversionFactor;
	private String description;
	
	EngineConfigDefinitionRow(String name, String type, double conversionFactor, String description){
		this.name = name;
		//TODO: convert type
		this.conversionFactor = conversionFactor;
		this.description = description;
	}
	
}
