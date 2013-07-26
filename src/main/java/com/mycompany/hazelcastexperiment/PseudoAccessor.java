package com.mycompany.hazelcastexperiment;

 
import com.hazelcast.client.ClientConfig;
import com.hazelcast.client.HazelcastClient;
 
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import java.util.Collection;
 
public class PseudoAccessor implements Runnable {
 
    private static HazelcastInstance haz;
    private int i;
    
    static {
      System.out.println("static initializer ....");
      ClientConfig clientConfig = new ClientConfig();
      clientConfig.addAddress("127.0.0.1");
      haz = HazelcastClient.newHazelcastClient(clientConfig);
}

 
    public PseudoAccessor(int _i) {
        this.i = _i;
        System.out.println("instantiating PseudoAccessor " + i);
        //ClientConfig clientConfig = new ClientConfig();
        //clientConfig.addAddress("127.0.0.1");
        //haz = HazelcastClient.newHazelcastClient(clientConfig);
    }
    
    
 
    @SuppressWarnings("unchecked")
    public void queryMap() {
 
        IMap map = haz.getMap("testmap");
        EntryObject e = new PredicateBuilder().getEntryObject();
        Predicate predicate = e.get("objectName").in("example: 1","example: 2","example: 4444").and(e.get("objectID").lessThan(4445));
        Collection<ObjectToCache> objects = (Collection<ObjectToCache>) map.values(predicate);
        for(ObjectToCache o : objects ) {
            System.out.println("Map Query Result :" + o);
        }
        System.out.println("map size =" + map.size());
    }
 
    public static void main(String[] args) throws InterruptedException {
        //PseudoAccessor use = new PseudoAccessor();
        //use.queryMap();
        //HazelcastClient.shutdownAll();
        
        for (int i =0; i < 1000; i++) {
            PseudoAccessor pa = new PseudoAccessor(i);
            pa.run();
        
        }
        
        Thread.sleep(10000);
        //HazelcastClient.shutdownAll(); 
        haz.shutdown(); // deprecated!! 
        Thread.sleep(10000);
        
    }

    public void run() {
        //PseudoAccessor use = new PseudoAccessor();
        queryMap();
    }
    
    protected void finalize() throws Throwable {
        System.out.println("finalizing PseudoAccessor " + i + " ...");
    } 
     
}