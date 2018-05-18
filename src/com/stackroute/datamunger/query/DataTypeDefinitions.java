package com.stackroute.datamunger.query;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * implementation of DataTypeDefinitions class. This class contains a method getDataTypes() 
 * which will contain the logic for getting the datatype for a given field value. This
 * method will be called from QueryProcessors.   
 * In this assignment, we are going to use Regular Expression to find the 
 * appropriate data type of a field. 
 * Integers: should contain only digits without decimal point 
 * Double: should contain digits as well as decimal point 
 * Date: Dates can be written in many formats in the CSV file. 
 * However, in this assignment,we will test for the following date formats('dd/mm/yyyy',
 * 'mm/dd/yyyy','dd-mon-yy','dd-mon-yyyy','dd-month-yy','dd-month-yyyy','yyyy-mm-dd')
 */
public class DataTypeDefinitions {

	private String[] headerNames;
	private String[] datatypes;

	public void setDatatypes(String[] datatypes) {
		this.datatypes = datatypes;
	}

	public void setheaderNames(String[] headerNames) {
		this.headerNames = headerNames;
	}

	public String[] getDataTypes() {
		int count = headerNames.length;
		String[] datatypesArray = new String[count];
		for (int index = 0; index < count; index++) {
			try {
				datatypesArray[index] = (String) getDataType(datatypes[index]);
			} catch (ArrayIndexOutOfBoundsException e) {
				datatypesArray[index] = (String) getDataType(" ");
			}
		}
		return datatypesArray;
	}
	// method stub
	public static Object getDataType(String input) {

		try {
			// checking for Integer
			Integer tempdata = Integer.parseInt(input);
			return tempdata.getClass().getName();
		} catch (Exception e) {
			try {
				java.util.Date date1 = null;
				String tempdata = input;

				// checking for date format dd/mm/yyyy

				if (tempdata.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
					date1 = new SimpleDateFormat("dd-mm-yyyy").parse(tempdata);
					return date1.getClass().getName();
				} else {
					// checking for date format mm/dd/yyyy

					if (tempdata.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
						date1 = new SimpleDateFormat("mm-dd-yyyy").parse(tempdata);
						return date1.getClass().getName();
					} else {
						// checking for date format dd-mon-yy

						if (tempdata.matches("([0-9]{2})-[a-zA-Z]{3}-([0-9]{2})")) {
							date1 = new SimpleDateFormat("dd-MMMM-yy").parse(tempdata);
							return date1.getClass().getName();
						} else {
							// checking for date format dd-mon-yyyy

							if (tempdata.matches("([0-9]{2})-[a-zA-Z]{3}-([0-9]{4})")) {
								date1 = new SimpleDateFormat("dd-MMMM-yyyy").parse(tempdata);
								return date1.getClass().getName();
							} else {
								// checking for date format dd-month-yy

								if (tempdata.matches("([0-9]{2})-[a-zA-Z]*-([0-9]{2})")) {
									date1 = new SimpleDateFormat("dd-MMMM-yy").parse(tempdata);
									return date1.getClass().getName();
								} else {
									// checking for date format dd-month-yyyy

									if (tempdata.matches("([0-9]{2})-[a-zA-Z]*-([0-9]{4})")) {
										date1 = new SimpleDateFormat("dd-MMMM-yyyy").parse(tempdata);
										return date1.getClass().getName();
									} else {

										// checking for date format yyyy-mm-dd
										if (tempdata.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
											date1 = new SimpleDateFormat("yyyy-mm-dd").parse(tempdata);

											return date1.getClass().getName();
										} else {
											// checking for floating point
											// numbers
											Double date2 = Double.parseDouble(tempdata);
											return date2.getClass().getName();
										}
									}

								}

							}
						}
					}

				}

			} catch (Exception e1) {
				String tempdata = input;
				if (tempdata.trim().equals("")) {
					Object object = new Object();
					// System.out.println(object.getClass().getName());
					return object.getClass().getName();
				} else {
					return tempdata.getClass().getName();
				}
			}
		}
	}

}