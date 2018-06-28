package com.github.javafaker.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DefaultResourceProvider implements ResourceProvider {

	@Override
	public List<InputStream> getResources(String filename) {
		List<InputStream> list = new ArrayList<InputStream>();
		String filenameWithExtension = "/" + filename + ".yml";
		InputStream streamOnClass = getClass().getResourceAsStream(filenameWithExtension);
		if (streamOnClass != null) {
			list.add(streamOnClass);
			return list;
		} 
		streamOnClass = getClass().getClassLoader().getResourceAsStream(filenameWithExtension);
		if (streamOnClass != null) {
			list.add(streamOnClass);
		} 		
		return list;
	}

}
