/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.screamingdata.hazelcastexperiment;

import com.hazelcast.client.ClientConfig;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author ubuntu
 */
public class CacheManagementUtilities {
    
    private final static String PERSIST_PATH  ="persistence/";
    private static HazelcastInstance haz;
    
     static {
      System.out.println("static initializer ....");
      ClientConfig clientConfig = new ClientConfig()
        .addAddress("127.0.0.1");       
      haz = HazelcastClient.newHazelcastClient(clientConfig);
} 
    
    
     public static void regenCache(String mapName) {
      
      IMap map = haz.getMap(mapName);
        File[] files =
            new File(getMapStorePath(mapName)).listFiles(getFilenameFilter());
        for(File file : files) {
            // change 33.ser to 33
            String key = file.getName().substring(0,file.getName().indexOf(".ser"));
            map.get(key);
        } 
    }
     
     public static void shutdown() {
         haz.shutdown(); 
     }
 
    private static String getMapStorePath(String mapName) {
        return PERSIST_PATH + mapName + "/";
    }
    
    private static FilenameFilter getFilenameFilter() {
        FilenameFilter filter = new FilenameFilter()
        {
            @Override
            public boolean accept(File file, String name) {
                return name.toLowerCase().endsWith(".ser");
            }
        };
        return filter;
    }
    
    public static void main(String[] args) {
        // for a quick test ...
        regenCache("testmap");
        shutdown();
    }
        
    
}
