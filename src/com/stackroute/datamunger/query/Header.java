package com.stackroute.datamunger.query;

import java.util.HashMap;

//header class containing a Collection containing the headers
public class Header extends HashMap<String, Integer> {

	public String header;

	public void setheader(String input_header) {
		this.header = input_header;
	}

	public String[] getheaders() {
		String[] finalheader = header.split(",");
		return finalheader;
	}
}