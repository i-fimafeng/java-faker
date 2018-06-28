package com.github.javafaker.service;

import java.io.InputStream;
import java.util.List;

public interface ResourceProvider {

	/**
	 * Return a list of open Yaml stream to consume by the Yaml decoder that match the local name.
	 * @param pattern
	 * @return a list of InputStream of resources that match the locale name
	 */
	List<InputStream>  getResources(String filename);
	
}
