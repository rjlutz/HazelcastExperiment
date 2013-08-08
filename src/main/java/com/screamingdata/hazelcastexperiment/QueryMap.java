package com.screamingdata.hazelcastexperiment;

 
import com.hazelcast.client.ClientConfig;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
 
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import java.util.Collection;
 
public class QueryMap {
 
    private static HazelcastInstance haz;
    
    static {
      System.out.println("static initializer ....");
      ClientConfig clientConfig = new ClientConfig();
      clientConfig.addAddress("127.0.0.1");       
      haz = HazelcastClient.newHazelcastClient(clientConfig);
}

 
    public QueryMap() {
        System.out.println("instantiating QueryList");
        
    }
 
    @SuppressWarnings("unchecked")
    public void queryMap() {
 
        IMap map = haz.getMap("testmap");
        //EntryObject e = new PredicateBuilder().getEntryObject();
        //Predicate predicate = e.get("objectName").in("example: 1","example: 2","example: 4444").and(e.get("objectID").lessThan(4445));
        //Collection<ObjectToCache> objects = (Collection<ObjectToCache>) map.values(predicate);
        Collection<ObjectToCache> objects = (Collection<ObjectToCache>) map.values();
        for(ObjectToCache o : objects ) {
            System.out.println("Map Query Result :" + o);
        }
        System.out.println("map size (should be 0) =" + map.size());
        
        for (int i=0; i<100; i++) {
            System.out.println("got: " + map.get(Integer.toString(i)));
        }
        System.out.println("map size (should be 100) =" + map.size());
    }
 
    public static void main(String[] args) throws InterruptedException {
        
     QueryMap q = new QueryMap();
     q.queryMap();
     haz.shutdown();
        
    }

    protected void finalize() throws Throwable {
        System.out.println("finalizing QueryList ");
    } 
     
}