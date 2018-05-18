package com.stackroute.datamunger.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonWriter {
	/*
	 * this method will write the resultSet object into a JSON file. On successful
	 * writing, the method will return true, else will return false
	 */
	public boolean writeToJson(Map resultSet) {

		/*
		 * Gson is a third party library to convert Java object to JSON. We will use
		 * Gson to convert resultSet object to JSON
		 */
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String result = gson.toJson(resultSet);
		Boolean fileWritten=false;

		/*
		 * write JSON string to data/result.json file. As we are performing File IO,
		 * consider handling exception
		 */
		 FileWriter fileWriter;
			try {
				fileWriter = new FileWriter("data/result.json");
				fileWriter.write(result);    
				fileWriter.close();
		         fileWritten=true;
			} catch (IOException e) {
				
				e.printStackTrace();
				fileWritten=false;
			} 
		/* return true if file writing is successful */

		/* return false if file writing is failed */

		/* close BufferedWriter object */

		return fileWritten;
	}

}