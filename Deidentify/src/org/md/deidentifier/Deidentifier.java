package org.md.deidentifier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;








import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class Deidentifier {

	public static void main(String[] args) {
		BufferedReader reader;
		Instances data = null;
		try {
			reader = new BufferedReader(new FileReader(args[0]));
			data = new Instances(reader);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Dictionary dict = new Dictionary();
		dict.createDictionary(data);
	
		
		
	    try {
	    	BufferedWriter writer = new BufferedWriter(new FileWriter("data/output.arff"));
			writer.write(data.toString());
			writer.newLine();
		    writer.flush();
		    writer.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	    
		

	}


}
