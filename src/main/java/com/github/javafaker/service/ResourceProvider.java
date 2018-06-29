package com.github.javafaker.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public interface ResourceProvider {

	/**
	 * Return a list of open Yaml stream to consume by the Yaml decoder that match the local name.
	 * @param pattern
	 * @return a list of InputStream of resources that match the locale name
	 */
	List<InputStream>  getResources(String filename);
	
    /**
     * This resource provider load resource from the basedir of the classloader.
     * 
     * This can be used by extending this class and create a no-arg constructor
     *
     */
    static class Base implements ResourceProvider {

        private final String basedir;
        
        protected Base(String basedir) {
            this.basedir = basedir;
        }
        
        @Override
        public List<InputStream> getResources(String filename) {
            List<InputStream> list = new ArrayList<InputStream>();
            InputStream in = getClass().getClassLoader().getResourceAsStream(basedir + "/" + filename + ".yml");
            if (in != null) {
                list.add(in);
            }
            return list;
        }
    }
    
	/**
	 * This resource provider load resource from the package folder of the class.
	 * 
	 * This can be used by simply declaring a provider extending this class
	 *
	 */
	static class Package implements ResourceProvider {

	    @Override
	    public List<InputStream> getResources(String filename) {
	        List<InputStream> list = new ArrayList<InputStream>();
	        InputStream in = getClass().getResourceAsStream(filename + ".yml");
	        if (in != null) {
	            list.add(in);
	        }
	        return list;
	    }

	}
	
    /**
     * This provider is the default one used to load Faker resources that are in root directory.
     * 
     * This one is not designed to be extended
     *
     */
	public final class Default implements ResourceProvider {

	    @Override
	    public List<InputStream> getResources(String filename) {
	        List<InputStream> list = new ArrayList<InputStream>();
	        String filenameWithExtension = "/" + filename + ".yml";
	        InputStream streamOnClass = Default.class.getResourceAsStream(filenameWithExtension);
	        if (streamOnClass != null) {
	            list.add(streamOnClass);
	            return list;
	        } 
	        streamOnClass = Default.class.getClassLoader().getResourceAsStream(filenameWithExtension);
	        if (streamOnClass != null) {
	            list.add(streamOnClass);
	        }       
	        return list;
	    }

	}


}
