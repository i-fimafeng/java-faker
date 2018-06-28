package com.github.javafaker.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DummyProvider2 implements ResourceProvider {

    @Override
    public List<InputStream> getResources(String filename) {
        List<InputStream> list = new ArrayList<InputStream>();
        InputStream in = getClass().getClassLoader().getResourceAsStream("test2/" + filename + ".yml");
        if (in != null) {
            list.add(in);
        }
        return list;
    }
    
}