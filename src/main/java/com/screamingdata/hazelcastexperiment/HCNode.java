package com.screamingdata.hazelcastexperiment;


import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import java.util.Map;
import java.util.Queue;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ubuntu
 */
public class HCNode {

    public static void main(String[] args) {
        
        Config cfg = new Config();
        MapConfig mapcfg = new MapConfig()
         .setName("testmap")
         .setBackupCount(1)
         .setTimeToLiveSeconds(1)
         //mapcfg.getMaxSizeConfig().setSize(10000);
         .setMaxIdleSeconds(0)
         .setEvictionPolicy(MapConfig.DEFAULT_EVICTION_POLICY); // default = NONE
        MapStoreConfig mapStoreCfg = new MapStoreConfig()
         .setFactoryClassName("com.screamingdata.hazelcastexperiment.FileSystemMapStoreFactory")
         .setEnabled(true)
         .setWriteDelaySeconds(1);
        mapcfg.setMapStoreConfig(mapStoreCfg);
        cfg.addMapConfig(mapcfg);
        
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
//        Map<Integer, String> mapCustomers = instance.getMap("customers");
//        mapCustomers.put(1, "Joe");
//        mapCustomers.put(2, "Ali");
//        mapCustomers.put(3, "Avi");
//
//        System.out.println("Customer with key 1: "+ mapCustomers.get(1));
//        System.out.println("Map Size:" + mapCustomers.size());
//
//        Queue<String> queueCustomers = instance.getQueue("customers");
//        queueCustomers.offer("Tom");
//        queueCustomers.offer("Mary");
//        queueCustomers.offer("Jane");
//        System.out.println("First customer: " + queueCustomers.poll());
//        System.out.println("Second customer: "+ queueCustomers.peek());
//        System.out.println("Queue size: " + queueCustomers.size());
    }
}