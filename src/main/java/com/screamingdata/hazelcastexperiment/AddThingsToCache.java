package com.screamingdata.hazelcastexperiment;


import com.hazelcast.client.ClientConfig;
import com.hazelcast.client.HazelcastClient;
import java.util.List;
import java.util.Map;
 
import com.hazelcast.core.HazelcastInstance;
 
public class AddThingsToCache {
 
    private HazelcastInstance haz;
     
    public AddThingsToCache() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.addAddress("127.0.0.1");
        haz = HazelcastClient.newHazelcastClient(clientConfig);
    }
     
    public void addListData() {
        List<ObjectToCache> list = haz.getList("testlist");
        for (int i = 0; i < 100; i++) {
            list.add(new ObjectToCache("example: " + i, "value" + i,i));
        }
    }
 
    public void addMapData() {
        Map<String, ObjectToCache> map = haz.getMap("testmap");
        for (int i = 0; i < 100; i++) {
            map.put(Integer.toString(i), new ObjectToCache("example: " + i, "value" + i,i));
        }
    }
    
    public void hangup() {
        System.out.println("shutting down hazelcast client");
        haz.shutdown(); // deprecated!!
    }
 
    public static void main(String[] args) {
        AddThingsToCache adder = new AddThingsToCache();
        adder.addListData();
        adder.addMapData();  
        adder.hangup();
        //HazelcastClient.shutdownAll();
    }
    
}