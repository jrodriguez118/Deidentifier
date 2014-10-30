package org.md.deidentifier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;





import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class Deidentifier {

	public static void main(String[] args) {
		ArffLoader loader = new ArffLoader();
		File file = new File(args[0]);
		Instances data = null;
		try {
			loader.setFile(file);
			data = loader.getDataSet();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		Dictionary dict = new Dictionary();
		dict.createDictionary(data);
		
		
	    try {
	    	BufferedWriter writer = new BufferedWriter(new FileWriter("/data/output.arff"));
			writer.write(data.toString());
			writer.newLine();
		    writer.flush();
		    writer.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	    
		

	}


}
